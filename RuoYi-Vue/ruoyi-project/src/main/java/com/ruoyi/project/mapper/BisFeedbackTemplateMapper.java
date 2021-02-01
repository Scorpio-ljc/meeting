package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.BisFeedbackTemplate;

/**
 * 反馈模版Mapper接口
 *
 * @author ruoyi
 * @date 2020-12-11
 */
public interface BisFeedbackTemplateMapper
{
    /**
     * 查询反馈模版
     *
     * @param id 反馈模版ID
     * @return 反馈模版
     */
    public BisFeedbackTemplate selectBisFeedbackTemplateById(Long id);

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
    public int insertBisFeedbackTemplate(BisFeedbackTemplate bisFeedbackTemplate);

    /**
     * 修改反馈模版
     *
     * @param bisFeedbackTemplate 反馈模版
     * @return 结果
     */
    public int updateBisFeedbackTemplate(BisFeedbackTemplate bisFeedbackTemplate);

    /**
     * 删除反馈模版
     *
     * @param id 反馈模版ID
     * @return 结果
     */
    public int deleteBisFeedbackTemplateById(Long id);

    /**
     * 批量删除反馈模版
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBisFeedbackTemplateByIds(Long[] ids);

    /**
     * 根据模版编码删除模版
     *
     * @param feedbackCode 模版编码
     * @return 结果
     */
    int deleteByFeedbackCode(String feedbackCode);

    List<BisFeedbackTemplate> getByFeedbackCode(String feedbackCode);
}
