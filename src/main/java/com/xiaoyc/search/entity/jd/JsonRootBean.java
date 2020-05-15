package com.xiaoyc.search.entity.jd;

import lombok.Data;

/**
 * @author xiaoyc
 * @date 2020/5/15 0015 20:04
 */
@Data
public class JsonRootBean {
    private String code;
    private boolean charge;
    private int remain;
    private int remainTimes;
    private int remainSeconds;
    private String msg;
    private Result result;
}
