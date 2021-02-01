package com.ruoyi.project.domain.vo;

import com.ruoyi.project.domain.BisSubject;
import lombok.Data;

/**
 * 题目选择vo
 * @author liujc
 * @create 2020-12-14 23:51:04
 **/
@Data
public class BisSubjectVo extends BisSubject {
    /**
     * 0-未选中 1-选中
     */
    private String picking;
    /**
     * 题目名称
     */
    private String subject;
}
