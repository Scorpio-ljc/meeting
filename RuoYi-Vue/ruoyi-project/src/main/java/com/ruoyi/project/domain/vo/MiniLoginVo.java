package com.ruoyi.project.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liujc
 * @create 2020-12-14 20:57:41
 **/
@Data
public class MiniLoginVo implements Serializable {
    /**
     * 小程序登code(微信获取)
     */
    private String loginCode;
}
