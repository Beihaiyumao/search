package com.xiaoyc.search.controller;

import com.xiaoyc.search.entity.vo.SearchVO;
import com.xiaoyc.search.service.SearchService;
import com.xiaoyc.search.tool.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaoyc
 * @date 2020/5/12 0012 16:14
 */
@RestController
@RequestMapping("/search")
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
    @GetMapping("/phz")
    public List<SearchVO> get(@RequestParam("keyword") String keyword) throws ErrorException {
        return searchService.getSearchVOByPangHaoZi(keyword);
    }

    /**
     * @description 逃避微信官方审核
     *
     * @params []
     * @return java.lang.Boolean
     * @author xiaoyc
     * @date 2020/5/15 0015 15:59
     **/
    @GetMapping("/tb")
    public Boolean getStatus() {
        return Boolean.TRUE;
    }
}
