package com.xiaoyc.search.entity.jd;

import lombok.Data;

/**
 * @author xiaoyc
 * @date 2020/5/15 0015 20:05
 */
@Data
public class GarbageInfo {
    private String cate_name;
    private String city_id;
    private String city_name;
    private int confidence;
    private String garbage_name;
    private String ps;
}
