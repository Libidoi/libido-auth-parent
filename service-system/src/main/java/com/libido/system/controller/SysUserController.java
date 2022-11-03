package com.libido.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.libido.common.result.Result;
import com.libido.model.system.SysRole;
import com.libido.model.system.SysUser;
import com.libido.model.vo.SysUserQueryVo;
import com.libido.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-10-30
 */

@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService SysUserService;

    @ApiOperation("用户列表")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Long page
            , @PathVariable Long limit
            , SysUserQueryVo sysUserQueryVo) {
        //创建page对象
        Page<SysUser> pageParm = new Page<>(page, limit);
        //调用serverce
        IPage<SysUser> pageModel = SysUserService.selectPage(pageParm, sysUserQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result save(@RequestBody SysUser user) {
        boolean is_Success = SysUserService.save(user);
        if (is_Success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //修改
    //1、先查询
    @ApiOperation("根据id查询")
    @GetMapping("getUser/{id}")
    public Result getUser(@PathVariable String id) {
        SysUser user = SysUserService.getById(id);
        return Result.ok(user);
    }

    //2、 修改
    @ApiOperation("修改用户")
    @PostMapping("update")
    public Result update(@RequestBody SysUser user) {
        boolean is_Success = SysUserService.updateById(user);
        if (is_Success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //删除
    @ApiOperation("删除用户")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean is_Success = SysUserService.removeById(id);
        if (is_Success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //用户状态
    @ApiOperation("更新状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id,
                               @PathVariable Integer status) {
        SysUserService.updateStatus(id, status);
        return Result.ok();
    }
}

