package com.ruoyi.project.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.BisSubjectMapper;
import com.ruoyi.project.domain.BisSubject;
import com.ruoyi.project.service.IBisSubjectService;

/**
 * 题目详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-12-11
 */
@Service
public class BisSubjectServiceImpl implements IBisSubjectService 
{
    @Autowired
    private BisSubjectMapper bisSubjectMapper;

    /**
     * 查询题目详情
     * 
     * @param id 题目详情ID
     * @return 题目详情
     */
    @Override
    public BisSubject selectBisSubjectById(Long id)
    {
        return bisSubjectMapper.selectBisSubjectById(id);
    }

    /**
     * 查询题目详情列表
     * 
     * @param bisSubject 题目详情
     * @return 题目详情
     */
    @Override
    public List<BisSubject> selectBisSubjectList(BisSubject bisSubject)
    {
        return bisSubjectMapper.selectBisSubjectList(bisSubject);
    }

    /**
     * 新增题目详情
     * 
     * @param bisSubject 题目详情
     * @return 结果
     */
    @Override
    public int insertBisSubject(BisSubject bisSubject)
    {
        bisSubject.setCreateTime(DateUtils.getNowDate());
        return bisSubjectMapper.insertBisSubject(bisSubject);
    }

    /**
     * 修改题目详情
     * 
     * @param bisSubject 题目详情
     * @return 结果
     */
    @Override
    public int updateBisSubject(BisSubject bisSubject)
    {
        bisSubject.setUpdateTime(DateUtils.getNowDate());
        return bisSubjectMapper.updateBisSubject(bisSubject);
    }

    /**
     * 批量删除题目详情
     * 
     * @param ids 需要删除的题目详情ID
     * @return 结果
     */
    @Override
    public int deleteBisSubjectByIds(Long[] ids)
    {
        return bisSubjectMapper.deleteBisSubjectByIds(ids);
    }

    /**
     * 删除题目详情信息
     * 
     * @param id 题目详情ID
     * @return 结果
     */
    @Override
    public int deleteBisSubjectById(Long id)
    {
        return bisSubjectMapper.deleteBisSubjectById(id);
    }
}
