package com.ruoyi.project.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.domain.BisMeeting;
import com.ruoyi.project.domain.BisUser;
import com.ruoyi.project.domain.vo.BisSignUpExcelVo;
import com.ruoyi.project.domain.vo.BisUserVo;
import com.ruoyi.project.mapper.BisMeetingMapper;
import com.ruoyi.project.mapper.BisUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.mapper.BisSignUpMapper;
import com.ruoyi.project.domain.BisSignUp;
import com.ruoyi.project.service.IBisSignUpService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会议报名信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BisSignUpServiceImpl implements IBisSignUpService {
    @Autowired
    private BisSignUpMapper bisSignUpMapper;
    @Autowired
    private BisUserMapper bisUserMapper;
    @Autowired
    private BisMeetingMapper bisMeetingMapper;

    /**
     * 查询会议报名信息
     *
     * @param id 会议报名信息ID
     * @return 会议报名信息
     */
    @Override
    public BisSignUp selectBisSignUpById(Long id) {
        return bisSignUpMapper.selectBisSignUpById(id);
    }

    /**
     * 查询会议报名信息列表
     *
     * @param bisSignUp 会议报名信息
     * @return 会议报名信息
     */
    @Override
    public List<BisSignUp> selectBisSignUpList(BisSignUp bisSignUp) {
        return bisSignUpMapper.selectBisSignUpList(bisSignUp);
    }

    @Override
    public List<BisSignUpExcelVo> selectBisSignUpExcelVoList(BisSignUp bisSignUp) {
        return bisSignUpMapper.selectBisSignUpExcelList(bisSignUp);
    }

    /**
     * 新增会议报名信息
     *
     * @param bisSignUp 会议报名信息
     * @return 结果
     */
    @Override
    public int insertBisSignUp(BisSignUp bisSignUp) {
        bisSignUp.setCreateTime(DateUtils.getNowDate());
        return bisSignUpMapper.insertBisSignUp(bisSignUp);
    }

    /**
     * 修改会议报名信息
     *
     * @param bisSignUp 会议报名信息
     * @return 结果
     */
    @Override
    public int updateBisSignUp(BisSignUp bisSignUp) {
        bisSignUp.setUpdateTime(DateUtils.getNowDate());
        return bisSignUpMapper.updateBisSignUp(bisSignUp);
    }

    /**
     * 批量删除会议报名信息
     *
     * @param ids 需要删除的会议报名信息ID
     * @return 结果
     */
    @Override
    public int deleteBisSignUpByIds(Long[] ids) {
        return bisSignUpMapper.deleteBisSignUpByIds(ids);
    }

    /**
     * 删除会议报名信息信息
     *
     * @param id 会议报名信息ID
     * @return 结果
     */
    @Override
    public int deleteBisSignUpById(Long id) {
        return bisSignUpMapper.deleteBisSignUpById(id);
    }

    @Override
    public boolean signUp(BisUserVo bisUserVo) {
        BisUser bisUser = new BisUser();
        BisMeeting bisMeeting = bisMeetingMapper.selectBisMeetingById(bisUserVo.getMeetingId());
        if (bisMeeting == null){
            return false;
        }
        BeanUtils.copyProperties(bisUserVo, bisUser);
        BisUser bisUserDb = bisUserMapper.selectBisUserById(bisUserVo.getUid());
        if(bisUserDb != null&& !StringUtils.isEmpty(bisUser.getName())){
            bisUserMapper.updateBisUser(bisUser);
        }
        BisSignUp bisSignUp = new BisSignUp();
        BeanUtils.copyProperties(bisUserVo, bisSignUp);
        bisSignUp.setUid(bisUserVo.getUid());
        bisSignUp.setMeetingTitle(bisMeeting.getMeetingTitle());
        bisSignUp.setCreateTime(DateUtils.getNowDate());
        bisSignUpMapper.insertBisSignUp(bisSignUp);
        return true;
    }



    @Override
    public boolean audit(BisSignUp bisSignUp) {
        BisSignUp param = new BisSignUp();
        param.setState(bisSignUp.getState());
        param.setId(bisSignUp.getId());
        int i = bisSignUpMapper.updateBisSignUp(param);
        return i > 0;
    }

    @Override
    public BisSignUp selectNewBisSignUpByUid(Long uid) {
        return bisSignUpMapper.selectNewBisSignUpByUid(uid);
    }
}
