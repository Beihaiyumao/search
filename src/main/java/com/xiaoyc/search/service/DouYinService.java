package com.xiaoyc.search.service;

import com.alibaba.fastjson.JSONObject;
import com.xiaoyc.search.entity.vo.DDXResultVO;
import com.xiaoyc.search.tool.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoyc
 * @date 2020/5/14 0014 18:16
 */
@Service
public class DouYinService {

    private static String DDX_FM_URL = "https://www.doudouxia.com/data-center/douyin/tool/videoParsing?url=";

    private static String DDX_VIDEO_URL = "https://www.doudouxia.com/data-center/douyin/tool/videoDownload";

    /**
     * @description 解析封面
     *
     * @params [url]
     * @return com.xiaoyc.search.entity.vo.DDXResultVO
     * @author xiaoyc
     * @date 2020/5/14 0014 18:28
     **/
    public DDXResultVO get(String url) throws Exception {
        if (url == null) {
            return new DDXResultVO();
        }
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(DDX_FM_URL + url, DDXResultVO.class);
        } catch (Exception e) {
            throw new ErrorException(404, "服务器异常!!!请稍后重试!!!谢谢");
        }
    }

    /**
     * @description 获取去水印后的视频内容
     *
     * @params [url]
     * @return java.io.File
     * @author xiaoyc
     * @date 2020/5/15 0015 10:01
     **/
    public byte[] getVideo(String url) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            MultiValueMap<String ,String> map = new LinkedMultiValueMap();
            map.add("url", url);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity httpEntity = new HttpEntity(map, headers);
            ResponseEntity<byte[]> fileResponseEntity = restTemplate.postForEntity(DDX_VIDEO_URL, httpEntity, byte[].class);
            System.out.println("/");
            return fileResponseEntity.getBody();
        } catch (Exception e) {
            throw new ErrorException(404, "服务器异常!!!请稍后重试!!!谢谢");
        }
    }

}
