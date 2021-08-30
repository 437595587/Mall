package com.ruoyi.search.service;

import com.ruoyi.search.domain.vo.SearchParam;
import com.ruoyi.search.domain.vo.SearchResult;

public interface ISearchService {
    SearchResult search(SearchParam param);
}
