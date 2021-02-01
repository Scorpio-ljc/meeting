package com.ruoyi.project.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @author liujc
 * @create 2021-01-07 13:51:27
 **/
@Data
public class UserFeedbackStatistic {
    private String subjectTitle;

    private List<SubStatistic> subStatisticList;


}
