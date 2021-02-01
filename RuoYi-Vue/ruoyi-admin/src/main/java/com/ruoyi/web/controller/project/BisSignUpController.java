package com.ruoyi.web.controller.project;

import java.util.List;

import com.ruoyi.common.annotation.AnonymousAccess;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.domain.vo.BisSignUpExcelVo;
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
import com.ruoyi.project.domain.BisSignUp;
import com.ruoyi.project.service.IBisSignUpService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletResponse;

/**
 * 会议报名信息Controller
 *
 * @author ruoyi
 * @date 2020-12-11
 */
@RestController
@RequestMapping("/api/web/signUp")
public class BisSignUpController extends BaseController {
    @Autowired
    private IBisSignUpService bisSignUpService;

    /**
     * 查询会议报名信息列表
     */
    @ApiOperation("查询会议报名信息列表")
    @GetMapping("/querySignUpList")
    public TableDataInfo querysignUpList(BisSignUp bisSignUp) {
        startPage();
        List<BisSignUp> list = bisSignUpService.selectBisSignUpList(bisSignUp);
        return getDataTable(list);
    }

    /**
     * 导出会议报名信息列表
     */
    @ApiOperation("导出会议报名信息列表")
    @Log(title = "会议报名信息", businessType = BusinessType.EXPORT)
    @GetMapping("/exportSignUpList")
    @AnonymousAccess
    public AjaxResult export(BisSignUp bisSignUp, HttpServletResponse response) {
        List<BisSignUpExcelVo> list = bisSignUpService.selectBisSignUpExcelVoList(bisSignUp);
        ExcelUtil<BisSignUpExcelVo> util = new ExcelUtil<>(BisSignUpExcelVo.class);
        return util.exportExcel(list, "up",response);
    }

    /**
     * 获取会议报名信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:up:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(bisSignUpService.selectBisSignUpById(id));
    }

    /**
     * 新增会议报名信息
     */
    @PreAuthorize("@ss.hasPermi('project:up:add')")
    @Log(title = "会议报名信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BisSignUp bisSignUp) {
        return toAjax(bisSignUpService.insertBisSignUp(bisSignUp));
    }

    /**
     * 修改会议报名信息
     */
    @PreAuthorize("@ss.hasPermi('project:up:edit')")
    @Log(title = "会议报名信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BisSignUp bisSignUp) {
        return toAjax(bisSignUpService.updateBisSignUp(bisSignUp));
    }

    /**
     * 删除会议报名信息
     */
    @PreAuthorize("@ss.hasPermi('project:up:remove')")
    @Log(title = "会议报名信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(bisSignUpService.deleteBisSignUpByIds(ids));
    }

    /**
     * 报名审核
     *
     * @param bisSignUp 审核对象
     * @return 结果
     */
    @PostMapping("/audit")
    public AjaxResult audit(@RequestBody BisSignUp bisSignUp) {
        if (StringUtils.isEmpty(bisSignUp.getState()) || bisSignUp.getId() == null) {
            return AjaxResult.error("参数异常");
        }
        return AjaxResult.success(bisSignUpService.audit(bisSignUp));
    }
}
