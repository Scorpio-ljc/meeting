package com.ruoyi.project.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.BisFeedbackMainMapper;
import com.ruoyi.project.domain.BisFeedbackMain;
import com.ruoyi.project.service.IBisFeedbackMainService;

/**
 * 反馈模版主Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-12-29
 */
@Service
public class BisFeedbackMainServiceImpl implements IBisFeedbackMainService 
{
    @Autowired
    private BisFeedbackMainMapper bisFeedbackMainMapper;

    /**
     * 查询反馈模版主
     * 
     * @param id 反馈模版主ID
     * @return 反馈模版主
     */
    @Override
    public BisFeedbackMain selectBisFeedbackMainById(Long id)
    {
        return bisFeedbackMainMapper.selectBisFeedbackMainById(id);
    }

    /**
     * 查询反馈模版主列表
     * 
     * @param bisFeedbackMain 反馈模版主
     * @return 反馈模版主
     */
    @Override
    public List<BisFeedbackMain> selectBisFeedbackMainList(BisFeedbackMain bisFeedbackMain)
    {
        return bisFeedbackMainMapper.selectBisFeedbackMainList(bisFeedbackMain);
    }

    /**
     * 新增反馈模版主
     * 
     * @param bisFeedbackMain 反馈模版主
     * @return 结果
     */
    @Override
    public int insertBisFeedbackMain(BisFeedbackMain bisFeedbackMain)
    {
        bisFeedbackMain.setCreateTime(DateUtils.getNowDate());
        return bisFeedbackMainMapper.insertBisFeedbackMain(bisFeedbackMain);
    }

    /**
     * 修改反馈模版主
     * 
     * @param bisFeedbackMain 反馈模版主
     * @return 结果
     */
    @Override
    public int updateBisFeedbackMain(BisFeedbackMain bisFeedbackMain)
    {
        bisFeedbackMain.setUpdateTime(DateUtils.getNowDate());
        return bisFeedbackMainMapper.updateBisFeedbackMain(bisFeedbackMain);
    }

    /**
     * 批量删除反馈模版主
     * 
     * @param ids 需要删除的反馈模版主ID
     * @return 结果
     */
    @Override
    public int deleteBisFeedbackMainByIds(Long[] ids)
    {
        return bisFeedbackMainMapper.deleteBisFeedbackMainByIds(ids);
    }

    /**
     * 删除反馈模版主信息
     * 
     * @param id 反馈模版主ID
     * @return 结果
     */
    @Override
    public int deleteBisFeedbackMainById(Long id)
    {
        return bisFeedbackMainMapper.deleteBisFeedbackMainById(id);
    }
}
