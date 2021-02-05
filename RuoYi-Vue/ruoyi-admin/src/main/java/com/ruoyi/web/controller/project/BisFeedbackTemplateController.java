package com.ruoyi.web.controller.project;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.project.domain.BisFeedbackMain;
import com.ruoyi.project.domain.vo.BisFeedbackTemplateDetailVo;
import com.ruoyi.project.domain.vo.BisFeedbackTemplateVo;
import com.ruoyi.project.service.IBisFeedbackMainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.project.domain.BisFeedbackTemplate;
import com.ruoyi.project.service.IBisFeedbackTemplateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 反馈模版Controller
 *
 * @author ruoyi
 * @date 2020-12-11
 */
@Api("反馈模版")
@RestController
@RequestMapping("/api/web/feedback")
public class BisFeedbackTemplateController extends BaseController
{
    @Autowired
    private IBisFeedbackTemplateService bisFeedbackTemplateService;
    @Autowired
    private IBisFeedbackMainService bisFeedbackMainService;

    /**
     * 查询反馈模版列表
     */
    @GetMapping("/queryFeedbackList")
    @ApiOperation("查询反馈模版列表")
    public TableDataInfo queryFeedbackList(BisFeedbackMain bisFeedbackMain)
    {
        startPage();
        List<BisFeedbackMain> list = bisFeedbackMainService.selectBisFeedbackMainList(bisFeedbackMain);
        return getDataTable(list);
    }

    /**
     * 导出反馈模版列表
     */
    @Log(title = "反馈模版", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BisFeedbackTemplate bisFeedbackTemplate)
    {
        List<BisFeedbackTemplate> list = bisFeedbackTemplateService.selectBisFeedbackTemplateList(bisFeedbackTemplate);
        ExcelUtil<BisFeedbackTemplate> util = new ExcelUtil<BisFeedbackTemplate>(BisFeedbackTemplate.class);
        return util.exportExcel(list, "template");
    }

    /**
     * 获取反馈模版详细信息
     */
    @GetMapping("/queryFeedback")
    public AjaxResult getInfo(String feedbackCode)
    {
        return AjaxResult.success(bisFeedbackTemplateService.selectBisFeedbackTemplateByFeedbackCode(feedbackCode));
    }

    /**
     * 新增反馈模版
     */
    @ApiOperation("新增反馈模版")
    @Log(title = "反馈模版", businessType = BusinessType.INSERT)
    @RepeatSubmit
    @PostMapping("/addFeedback")
    public AjaxResult addFeedback(@RequestBody BisFeedbackTemplateDetailVo bisFeedbackTemplateDetailVo)
    {
        return toAjax(bisFeedbackTemplateService.addBisFeedbackTemplate(bisFeedbackTemplateDetailVo));
    }

    /**
     * 修改反馈模版
     */
    @ApiOperation("修改反馈模版")
    @Log(title = "反馈模版", businessType = BusinessType.UPDATE)
    @PostMapping("/updateFeedback")
    public AjaxResult updateFeedback(@RequestBody BisFeedbackTemplateDetailVo bisFeedbackTemplateUpdateVo)
    {
        return toAjax(bisFeedbackTemplateService.updateBisFeedbackTemplate(bisFeedbackTemplateUpdateVo));
    }

    /**
     * 删除反馈模版
     */
    @PreAuthorize("@ss.hasPermi('project:template:remove')")
    @Log(title = "反馈模版", businessType = BusinessType.DELETE)
	@PostMapping("/deleteFeedback")
    public AjaxResult remove(@RequestBody BisFeedbackTemplate bisFeedbackTemplate)
    {
        return toAjax(bisFeedbackTemplateService.deleteByCode(bisFeedbackTemplate.getFeedbackCode()));
    }
}
