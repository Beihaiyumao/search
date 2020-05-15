package com.xiaoyc.search.entity.jd;

import lombok.Data;

import java.util.List;

/**
 * @author xiaoyc
 * @date 2020/5/15 0015 20:05
 */
@Data
public class Result {
    private int status;
    private String message;
    private List<GarbageInfo> garbage_info;
}
