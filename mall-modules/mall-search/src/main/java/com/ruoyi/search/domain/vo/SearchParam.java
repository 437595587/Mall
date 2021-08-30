package com.ruoyi.search.domain.vo;

import java.util.List;

/**
 * 创建SearchParam用于检索VO
 *
 * 全文检索：skuTitle-》keyword
 * 排序：saleCount（销量）、hotScore（热度分）、skuPrice（价格）
 * 过滤：hasStock、skuPrice区间、brandId、catalog3Id、attrs
 * 聚合：attrs
 * keyword=小米&
 * sort=saleCount_desc/asc&
 * hasStock=0/1&
 * skuPrice=400_1900&
 * brandId=1&
 * catalog3Id=1&
 * attrs=1_3G:4G:5G&
 * attrs=2_骁龙845&
 * attrs=4_高清屏
 *
 */
public class SearchParam {
    // 页面传递过来的全文匹配关键字
    private String keyword;

    /** 三级分类id*/
    private Long catalog3Id;
    //排序条件：sort=price/salecount/hotscore_desc/asc
    private String sort;
    // 仅显示有货
    private Integer hasStock;

    /*** 价格区间 */
    private String skuPrice;

    /*** 品牌id 可以多选 */
    private List<Long> brandId;

    /*** 按照属性进行筛选 */
    private List<String> attrs;

    /*** 页码*/
    private Integer pageNum = 1;

    /*** 原生所有查询属性*/
    private String _queryString;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getCatalog3Id() {
        return catalog3Id;
    }

    public void setCatalog3Id(Long catalog3Id) {
        this.catalog3Id = catalog3Id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getHasStock() {
        return hasStock;
    }

    public void setHasStock(Integer hasStock) {
        this.hasStock = hasStock;
    }

    public String getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(String skuPrice) {
        this.skuPrice = skuPrice;
    }

    public List<Long> getBrandId() {
        return brandId;
    }

    public void setBrandId(List<Long> brandId) {
        this.brandId = brandId;
    }

    public List<String> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<String> attrs) {
        this.attrs = attrs;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String get_queryString() {
        return _queryString;
    }

    public void set_queryString(String _queryString) {
        this._queryString = _queryString;
    }
}
