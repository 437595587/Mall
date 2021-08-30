package com.ruoyi.search.controller;

import com.ruoyi.search.domain.vo.SearchParam;
import com.ruoyi.search.domain.vo.SearchResult;
import com.ruoyi.search.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @GetMapping("/list.html")
    public String listPage(SearchParam param, Model model, HttpServletRequest request) {
        String queryString = request.getQueryString();
        param.set_queryString(queryString);
        SearchResult result = searchService.search(param);
        model.addAttribute("result", result);
        return "list";
    }
}
