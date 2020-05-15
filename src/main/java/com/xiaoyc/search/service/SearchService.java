package com.xiaoyc.search.service;

import com.xiaoyc.search.entity.vo.SearchVO;
import com.xiaoyc.search.tool.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author xiaoyc
 * @date 2020/5/12 0012 16:15
 */
@Service
public class SearchService {

    private static String URL = "http://www.panghaozi.com/pan/search/search?keyword=";

    /**
     * @description 胖浩子搜索
     *
     * @params [keyword]
     * @return java.util.List<com.xiaoyc.search.entity.vo.SearchVO>
     * @author xiaoyc
     * @date 2020/5/12 17:16
     **/
    public List<SearchVO> getSearchVOByPangHaoZi(String keyword) throws ErrorException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(URL + keyword + "&method=cache", List.class);
        } catch (Exception e) {
            throw new ErrorException(404, "服务器异常!!!请稍后重试!!!谢谢");
        }
    }


}
