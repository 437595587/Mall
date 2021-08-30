package com.ruoyi.search;

import com.alibaba.fastjson.JSON;
import com.ruoyi.search.config.MallElasticSearchConfig;
import com.ruoyi.search.domain.Account;
import com.ruoyi.search.domain.User;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallSearchApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * (1)、方便检索{
     *     skuId: 1
     *     spuId: 11
     *     skuTitle: 华为xx
     *     price: 998
     *     saleCount: 99
     *     attrs:[
     *      {尺寸: 5寸},
     *      {CPU: 高通945},
     *      {分辨率: 全高清}
     *     ]
     * }
     * 冗余:
     *  100万*20=1000000*2KB=2000MB=2G
     *
     * (2)、
     *   sku索引{
     *       skuId: 1
     *       spuId: 11
     *       xxx
     *   }
     *
     *   attr索引:[
     *     spuId:11
     *     attrs:[
     *      {尺寸: 5寸},
     *      {CPU: 高通945},
     *      {分辨率: 全高清}
     *     ]
     *   ]
     *
     * 搜索小米： 粮食、手机、电器
     * 10000个，4000spu
     * 分布，4000个spu对应的所有可能属性
     * esClient spuId:[4000个spuId] 4000*8=32000byte=32kb
     *
     * 32kb*10000=320mb;
     *
     * @throws Exception
     */
    @Test
    public void searchData() throws Exception {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("bank");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //1.1)、 构造检索条件
//        sourceBuilder.query();
//        sourceBuilder.from();
//        sourceBuilder.size();
//        sourceBuilder.aggregation();
        sourceBuilder.query(QueryBuilders.matchQuery("address", "mill"));
        //1.2)、 按照年龄的值分布进行聚合
        TermsAggregationBuilder ageAgg = AggregationBuilders.terms("ageAgg").field("age").size(10);
        sourceBuilder.aggregation(ageAgg);
        //1.3)、 计算平均薪资
        AvgAggregationBuilder balanceAvg = AggregationBuilders.avg("balanceAvg").field("balance");
        sourceBuilder.aggregation(balanceAvg);
        System.out.println("检索条件 = " + sourceBuilder);
        searchRequest.source(sourceBuilder);
        //2、 执行检索
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, MallElasticSearchConfig.COMMON_OPTIONS);
        //3、 分析结果
        System.out.println("searchResponse = " + searchResponse);
//        JSON.parseObject(searchResponse.toString(), Map.class);
        //3.1)、 获取所有查询到的数据
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
//            hit.getIndex();hit.getType();hit.getId();
            Account account = JSON.parseObject(hit.getSourceAsString(), Account.class);
            System.out.println("account = " + account);
        }
        //3.2)、 获取这次检索到的分析信息
        Aggregations aggregations = searchResponse.getAggregations();
//        for (Aggregation aggregation : aggregations) {
//            System.out.println("当前聚合：" + aggregation.getName());
//        }
        Terms ageAgg1 = aggregations.get("ageAgg");
        for (Terms.Bucket bucket : ageAgg1.getBuckets()) {
            String keyAsString = bucket.getKeyAsString();
            System.out.println("年龄：" + keyAsString + "==>" + bucket.getDocCount());
        }
        Avg balanceAvg1 = aggregations.get("balanceAvg");
        System.out.println("平均薪资：" + balanceAvg1.getValue());
    }

    /**
     * 测试存储数据到es
     * 更新也可以
     */
    @Test
    public void indexData() throws Exception {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
//        indexRequest.source("username", "zhangsan", "age", 18, "gender", "男");
        User user = new User();
        user.setUserName("zhangsan");
        user.setAge(18);
        user.setGender("男");
        String jsonString = JSON.toJSONString(user);
        indexRequest.source(jsonString, XContentType.JSON); //要保存的内容
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, MallElasticSearchConfig.COMMON_OPTIONS);

        System.out.println(indexResponse);
    }

    @Test
    public void contextLoads() {
        System.out.println(restHighLevelClient);
    }


}
