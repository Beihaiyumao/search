package com.xiaoyc.search;

import com.xiaoyc.search.service.DouYinService;
import com.xiaoyc.search.service.RubbishService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchApplicationTests {

    @Autowired
    DouYinService douYinService;
    @Autowired
    private RubbishService rubbishService;

    @Test
    void contextLoads() throws Exception {
        System.out.println(douYinService.get(" https://v.douyin.com/EorSXH/"));
    }

    @Test
    void getVideo() throws Exception {
        System.out.println(douYinService.getVideo(" https://v.douyin.com/EorSXH/"));
    }

    @Test
    void getTime() {
        System.out.println(System.currentTimeMillis());
        Integer a=2;
        Integer b=3;
        System.out.println(a-b);
    }
    @Test
    void getTextRubb() throws Exception {
        System.out.println(rubbishService.getText("310000","剩饭"));
    }
}
