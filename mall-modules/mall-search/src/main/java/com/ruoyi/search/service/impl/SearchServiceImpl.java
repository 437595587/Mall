package com.ruoyi.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.mall.api.RemoteProductService;
import com.ruoyi.mall.api.to.es.SkuEsModel;
import com.ruoyi.mall.api.vo.PmsAttrRespVo;
import com.ruoyi.mall.api.vo.PmsBrand;
import com.ruoyi.search.config.MallElasticSearchConfig;
import com.ruoyi.search.constant.EsConstant;
import com.ruoyi.search.domain.vo.SearchParam;
import com.ruoyi.search.domain.vo.SearchResult;
import com.ruoyi.search.service.ISearchService;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private RemoteProductService remoteProductService;

    @Override
    public SearchResult search(SearchParam param) {
        SearchResult searchResult = null;
        SearchRequest searchRequest = buildSearchRequest(param);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, MallElasticSearchConfig.COMMON_OPTIONS);
            searchResult = buildSearchResult(searchResponse, param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResult;
    }

    /**
     * ??????????????????
     * @param searchResponse
     * @return
     */
    private SearchResult buildSearchResult(SearchResponse searchResponse, SearchParam param) {
        SearchResult result = new SearchResult();
        SearchHits hits = searchResponse.getHits();
        //1. ????????????????????????
        List<SkuEsModel> esModels = new ArrayList<>();
        if (hits.getHits() != null && hits.getHits().length > 0) {
            for (SearchHit hit : hits.getHits()) {
                String sourceAsString = hit.getSourceAsString();
                SkuEsModel esModel = JSON.parseObject(sourceAsString, SkuEsModel.class);
                if (StringUtils.isNotEmpty(param.getKeyword())) {
                    HighlightField skuTitle = hit.getHighlightFields().get("skuTitle");
                    esModel.setSkuTitle(skuTitle.getFragments()[0].string());
                }
                esModels.add(esModel);
            }
        }
        result.setProducts(esModels);
        Aggregations aggregations = searchResponse.getAggregations();
        //2. ?????????????????????????????????????????????
        ParsedNested attr_agg = aggregations.get("attr_agg");
        List<SearchResult.AttrVo> attrVos = new ArrayList<>();
        ParsedLongTerms attr_id_agg = attr_agg.getAggregations().get("attr_id_agg");
        for (Terms.Bucket bucket : attr_id_agg.getBuckets()) {
            SearchResult.AttrVo attrVo = new SearchResult.AttrVo();
            long attrId = bucket.getKeyAsNumber().longValue();
            attrVo.setAttrId(attrId);
            String attrName = ((ParsedStringTerms) bucket.getAggregations().get("attr_name_agg")).getBuckets().get(0).getKeyAsString();
            List<String> attrValue= ((ParsedStringTerms) bucket.getAggregations().get("attr_value_agg")).getBuckets().stream().map(MultiBucketsAggregation.Bucket::getKeyAsString).collect(Collectors.toList());
            attrVo.setAttrName(attrName);
            attrVo.setAttrValue(attrValue);
            attrVos.add(attrVo);
        }
        result.setAttrs(attrVos);
        //3. ?????????????????????????????????????????????
        ParsedLongTerms brand_agg = aggregations.get("brand_agg");
        List<SearchResult.BrandVo> brandVos = new ArrayList<>();
        for (Terms.Bucket bucket : brand_agg.getBuckets()) {
            SearchResult.BrandVo brandVo = new SearchResult.BrandVo();
            brandVo.setBrandId(bucket.getKeyAsNumber().longValue());
            String brandName = ((ParsedStringTerms) bucket.getAggregations().get("brand_name_agg")).getBuckets().get(0).getKeyAsString();
            String brandImg = ((ParsedStringTerms) bucket.getAggregations().get("brand_img_agg")).getBuckets().get(0).getKeyAsString();
            brandVo.setBrandName(brandName);
            brandVo.setBrandImg(brandImg);
            brandVos.add(brandVo);
        }
        result.setBrands(brandVos);
        //4. ?????????????????????????????????????????????
        ParsedLongTerms catalog_agg = aggregations.get("catalog_agg");
        List<SearchResult.CatalogVo> catalogVos = new ArrayList<>();
        for (Terms.Bucket bucket : catalog_agg.getBuckets()) {
            SearchResult.CatalogVo catalogVo = new SearchResult.CatalogVo();
            catalogVo.setCatalogId(bucket.getKeyAsNumber().longValue());
            ParsedStringTerms catalog_name_agg = bucket.getAggregations().get("catalog_name_agg");
            catalogVo.setCatalogName(catalog_name_agg.getBuckets().get(0).getKeyAsString());
            catalogVos.add(catalogVo);
        }
        result.setCatalogs(catalogVos);
        //5. ???????????? ??????-????????????-?????????
        result.setPageNum(param.getPageNum());
        long total = hits.getTotalHits().value;
        result.setTotal(total);
        int totalPages = Math.toIntExact(total % EsConstant.PRODUCT_PAGE_SIZE == 0 ? total / EsConstant.PRODUCT_PAGE_SIZE : total / EsConstant.PRODUCT_PAGE_SIZE + 1);
        result.setTotalPages(totalPages);

        List<Integer> pageNavs = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            pageNavs.add(i);
        }
        result.setPageNavs(pageNavs);
        String queryString = param.get_queryString();
        //6.?????????????????????
        //??????
        if (param.getBrandId() != null && param.getBrandId().size() > 0) {
            List<SearchResult.NavVo> navs = result.getNavs();
            SearchResult.NavVo navVo = new SearchResult.NavVo();
            navVo.setName("??????");
            R<List<PmsBrand>> r = remoteProductService.getBrandInfos(param.getBrandId());
            if (r.getCode() == 200) {
                List<PmsBrand> data = r.getData();
                StringBuilder builder = new StringBuilder();
                String replace = queryString;
                for (int i = 0; i < data.size(); i++) {
                    PmsBrand brand = data.get(i);
                    if (i == data.size() - 1) {
                        builder.append(brand.getName());
                    } else {
                        builder.append(brand.getName()).append("???");
                    }
                    replace = replaceQueryString(replace, brand.getBrandId().toString(), "brandId");
                }
                navVo.setNavValue(builder.toString());
                if (param.get_queryString().contains("keyword")) {
                    navVo.setLink("http://search.mall.com/list.html?" + replace);
                } else {
                    navVo.setLink("http://search.mall.com/list.html" + replace);
                }
            }
            navs.add(navVo);
        }

        //TODO ??????

        //??????
        if (param.getAttrs() != null && param.getAttrs().size() > 0) {
            List<SearchResult.NavVo> navVos = param.getAttrs().stream().map(attr -> {
                SearchResult.NavVo navVo = new SearchResult.NavVo();
                String[] s = attr.split("_");
                s = Arrays.stream(s).filter(StringUtils::isNotEmpty).collect(Collectors.toList()).toArray(new String[]{});
                if (s.length == 2) {
                    navVo.setNavValue(s[1]);
                    R<PmsAttrRespVo>  r= remoteProductService.getAttrInfo(Long.parseLong(s[0]));
                    result.getAttrIds().add(Long.parseLong(s[0]));
                    if (r.getCode() == 200) {
                        PmsAttrRespVo data = r.getData();
                        navVo.setName(data.getAttrName());
                    } else {
                        navVo.setName(s[0]);
                    }
                    String replace = replaceQueryString(queryString, attr, "attrs");
                    if (param.get_queryString().contains("keyword")) {
                        navVo.setLink("http://search.mall.com/list.html?" + replace);
                    } else {
                        navVo.setLink("http://search.mall.com/list.html" + replace);
                    }
                }
                return navVo;
            }).collect(Collectors.toList());
            result.getNavs().addAll(navVos);
        }
        return result;
    }

    private String replaceQueryString(String queryString, String value, String key) {
        String encode = encode(value); //???????????????????????????java?????????
        String replace = queryString.replace("&" + key + "=" + encode, "");
        replace = replace.replace(key + "=" + encode, "");
        return replace;
    }

    private String encode(String url) {
        char[] tp = url.toCharArray();
        String now = "";
        for (char ch : tp) {
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
            if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                    || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                    || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                    || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                try {
                    now += URLEncoder.encode(ch + "", "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else if ((int) ch == 32) {
                now += "%20";
            } else {
                now += ch;
            }
        }
        return now;
    }

    /**
     * ??????????????????
     * @return
     */
    private SearchRequest buildSearchRequest(SearchParam param) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //?????????????????????????????????????????????????????????????????????????????????
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //1.1 must-????????????
        if (StringUtils.isNotEmpty(param.getKeyword())) {
            boolQuery.must(QueryBuilders.matchQuery("skuTitle", param.getKeyword()));
        }
        //1.2 filter ??????????????????id??????
        if (param.getCatalog3Id() != null) {
            boolQuery.filter(QueryBuilders.termQuery("catalogId", param.getCatalog3Id()));
        }
        //1.3 filter ????????????id??????
        if (param.getBrandId() != null && param.getBrandId().size() > 0) {
            boolQuery.filter(QueryBuilders.termsQuery("brandId", param.getBrandId()));
        }
        //1.4 ?????????????????????????????????
        if (param.getHasStock() != null) {
            boolQuery.filter(QueryBuilders.termQuery("hasStock", param.getHasStock() == 1));
        }
        //1.5 ??????????????????
        if (StringUtils.isNotEmpty(param.getSkuPrice())) {
            //1_500/_500/500_
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("skuPrice");
            String[] s = param.getSkuPrice().split("_");
            s = Arrays.stream(s).filter(StringUtils::isNotEmpty).collect(Collectors.toList()).toArray(new String[]{});
            if (s.length == 2) {
                rangeQuery.gte(s[0]).lte(s[1]);
            } else if (s.length == 1) {
                if (param.getSkuPrice().startsWith("_")) {
                    rangeQuery.lte(s[0]);
                }
                if (param.getSkuPrice().endsWith("_")) {
                    rangeQuery.gt(s[0]);
                }
            }
            boolQuery.filter(rangeQuery);
        }
        //1.6 ????????????????????????
        if (param.getAttrs() != null && param.getAttrs().size() > 0) {
            for (String attrStr : param.getAttrs()) {
                // attrs=1_5???:8???&attrs=2_16G:8G
                BoolQueryBuilder nestedBoolQuery = QueryBuilders.boolQuery();
                String[] s = attrStr.split("_");
                if (s.length == 2) {
                    String attrId = s[0]; //???????????????id
                    String[] attrValues = s[1].split(":");// ??????????????????
                    nestedBoolQuery.must(QueryBuilders.termQuery("attrs.attrId", attrId));
                    nestedBoolQuery.must(QueryBuilders.termsQuery("attrs.attrValue", attrValues));
                }
                //?????????????????????nested??????
                NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("attrs", nestedBoolQuery, ScoreMode.None);
                boolQuery.filter(nestedQuery);
            }
        }
        //?????????????????????
        sourceBuilder.query(boolQuery);
        //????????????????????????
        //2.1 ??????
        if (StringUtils.isNotEmpty(param.getSort())) {
            String sort = param.getSort();
            String[] s = sort.split("_");
            s = Arrays.stream(s).filter(StringUtils::isNotEmpty).collect(Collectors.toList()).toArray(new String[]{});
            if (s.length == 2) {
                SortOrder order = s[1].equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC;
                sourceBuilder.sort(s[0], order);
            }
        }
        //2.2 ??????
        sourceBuilder.from((param.getPageNum() - 1) * EsConstant.PRODUCT_PAGE_SIZE);
        sourceBuilder.size(EsConstant.PRODUCT_PAGE_SIZE);
        //2.3 ??????
        if (StringUtils.isNotEmpty(param.getKeyword())) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuTitle");
            highlightBuilder.preTags("<b style='color: red'>");
            highlightBuilder.postTags("</b>");
            sourceBuilder.highlighter(highlightBuilder);
        }
        //????????????
        //3.1 ????????????
        TermsAggregationBuilder brand_agg = AggregationBuilders.terms("brand_agg");
        brand_agg.field("brandId").size(50);
        //????????????????????????
        brand_agg.subAggregation(AggregationBuilders.terms("brand_name_agg").field("brandName").size(1));
        brand_agg.subAggregation(AggregationBuilders.terms("brand_img_agg").field("brandImg").size(1));
        sourceBuilder.aggregation(brand_agg);
        //3.2 ????????????
        TermsAggregationBuilder catalog_agg = AggregationBuilders.terms("catalog_agg").field("catalogId").size(20);
        catalog_agg.subAggregation(AggregationBuilders.terms("catalog_name_agg").field("catalogName").size(1));
        sourceBuilder.aggregation(catalog_agg);
        //3.3 ????????????
        NestedAggregationBuilder attr_agg = AggregationBuilders.nested("attr_agg", "attrs");
        TermsAggregationBuilder attr_id_agg = AggregationBuilders.terms("attr_id_agg").field("attrs.attrId");
        attr_id_agg.subAggregation(AggregationBuilders.terms("attr_name_agg").field("attrs.attrName").size(1));
        attr_id_agg.subAggregation(AggregationBuilders.terms("attr_value_agg").field("attrs.attrValue").size(50));
        attr_agg.subAggregation(attr_id_agg);
        sourceBuilder.aggregation(attr_agg);
        System.out.println("DSL?????????" + sourceBuilder);
        SearchRequest searchRequest = new SearchRequest(new String[]{EsConstant.PRODUCT_INDEX}, sourceBuilder);
        return searchRequest;
    }
}
