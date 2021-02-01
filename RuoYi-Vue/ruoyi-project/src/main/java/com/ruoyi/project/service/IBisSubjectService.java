package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.BisSubject;

/**
 * 题目详情Service接口
 * 
 * @author ruoyi
 * @date 2020-12-11
 */
public interface IBisSubjectService 
{
    /**
     * 查询题目详情
     * 
     * @param id 题目详情ID
     * @return 题目详情
     */
    public BisSubject selectBisSubjectById(Long id);

    /**
     * 查询题目详情列表
     * 
     * @param bisSubject 题目详情
     * @return 题目详情集合
     */
    public List<BisSubject> selectBisSubjectList(BisSubject bisSubject);

    /**
     * 新增题目详情
     * 
     * @param bisSubject 题目详情
     * @return 结果
     */
    public int insertBisSubject(BisSubject bisSubject);

    /**
     * 修改题目详情
     * 
     * @param bisSubject 题目详情
     * @return 结果
     */
    public int updateBisSubject(BisSubject bisSubject);

    /**
     * 批量删除题目详情
     * 
     * @param ids 需要删除的题目详情ID
     * @return 结果
     */
    public int deleteBisSubjectByIds(Long[] ids);

    /**
     * 删除题目详情信息
     * 
     * @param id 题目详情ID
     * @return 结果
     */
    public int deleteBisSubjectById(Long id);
}
