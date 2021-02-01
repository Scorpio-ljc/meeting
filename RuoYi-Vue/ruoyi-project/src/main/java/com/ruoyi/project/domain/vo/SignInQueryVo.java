package com.ruoyi.project.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author liujc
 * @create 2021-01-22 18:26:36
 **/
@Data
public class SignInQueryVo {
    private Long userId;
    private Long meetingId;
    private Long currentMeetingId;
    private Date startDate;

    public SignInQueryVo(Long userId, Long meetingId, Long currentMeetingId,Date startDate) {
        this.userId = userId;
        this.meetingId = meetingId;
        this.currentMeetingId = currentMeetingId;
        this.startDate = startDate;
    }
}
