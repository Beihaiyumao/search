package com.xiaoyc.search.service;

import com.xiaoyc.search.entity.jd.GarbageInfo;
import com.xiaoyc.search.entity.jd.JsonRootBean;
import com.xiaoyc.search.tool.ErrorException;
import com.xiaoyc.search.tool.FileUitl;
import com.xiaoyc.search.tool.MD5Util;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoyc
 * @date 2020/5/15 0015 19:38
 */
@Service
public class RubbishService {

    private static final String APP_KEY = "cde4c94e01239ffe0dda2297fb4419e8";

    private static final String SECTET_KEY = "9617cfc91bb5fa855f9036d2c1d9b406";

    private static String URL_MORE = APP_KEY + "&timestamp=" + System.currentTimeMillis() + "&sign=" + MD5Util.getMD5Str(SECTET_KEY + System.currentTimeMillis());

    private static String JD_TEXT_URL = "https://aiapi.jd.com/jdai/garbageTextSearch?appkey=" + URL_MORE;

    private static String JD_IMAGE_URL = "https://aiapi.jd.com/jdai/garbageImageSearch?appkey=" + URL_MORE;

    /**
     * @description 文字识别垃圾分类
     *
     * @params [cityId, text]
     * @return com.xiaoyc.search.entity.jd.JsonRootBean
     * @author xiaoyc
     * @date 2020/5/15 0015 20:16
     **/
    public List<GarbageInfo> getText(String cityId, String text) throws Exception {

        Map<String, String> map = new HashMap<>(2);
        map.put("cityId", cityId);
        map.put("text", text);
        return publicGetResult(map, JD_TEXT_URL);

    }

    /**
     * @description 图形识别垃圾分类
     *
     * @params [cityId, file]
     * @return java.util.List<com.xiaoyc.search.entity.jd.GarbageInfo>
     * @author xiaoyc
     * @date 2020/5/15 0015 20:51
     **/
    public List<GarbageInfo> getImage(String cityId, MultipartFile file) throws Exception {
        String imgBase64 = FileUitl.encodeBase64File(file);
        Map<String, String> map = new HashMap<>(2);
        map.put("cityId", cityId);
        map.put("imgBase64", imgBase64);
        return publicGetResult(map, JD_IMAGE_URL);
    }

    /**
     * @description 公共方法
     *
     * @params [map, url]
     * @return java.util.List<com.xiaoyc.search.entity.jd.GarbageInfo>
     * @author xiaoyc
     * @date 2020/5/15 0015 20:49
     **/
    public List<GarbageInfo> publicGetResult(Map<String, String> map, String url) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity(map, headers);
            ResponseEntity<JsonRootBean> fileResponseEntity = restTemplate.postForEntity(url, httpEntity, JsonRootBean.class);
            JsonRootBean jsonRootBean = fileResponseEntity.getBody();
            if (jsonRootBean.getResult().getStatus() != 0) {
                throw new Exception();
            }
            return fileResponseEntity.getBody().getResult().getGarbage_info();
        } catch (Exception e) {
            throw new ErrorException(404, "服务器异常!!!请稍后重试!!!谢谢");
        }
    }
}
