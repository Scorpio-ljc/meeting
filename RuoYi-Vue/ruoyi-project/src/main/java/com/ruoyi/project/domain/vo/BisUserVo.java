package com.ruoyi.project.domain.vo;

import com.ruoyi.project.domain.BisUser;
import lombok.Data;

/**
 * @author liujc
 * @create 2020-12-14 21:37:59
 **/
@Data
public class BisUserVo extends BisUser {
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
    /**
     * token令牌
     */
    private String token;

    /** 手机号 */
    private String mobile;

    /** 身份证号 */
    private String cardId;

    /** 职务 */
    private String position;

    /** 单位名称 */
    private String orgName;

    /** 邮箱 */
    private String email;
    /**
     * 会议id
     */
    private Long  meetingId;
    /**
     * 登陆code
     */
    private String code;
    /**
     * 当前会议id
     */
    private Long currentMeetingId;
}
