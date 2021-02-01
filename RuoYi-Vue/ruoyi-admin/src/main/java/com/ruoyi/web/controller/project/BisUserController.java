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
import com.ruoyi.project.domain.BisUser;
import com.ruoyi.project.service.IBisUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户Controller
 *
 * @author ruoyi
 * @date 2020-12-11
 */
@RestController
@RequestMapping("/project/user")
public class BisUserController extends BaseController
{
    @Autowired
    private IBisUserService bisUserService;

    /**
     * 查询用户列表
     */
    @PreAuthorize("@ss.hasPermi('project:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(BisUser bisUser)
    {
        startPage();
        List<BisUser> list = bisUserService.selectBisUserList(bisUser);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @PreAuthorize("@ss.hasPermi('project:user:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BisUser bisUser)
    {
        List<BisUser> list = bisUserService.selectBisUserList(bisUser);
        ExcelUtil<BisUser> util = new ExcelUtil<BisUser>(BisUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 获取用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(bisUserService.selectBisUserById(id));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('project:user:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BisUser bisUser)
    {
        return toAjax(bisUserService.insertBisUser(bisUser));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('project:user:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BisUser bisUser)
    {
        return toAjax(bisUserService.updateBisUser(bisUser));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('project:user:remove')")
    @Log(title = "用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bisUserService.deleteBisUserByIds(ids));
    }
}
