package com.ruoyi.web.controller.project;

import java.util.List;

import com.ruoyi.common.annotation.AnonymousAccess;
import com.ruoyi.project.domain.vo.BisSignInVo;
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
import com.ruoyi.project.domain.BisSignIn;
import com.ruoyi.project.service.IBisSignInService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 签到信息Controller
 *
 * @author ruoyi
 * @date 2020-12-11
 */
@RestController
@RequestMapping("/api/web/signIn")
public class BisSignInController extends BaseController
{
    @Autowired
    private IBisSignInService bisSignInService;

    /**
     * 查询签到信息列表
     */
    @ApiOperation("查询签到信息列表")
    @GetMapping("/querySignInList")
    public TableDataInfo list(BisSignInVo bisSignInVo)
    {
        startPage();
        List<BisSignInVo> list = bisSignInService.selectBisSignInList(bisSignInVo);
        return getDataTable(list);
    }

    /**
     * 导出签到信息列表
     */
    @ApiOperation("导出签到列表")
    @Log(title = "签到信息", businessType = BusinessType.EXPORT)
    @AnonymousAccess
    @GetMapping("/exportSignInList")
    public AjaxResult exportSignInList(BisSignInVo bisSignInVo, HttpServletResponse response)
    {
        List<BisSignInVo> list = bisSignInService.selectBisSignInList(bisSignInVo);
        ExcelUtil<BisSignInVo> util = new ExcelUtil<>(BisSignInVo.class);
        return util.exportExcel(list, "in",response);
    }

    /**
     * 获取签到信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:in:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bisSignInService.selectBisSignInById(id));
    }

    /**
     * 新增签到信息
     */
    @PreAuthorize("@ss.hasPermi('project:in:add')")
    @Log(title = "签到信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BisSignIn bisSignIn)
    {
        return toAjax(bisSignInService.insertBisSignIn(bisSignIn));
    }

    /**
     * 修改签到信息
     */
    @PreAuthorize("@ss.hasPermi('project:in:edit')")
    @Log(title = "签到信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BisSignIn bisSignIn)
    {
        return toAjax(bisSignInService.updateBisSignIn(bisSignIn));
    }

    /**
     * 删除签到信息
     */
    @PreAuthorize("@ss.hasPermi('project:in:remove')")
    @Log(title = "签到信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bisSignInService.deleteBisSignInByIds(ids));
    }
}
