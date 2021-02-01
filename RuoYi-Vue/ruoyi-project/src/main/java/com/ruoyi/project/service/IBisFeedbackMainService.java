package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.BisFeedbackMain;

/**
 * 反馈模版主Service接口
 * 
 * @author ruoyi
 * @date 2020-12-29
 */
public interface IBisFeedbackMainService 
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
     * 批量删除反馈模版主
     * 
     * @param ids 需要删除的反馈模版主ID
     * @return 结果
     */
    public int deleteBisFeedbackMainByIds(Long[] ids);

    /**
     * 删除反馈模版主信息
     * 
     * @param id 反馈模版主ID
     * @return 结果
     */
    public int deleteBisFeedbackMainById(Long id);
}
