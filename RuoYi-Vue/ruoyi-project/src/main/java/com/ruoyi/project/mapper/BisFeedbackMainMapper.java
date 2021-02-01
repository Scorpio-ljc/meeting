package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.BisFeedbackMain;

/**
 * 反馈模版主Mapper接口
 *
 * @author ruoyi
 * @date 2020-12-29
 */
public interface BisFeedbackMainMapper
{
    /**
     * 查询反馈模版主
     *
     * @param id 反馈模版主ID
     * @return 反馈模版主
     */
    public BisFeedbackMain selectBisFeedbackMainById(Long id);

    /**
     * 查询反馈模版主列表
     *
     * @param bisFeedbackMain 反馈模版主
     * @return 反馈模版主集合
     */
    public List<BisFeedbackMain> selectBisFeedbackMainList(BisFeedbackMain bisFeedbackMain);

    /**
     * 新增反馈模版主
     *
     * @param bisFeedbackMain 反馈模版主
     * @return 结果
     */
    public int insertBisFeedbackMain(BisFeedbackMain bisFeedbackMain);

    /**
     * 修改反馈模版主
     *
     * @param bisFeedbackMain 反馈模版主
     * @return 结果
     */
    public int updateBisFeedbackMain(BisFeedbackMain bisFeedbackMain);

    /**
     * 删除反馈模版主
     *
     * @param id 反馈模版主ID
     * @return 结果
     */
    public int deleteBisFeedbackMainById(Long id);

    /**
     * 批量删除反馈模版主
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBisFeedbackMainByIds(Long[] ids);

    /**
     * 根据反馈模版编码获取模版主表信息
     *
     * @param feedbackCode 模版code
     * @return 结果
     */
    BisFeedbackMain selectBisFeedbackMainByCode(String feedbackCode);
    int deleteBisFeedbackMainByCode(String feedbackCode);
}
