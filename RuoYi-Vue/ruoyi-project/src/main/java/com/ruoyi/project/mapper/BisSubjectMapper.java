package com.ruoyi.project.mapper;

import java.util.List;
import com.ruoyi.project.domain.BisSubject;
import com.ruoyi.project.domain.vo.BisSubjectVo;
import org.apache.ibatis.annotations.Param;

/**
 * 题目详情Mapper接口
 *
 * @author ruoyi
 * @date 2020-12-11
 */
public interface BisSubjectMapper
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
    public List<BisSubjectVo> selectBisSubjectVoList(BisSubject bisSubject);

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
     * 删除题目详情
     *
     * @param id 题目详情ID
     * @return 结果
     */
    public int deleteBisSubjectById(Long id);

    /**
     * 批量删除题目详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBisSubjectByIds(Long[] ids);

    /**
     * 批量增加题目
     *
     * @param list
     * @return
     */
    int addList(@Param("list") List<BisSubject> list);

    /**
     * 根据模版code删除
     *
     * @param feedbackCode
     * @return
     */
    int deleteByFeedbackCode(String feedbackCode);

    /**
     * 根据题目返回选项列表
     *
     * @param subjectId
     * @return
     */
    List<BisSubject> getBySubjectId(Long subjectId);
}
