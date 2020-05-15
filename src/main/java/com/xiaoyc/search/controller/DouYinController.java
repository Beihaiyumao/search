package com.xiaoyc.search.controller;



import com.xiaoyc.search.entity.vo.DDXResultVO;
import com.xiaoyc.search.service.DouYinReptileService;
import com.xiaoyc.search.service.DouYinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaoyc
 * @date 2020/5/14 0014 18:16
 */
@RestController
@RequestMapping("/water-out")
public class DouYinController {

    @Autowired
    private DouYinService douYinService;

    /**
     * @description 获取封面
     *
     * @params [url]
     * @return com.xiaoyc.search.entity.vo.DDXResultVO
     * @author xiaoyc
     * @date 2020/5/14 0014 18:31
     **/
    @GetMapping("/dy/fm")
    public DDXResultVO get(@RequestParam("url") String url) throws Exception {
        return douYinService.get(url);
    }

    /**
     * @description 下载视频
     *
     * @params [url]
     * @return java.io.File
     * @author xiaoyc
     * @date 2020/5/15 0015 10:49
     **/
    @GetMapping("/dy/video")
    public byte[] getVideo(String url) throws Exception {
        return douYinService.getVideo(url);
    }

    @Autowired
    private DouYinReptileService douYinReptileService;

    @GetMapping("/dy")
    public String getT(@RequestParam("url") String url) throws Exception {
        return douYinReptileService.gettt(url);
    }

}
