package com.xiaoyc.search.tool;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Iterator;

/**
 * @author xiaoyc
 * @date 2020/5/12 17:09
 */
@Getter
@NoArgsConstructor
public class ErrorException extends Exception {

    private String msg;

    private Integer code;

    public ErrorException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
