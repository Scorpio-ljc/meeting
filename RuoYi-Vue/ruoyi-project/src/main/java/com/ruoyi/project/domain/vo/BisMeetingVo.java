package com.ruoyi.project.domain.vo;

import com.ruoyi.project.domain.BisMeeting;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会议vo
 * @author liujc
 * @create 2021-01-07 09:52:51
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class BisMeetingVo extends BisMeeting {
    /**
     * 1-未报名 2-已报名未签到 3-已签到 4-已经结束 5-会议未开始 6-报名已结束
     */
    private String state;
    /**
     * 是否新用户
     */
    private boolean newUser;
    /**
     * 是否打卡范围
     */
    private boolean signInArea;
    /**
     * 是否反馈
     */
    private boolean feedback;

}
