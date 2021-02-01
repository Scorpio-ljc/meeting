package com.ruoyi.common.core.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liujc
 * @create 2020-12-14 22:07:29
 **/
@Data
public class UserInfor implements Serializable {
    /**
     * 微信昵称
     */
    private String nickname;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 微信头像
     */
    private String avatar;
    /**
     * 用户id
     */
    private Long uid;


}
