package com.ruoyi.project.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liujc
 * @create 2020-12-14 23:32:16
 **/
@Data
public class SignInResponseVo implements Serializable {
    /**
     * 签到状态：0-未签到 1-已签到
     */
    private String signState;
    /**
     * 签到时间
     */
    private Date signTime;

    /**
     * 1-正常 2-不在打卡区域 3-不在时间范围内
     */
    private String state;

    public SignInResponseVo(String signState, Date signTime, String state) {
        this.signState = signState;
        this.signTime = signTime;
        this.state = state;
    }
}
