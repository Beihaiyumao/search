package com.xiaoyc.search;

import com.xiaoyc.search.service.DouYinService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SearchApplicationTests {

    @Autowired
    DouYinService douYinService;
    @Test
    void contextLoads() throws Exception {
        System.out.println(douYinService.get(" https://v.douyin.com/EorSXH/"));
    }

    @Test
    void getVideo() throws Exception {
        System.out.println(douYinService.getVideo(" https://v.douyin.com/EorSXH/"));
    }
}
