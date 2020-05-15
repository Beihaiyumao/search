package com.xiaoyc.search.controller;

import java.io.File;

import com.xiaoyc.search.entity.jd.GarbageInfo;
import com.xiaoyc.search.service.RubbishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xiaoyc 垃圾分类搜索
 * @date 2020/5/15 0015 19:35
 */
@RequestMapping("/rubbish")
@RestController
public class RubbishController {

    @Autowired
    private RubbishService rubbishService;

    /**
     * @description 文字识别垃圾分类
     *
     * @params [cityId, text]
     * @return java.util.List<com.xiaoyc.search.entity.jd.GarbageInfo>
     * @author xiaoyc
     * @date 2020/5/15 0015 20:32
     **/
    @GetMapping("/text")
    public List<GarbageInfo> getText(@RequestParam("cityId") String cityId, @RequestParam("text") String text) throws Exception {
        return rubbishService.getText(cityId, text);
    }

    /**
     * @description 图形识别垃圾分类
     *
     * @params [cityId, file]
     * @return java.util.List<com.xiaoyc.search.entity.jd.GarbageInfo>
     * @author xiaoyc
     * @date 2020/5/15 0015 20:52
     **/
    @PostMapping("/image")
    public List<GarbageInfo> getImage(@RequestParam("cityId") String cityId, @RequestParam("file") MultipartFile file) throws Exception {
        return rubbishService.getImage(cityId, file);
    }
}
