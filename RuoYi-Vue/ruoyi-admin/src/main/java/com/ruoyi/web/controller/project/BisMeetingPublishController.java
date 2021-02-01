package com.ruoyi.web.controller.project;

import java.util.List;

import com.ruoyi.common.annotation.AnonymousAccess;
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
import com.ruoyi.project.domain.BisMeetingPublish;
import com.ruoyi.project.service.IBisMeetingPublishService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会议信息发布Controller
 *
 * @author ruoyi
 * @date 2020-12-19
 */
@RestController
@RequestMapping("/api/web/meetingPublish")
public class BisMeetingPublishController extends BaseController
{
    @Autowired
    private IBisMeetingPublishService bisMeetingPublishService;

    /**
     * 查询会议信息发布列表
     */
    @PreAuthorize("@ss.hasPermi('project:publish:list')")
    @GetMapping("/list")
    @AnonymousAccess
    public TableDataInfo list(BisMeetingPublish bisMeetingPublish)
    {
        startPage();
        List<BisMeetingPublish> list = bisMeetingPublishService.selectBisMeetingPublishList(bisMeetingPublish);
        return getDataTable(list);
    }

    /**
     * 导出会议信息发布列表
     */
    @PreAuthorize("@ss.hasPermi('project:publish:export')")
    @Log(title = "会议信息发布", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @AnonymousAccess
    public AjaxResult export(BisMeetingPublish bisMeetingPublish)
    {
        List<BisMeetingPublish> list = bisMeetingPublishService.selectBisMeetingPublishList(bisMeetingPublish);
        ExcelUtil<BisMeetingPublish> util = new ExcelUtil<BisMeetingPublish>(BisMeetingPublish.class);
        return util.exportExcel(list, "publish");
    }

    /**
     * 获取会议信息发布详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:publish:query')")
    @GetMapping(value = "/{id}")
    @AnonymousAccess
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bisMeetingPublishService.selectBisMeetingPublishById(id));
    }

    /**
     * 新增会议信息发布
     */
    @PreAuthorize("@ss.hasPermi('project:publish:add')")
    @Log(title = "会议信息发布", businessType = BusinessType.INSERT)
    @PostMapping("/addMeetingPublish")
    public AjaxResult aaddMeetingPublishdd(@RequestBody BisMeetingPublish bisMeetingPublish)
    {
        bisMeetingPublishService.insertBisMeetingPublish(bisMeetingPublish);
        return AjaxResult.success();
    }

    /**
     * 修改会议信息发布
     */
    @PreAuthorize("@ss.hasPermi('project:publish:edit')")
    @Log(title = "会议信息发布", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BisMeetingPublish bisMeetingPublish)
    {
        return toAjax(bisMeetingPublishService.updateBisMeetingPublish(bisMeetingPublish));
    }

    /**
     * 删除会议信息发布
     */
    @PreAuthorize("@ss.hasPermi('project:publish:remove')")
    @Log(title = "会议信息发布", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bisMeetingPublishService.deleteBisMeetingPublishByIds(ids));
    }
}
