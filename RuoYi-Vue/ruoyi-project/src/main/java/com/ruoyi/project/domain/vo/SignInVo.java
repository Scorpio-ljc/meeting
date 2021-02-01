package com.ruoyi.project.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 签到请求对象
 *
 * @author liujc
 * @create 2020-12-14 23:15:59
 **/
@Data
public class SignInVo implements Serializable {
    /**
     * 会议id
     */
    private Long meetingId;
    /**
     * 经度
     */
    private BigDecimal lng;
    /**
     * 纬度
     */
    private BigDecimal lat;
    /**
     * 用户id
     */
    private Long uid;
}
