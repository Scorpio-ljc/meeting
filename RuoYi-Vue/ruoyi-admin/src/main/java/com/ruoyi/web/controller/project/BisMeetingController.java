package com.ruoyi.web.controller.project;

import java.util.List;

import com.ruoyi.common.annotation.AnonymousAccess;
import com.ruoyi.common.annotation.RepeatSubmit;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.project.domain.BisMeeting;
import com.ruoyi.project.service.IBisMeetingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 会议信息Controller
 *
 * @author ruoyi
 * @date 2020-12-11
 */
@RestController
@RequestMapping("/api/web/meeting/")
public class BisMeetingController extends BaseController
{
    @Autowired
    private IBisMeetingService bisMeetingService;

    /**
     * 查询会议信息列表
     */
    @ApiOperation("查询会议信息列表")
    @GetMapping("/queryMeetingList")
    @AnonymousAccess
    public TableDataInfo queryMeetingList(BisMeeting bisMeeting)
    {
        startPage();
        List<BisMeeting> list = bisMeetingService.selectBisMeetingList(bisMeeting);
        return getDataTable(list);
    }

    /**
     * 导出会议信息列表
     */
    @PreAuthorize("@ss.hasPermi('project:meeting:export')")
    @Log(title = "会议信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BisMeeting bisMeeting)
    {
        List<BisMeeting> list = bisMeetingService.selectBisMeetingList(bisMeeting);
        ExcelUtil<BisMeeting> util = new ExcelUtil<BisMeeting>(BisMeeting.class);
        return util.exportExcel(list, "meeting");
    }

    /**
     * 获取会议信息详细信息
     */
    @ApiOperation("获取会议信息详细信息根据id")
    @GetMapping("/queryMeetingById")
    @AnonymousAccess
    public AjaxResult queryMeetingById(Long id)
    {
        return AjaxResult.success(bisMeetingService.selectBisMeetingById(id));
    }

    /**
     * 新增会议信息
     */
    @ApiOperation("新增会议信息")
    @Log(title = "会议信息", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/addMeeting")
    public AjaxResult addMeeting(@RequestBody BisMeeting bisMeeting)
    {
        return toAjax(bisMeetingService.insertBisMeeting(bisMeeting));
    }

    /**
     * 修改会议信息
     */
    @ApiOperation("修改会议信息")
    @Log(title = "会议信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateMeeting")
    public AjaxResult updateMeeting(@RequestBody BisMeeting bisMeeting)
    {
        return toAjax(bisMeetingService.updateBisMeeting(bisMeeting));
    }

    /**
     * 删除会议信息
     */
    @ApiOperation("删除会议")
    @Log(title = "会议信息", businessType = BusinessType.DELETE)
	@PostMapping("/deleteMeeting")
    public AjaxResult deleteMeeting(@RequestBody BisMeeting bisMeeting)
    {
        Long[] ids = {bisMeeting.getId()};
        return toAjax(bisMeetingService.deleteBisMeetingByIds(ids));
    }
    @ApiOperation("获取会议二维码")
    @GetMapping("/getQrcode")
    @AnonymousAccess
    public void getQrcode(Long id, HttpServletResponse response){
        if(id == null){
            return;
        }
        bisMeetingService.getQrCode(id,response);
    }
    @ApiOperation("导出用户反馈统计信息")
    @GetMapping("/exportUserFeedback")
    @AnonymousAccess
    public void exportUserFeedback(Long meetingId,HttpServletResponse response){
        BisMeeting bisMeeting = new BisMeeting();
        bisMeeting.setId(meetingId);
        bisMeetingService.exportUserFeedback(bisMeeting,response);
    }

}
