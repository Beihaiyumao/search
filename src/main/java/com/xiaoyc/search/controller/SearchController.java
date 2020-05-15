package com.xiaoyc.search.controller;

import com.xiaoyc.search.entity.vo.SearchVO;
import com.xiaoyc.search.service.SearchService;
import com.xiaoyc.search.tool.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaoyc
 * @date 2020/5/12 0012 16:14
 */
@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * @description
     *  胖浩子搜索
     * @params [keyword]
     * @return java.util.List<com.xiaoyc.search.entity.vo.SearchVO>
     * @author xiaoyc
     * @date 2020/5/12 17:15
     **/
    @GetMapping("/search")
    public List<SearchVO> get(@RequestParam("keyword") String keyword) throws ErrorException {
        return searchService.getSearchVOByPangHaoZi(keyword);
    }
}
