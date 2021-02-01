package com.ruoyi.web.controller.project;

import java.util.List;
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
import com.ruoyi.project.domain.BisUserFeedback;
import com.ruoyi.project.service.IBisUserFeedbackService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户反馈Controller
 *
 * @author ruoyi
 * @date 2020-12-11
 */
@RestController
@RequestMapping("/project/feedback")
public class BisUserFeedbackController extends BaseController
{
    @Autowired
    private IBisUserFeedbackService bisUserFeedbackService;

    /**
     * 查询用户反馈列表
     */
    @PreAuthorize("@ss.hasPermi('project:feedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(BisUserFeedback bisUserFeedback)
    {
        startPage();
        List<BisUserFeedback> list = bisUserFeedbackService.selectBisUserFeedbackList(bisUserFeedback);
        return getDataTable(list);
    }

    /**
     * 导出用户反馈列表
     */
    @PreAuthorize("@ss.hasPermi('project:feedback:export')")
    @Log(title = "用户反馈", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BisUserFeedback bisUserFeedback)
    {
        List<BisUserFeedback> list = bisUserFeedbackService.selectBisUserFeedbackList(bisUserFeedback);
        ExcelUtil<BisUserFeedback> util = new ExcelUtil<BisUserFeedback>(BisUserFeedback.class);
        return util.exportExcel(list, "feedback");
    }

    /**
     * 获取用户反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:feedback:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bisUserFeedbackService.selectBisUserFeedbackById(id));
    }

    /**
     * 新增用户反馈
     */
    @PreAuthorize("@ss.hasPermi('project:feedback:add')")
    @Log(title = "用户反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BisUserFeedback bisUserFeedback)
    {
        return toAjax(bisUserFeedbackService.insertBisUserFeedback(bisUserFeedback));
    }

    /**
     * 修改用户反馈
     */
    @PreAuthorize("@ss.hasPermi('project:feedback:edit')")
    @Log(title = "用户反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BisUserFeedback bisUserFeedback)
    {
        return toAjax(bisUserFeedbackService.updateBisUserFeedback(bisUserFeedback));
    }

    /**
     * 删除用户反馈
     */
    @PreAuthorize("@ss.hasPermi('project:feedback:remove')")
    @Log(title = "用户反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bisUserFeedbackService.deleteBisUserFeedbackByIds(ids));
    }

}
