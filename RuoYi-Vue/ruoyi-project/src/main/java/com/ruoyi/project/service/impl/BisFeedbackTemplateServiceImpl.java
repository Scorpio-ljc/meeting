package com.ruoyi.project.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.GenUtil;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.project.domain.BisFeedbackMain;
import com.ruoyi.project.domain.BisSubject;
import com.ruoyi.project.domain.BisUserFeedback;
import com.ruoyi.project.domain.vo.BisFeedbackTemplateDetailVo;
import com.ruoyi.project.domain.vo.BisFeedbackTemplateVo;
import com.ruoyi.project.domain.vo.BisSubjectVo;
import com.ruoyi.project.mapper.BisFeedbackMainMapper;
import com.ruoyi.project.mapper.BisSubjectMapper;
import com.ruoyi.project.mapper.BisUserFeedbackMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.BisFeedbackTemplateMapper;
import com.ruoyi.project.domain.BisFeedbackTemplate;
import com.ruoyi.project.service.IBisFeedbackTemplateService;

/**
 * 反馈模版Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-11
 */
@Service
public class BisFeedbackTemplateServiceImpl implements IBisFeedbackTemplateService {
    @Autowired
    private BisFeedbackTemplateMapper bisFeedbackTemplateMapper;
    @Autowired
    private BisSubjectMapper bisSubjectMapper;
    @Autowired
    private BisUserFeedbackMapper bisUserFeedbackMapper;
    @Autowired
    private BisFeedbackMainMapper bisFeedbackMainMapper;

    /**
     * 查询反馈模版
     *
     * @param id 反馈模版ID
     * @return 反馈模版
     */
    @Override
    public BisFeedbackTemplate selectBisFeedbackTemplateById(Long id) {
        return bisFeedbackTemplateMapper.selectBisFeedbackTemplateById(id);
    }

    @Override
    public BisFeedbackTemplateDetailVo selectBisFeedbackTemplateByFeedbackCode(String feedbackCode) {
        BisFeedbackTemplateDetailVo bisFeedbackTemplateDetailVo = new BisFeedbackTemplateDetailVo();
        bisFeedbackTemplateDetailVo.setFeedbackCode(feedbackCode);
        List<BisFeedbackTemplate> byFeedbackCode = bisFeedbackTemplateMapper.getByFeedbackCode(feedbackCode);
        List<BisFeedbackTemplateVo> collect = byFeedbackCode.stream().map(a -> {
            BisFeedbackTemplateVo bisFeedbackTemplateVo = new BisFeedbackTemplateVo();
            bisFeedbackTemplateVo.setFeedbackCode(feedbackCode);
            bisFeedbackTemplateVo.setSubject(a.getSubject());
            List<BisSubject> bySubjectId = bisSubjectMapper.getBySubjectId(a.getId());
            List<BisSubjectVo> bisSubjectVoList = BeanUtils.copyList(bySubjectId, BisSubjectVo.class);
            bisFeedbackTemplateVo.setOptionList(bisSubjectVoList);
            return bisFeedbackTemplateVo;
        }).collect(Collectors.toList());
        bisFeedbackTemplateDetailVo.setList(collect);
        BisFeedbackMain bisFeedbackMain = bisFeedbackMainMapper.selectBisFeedbackMainByCode(feedbackCode);
        if (bisFeedbackMain != null) {
            bisFeedbackTemplateDetailVo.setTemplateName(bisFeedbackMain.getTemplateName());
        }
        return bisFeedbackTemplateDetailVo;
    }

    @Override
    public BisFeedbackTemplateDetailVo selectBisFeedbackTemplateByFeedbackCode(Long meetingId, String feedbackCode, Long uid) {
        BisFeedbackTemplateDetailVo bisFeedbackTemplateDetailVo = new BisFeedbackTemplateDetailVo();
        bisFeedbackTemplateDetailVo.setFeedbackCode(feedbackCode);
        BisUserFeedback bisUserFeedback = new BisUserFeedback();
        bisUserFeedback.setUid(uid);
        bisUserFeedback.setMeetingId(meetingId);
        List<BisFeedbackTemplate> byFeedbackCode = bisFeedbackTemplateMapper.getByFeedbackCode(feedbackCode);
        List<BisFeedbackTemplateVo> collect = byFeedbackCode.stream().map(a -> {
            BisFeedbackTemplateVo bisFeedbackTemplateVo = new BisFeedbackTemplateVo();
            bisFeedbackTemplateVo.setFeedbackCode(feedbackCode);
            bisFeedbackTemplateVo.setSubject(a.getSubject());
            List<BisUserFeedback> bisUserFeedbackList = bisUserFeedbackMapper.selectBisUserFeedbackList(bisUserFeedback);
            List<BisSubjectVo> bisSubjectVoList = null;
            if (CollectionUtils.isNotEmpty(bisUserFeedbackList)) {
                bisSubjectVoList = BeanUtils.copyList(bisUserFeedbackList, BisSubjectVo.class);
            } else {
                List<BisSubject> bySubjectId = bisSubjectMapper.getBySubjectId(a.getId());
                bisSubjectVoList = BeanUtils.copyList(bySubjectId, BisSubjectVo.class);
            }
            bisFeedbackTemplateVo.setOptionList(bisSubjectVoList);
            return bisFeedbackTemplateVo;
        }).collect(Collectors.toList());
        bisFeedbackTemplateDetailVo.setList(collect);
        return bisFeedbackTemplateDetailVo;
    }

    /**
     * 查询反馈模版列表
     *
     * @param bisFeedbackTemplate 反馈模版
     * @return 反馈模版
     */
    @Override
    public List<BisFeedbackTemplate> selectBisFeedbackTemplateList(BisFeedbackTemplate bisFeedbackTemplate) {
        return bisFeedbackTemplateMapper.selectBisFeedbackTemplateList(bisFeedbackTemplate);
    }

    /**
     * 新增反馈模版
     *
     * @param bisFeedbackTemplate 反馈模版
     * @return 结果
     */
    @Override
    public int insertBisFeedbackTemplate(BisFeedbackTemplate bisFeedbackTemplate) {
        bisFeedbackTemplate.setCreateTime(DateUtils.getNowDate());
        return bisFeedbackTemplateMapper.insertBisFeedbackTemplate(bisFeedbackTemplate);
    }

    @Override
    public int addBisFeedbackTemplate(List<BisFeedbackTemplateVo> bisFeedbackTemplateList) {
        Date createTime = bisFeedbackTemplateList.get(0).getOptionList().get(0).getCreateTime();
        final Date currentDate = createTime == null ? new Date() : createTime;
        final String code = StringUtils.isEmpty(bisFeedbackTemplateList.get(0).getFeedbackCode()) ? GenUtil.generateFeedbackCode() : bisFeedbackTemplateList.get(0).getFeedbackCode();
        long count = bisFeedbackTemplateList.stream().map(a -> {
            BisFeedbackTemplate bisFeedbackTemplate = new BisFeedbackTemplate();
            bisFeedbackTemplate.setCreateBy(SecurityUtils.getUsername());
            bisFeedbackTemplate.setCreateTime(currentDate);
            bisFeedbackTemplate.setSubject(a.getSubject());
            bisFeedbackTemplate.setFeedbackCode(code);
            bisFeedbackTemplateMapper.insertBisFeedbackTemplate(bisFeedbackTemplate);
            List<BisSubject> collect = a.getOptionList().stream().map(b -> {
                BisSubject bisSubject = new BisSubject();
                bisSubject.setFeedbackCode(code);
                bisSubject.setSubjectId(bisFeedbackTemplate.getId());
                bisSubject.setKey(b.getKey());
                bisSubject.setValue(b.getValue());
                bisSubject.setCreateTime(currentDate);
                bisSubject.setCreateBy(SecurityUtils.getUsername());
                return bisSubject;
            }).collect(Collectors.toList());
            bisSubjectMapper.addList(collect);
            return bisFeedbackTemplate;
        }).count();
        return Integer.parseInt(String.valueOf(count));
    }

    @Override
    public int addBisFeedbackTemplate(BisFeedbackTemplateDetailVo bisFeedbackTemplateDetailVo) {
        BisFeedbackMain bisFeedbackMain = new BisFeedbackMain();
        bisFeedbackMain.setFeedbackCode(GenUtil.generateFeedbackCode());
        bisFeedbackMain.setTemplateName(bisFeedbackTemplateDetailVo.getTemplateName());
        bisFeedbackMain.setCreateTime(new Date());
        int i = bisFeedbackMainMapper.insertBisFeedbackMain(bisFeedbackMain);
        if (i > 0 && CollectionUtils.isNotEmpty(bisFeedbackTemplateDetailVo.getList())) {
            bisFeedbackTemplateDetailVo.getList().get(0).setFeedbackCode(bisFeedbackMain.getFeedbackCode());
            i = addBisFeedbackTemplate(bisFeedbackTemplateDetailVo.getList());
        }
        return i;
    }

    /**
     * 修改反馈模版
     *
     * @param bisFeedbackTemplateUpdateVo 反馈模版
     * @return 结果
     */
    @Override
    public int updateBisFeedbackTemplate(BisFeedbackTemplateDetailVo bisFeedbackTemplateUpdateVo) {
        bisFeedbackTemplateMapper.deleteByFeedbackCode(bisFeedbackTemplateUpdateVo.getFeedbackCode());
        BisFeedbackMain param = new BisFeedbackMain();
        param.setFeedbackCode(bisFeedbackTemplateUpdateVo.getFeedbackCode());
        param.setTemplateName(bisFeedbackTemplateUpdateVo.getTemplateName());
        bisFeedbackMainMapper.updateBisFeedbackMain(param);
        return addBisFeedbackTemplate(bisFeedbackTemplateUpdateVo.getList());
    }

    /**
     * 批量删除反馈模版
     *
     * @param ids 需要删除的反馈模版ID
     * @return 结果
     */
    @Override
    public int deleteBisFeedbackTemplateByIds(Long[] ids) {
        return bisFeedbackTemplateMapper.deleteBisFeedbackTemplateByIds(ids);
    }

    /**
     * 删除反馈模版信息
     *
     * @param id 反馈模版ID
     * @return 结果
     */
    @Override
    public int deleteBisFeedbackTemplateById(Long id) {
        return bisFeedbackTemplateMapper.deleteBisFeedbackTemplateById(id);
    }

    @Override
    public int deleteByCode(String feedbackCode) {
        bisFeedbackTemplateMapper.deleteByFeedbackCode(feedbackCode);
        return bisFeedbackMainMapper.deleteBisFeedbackMainByCode(feedbackCode);
    }
}
