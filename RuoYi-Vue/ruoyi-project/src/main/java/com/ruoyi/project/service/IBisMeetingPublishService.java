package com.ruoyi.project.service;

import java.util.List;
import com.ruoyi.project.domain.BisMeetingPublish;

/**
 * 会议信息发布Service接口
 *
 * @author ruoyi
 * @date 2020-12-19
 */
public interface IBisMeetingPublishService
{
    /**
     * 查询会议信息发布
     *
     * @param id 会议信息发布ID
     * @return 会议信息发布
     */
    public BisMeetingPublish selectBisMeetingPublishById(Long id);

    /**
     * 查询会议信息发布列表
     *
     * @param bisMeetingPublish 会议信息发布
     * @return 会议信息发布集合
     */
    public List<BisMeetingPublish> selectBisMeetingPublishList(BisMeetingPublish bisMeetingPublish);

    /**
     * 新增会议信息发布
     *
     * @param bisMeetingPublish 会议信息发布
     * @return 结果
     */
    void insertBisMeetingPublish(BisMeetingPublish bisMeetingPublish);

    /**
     * 修改会议信息发布
     *
     * @param bisMeetingPublish 会议信息发布
     * @return 结果
     */
    public int updateBisMeetingPublish(BisMeetingPublish bisMeetingPublish);

    /**
     * 批量删除会议信息发布
     *
     * @param ids 需要删除的会议信息发布ID
     * @return 结果
     */
    public int deleteBisMeetingPublishByIds(Long[] ids);

    /**
     * 删除会议信息发布信息
     *
     * @param id 会议信息发布ID
     * @return 结果
     */
    public int deleteBisMeetingPublishById(Long id);
}
