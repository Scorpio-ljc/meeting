package com.ruoyi.project.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author liujc
 * @create 2021-01-19 16:58:00
 **/
@Data
public class MeetingDetailQueryVo {
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
    private Long userId;
}
