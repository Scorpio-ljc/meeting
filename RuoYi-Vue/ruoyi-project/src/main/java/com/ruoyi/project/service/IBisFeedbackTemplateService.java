package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.BisFeedbackTemplate;
import com.ruoyi.project.domain.vo.BisFeedbackTemplateDetailVo;
import com.ruoyi.project.domain.vo.BisFeedbackTemplateVo;

/**
 * 反馈模版Service接口
 *
 * @author ruoyi
 * @date 2020-12-11
 */
public interface IBisFeedbackTemplateService
{
    /**
     * 查询反馈模版
     *
     * @param id 反馈模版ID
     * @return 反馈模版
     */
    public BisFeedbackTemplate selectBisFeedbackTemplateById(Long id);

    /**
     * 根据模版code查询反馈模版
     *
     * @param feedbackCode 反馈模版编码
     * @return
     */
    BisFeedbackTemplateDetailVo selectBisFeedbackTemplateByFeedbackCode(String feedbackCode);

    /**
     * 根据模版code及用户信息查询反馈模版
     *
     * @param feedbackCode  反馈编码
     * @param uid 用户id
     * @return
     */
    BisFeedbackTemplateDetailVo selectBisFeedbackTemplateByFeedbackCode(Long meetingId,String feedbackCode,Long uid);

    /**
     * 查询反馈模版列表
     *
     * @param bisFeedbackTemplate 反馈模版
     * @return 反馈模版集合
     */
    public List<BisFeedbackTemplate> selectBisFeedbackTemplateList(BisFeedbackTemplate bisFeedbackTemplate);

    /**
     * 新增反馈模版
     *
     * @param bisFeedbackTemplate 反馈模版
     * @return 结果
     */
    @Deprecated
    public int insertBisFeedbackTemplate(BisFeedbackTemplate bisFeedbackTemplate);

    /**
     * 保存反馈模版详情
     *
     * @param bisFeedbackTemplateList 反馈模版
     * @return 结果
     */
     int addBisFeedbackTemplate(List<BisFeedbackTemplateVo> bisFeedbackTemplateList);

    /**
     * 保存反馈模版主表
     *
     * @param bisFeedbackTemplateDetailVo 反馈模版
     * @return 结果
     */
     int addBisFeedbackTemplate(BisFeedbackTemplateDetailVo bisFeedbackTemplateDetailVo);

    /**
     * 修改反馈模版
     *
     * @param bisFeedbackTemplateUpdateVo 反馈模版
     * @return 结果
     */
     int updateBisFeedbackTemplate(BisFeedbackTemplateDetailVo bisFeedbackTemplateUpdateVo);

    /**
     * 批量删除反馈模版
     *
     * @param ids 需要删除的反馈模版ID
     * @return 结果
     */
    public int deleteBisFeedbackTemplateByIds(Long[] ids);

    /**
     * 删除反馈模版信息
     *
     * @param id 反馈模版ID
     * @return 结果
     */
    public int deleteBisFeedbackTemplateById(Long id);

    int deleteByCode(String feedbackCode);

}
