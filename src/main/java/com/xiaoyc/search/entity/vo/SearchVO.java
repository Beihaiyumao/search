package com.xiaoyc.search.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author xiaoyc
 * @date 2020/5/12 0012 16:22
 */
@Data
public class SearchVO {
    private int id;
    private String rawID;
    private String url;
    private String title;
    private String ext;
    private Date cTime;
    private String size;
    private Boolean hasPwd;
    private String password;
    private String category;
    private String resource;
}
