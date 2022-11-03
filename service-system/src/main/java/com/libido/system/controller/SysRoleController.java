package com.libido.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.libido.common.result.Result;
import com.libido.model.system.SysRole;
import com.libido.model.vo.SysPostQueryVo;
import com.libido.model.vo.SysRoleQueryVo;
import com.libido.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    //2、逻辑删除接口
    @ApiOperation(value = "逻辑删除接口")
    @DeleteMapping("remove/{id}")
    public Result removeRole(@PathVariable Long id) {
        //调用
        boolean isSuccess = sysRoleService.removeById(id);
        if (isSuccess == true) {
            return Result.ok();
        } else {
            return Result.fail();
        }

    }

    //http://localhost:8800/admin/system/sysRole/findAll

    //3、查询所有的记录
    @ApiOperation(value = "获取全部角色列表")
    @GetMapping("findAll")
    public Result findAllRole() {
        //调用service
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }


    //3、条件分页查询
    @ApiOperation("条件分页查询")
    //page当前页 limit每页查询数目
    @GetMapping("{page}/{limit}")
    public Result findPageQueryRole(@PathVariable Long page,
                                    @PathVariable Long limit,
                                    SysRoleQueryVo sysRoleQueryVo) {
        //插件page对象
        Page<SysRole> pageParam = new Page<>(page, limit);
        //调用service
        IPage<SysRole> pageModel = sysRoleService.selectPage(pageParam, sysRoleQueryVo);
        //返回
        return Result.ok(pageModel);

    }

    //4、新增角色
    @ApiOperation("新增角色")
    @PostMapping("/save")
    //@RequestBody 不能用get提交
    //传递的是一个json格式数据，将json数据包装封装到对象里
    public Result saveRole(@RequestBody SysRole sysRole) {
        boolean isSuccess = sysRoleService.save(sysRole);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }

    }

    //5、修改-根据id查询
    @ApiOperation("根据id查询")
    @PostMapping("findById/{id}")
    public Result findRoleById(@PathVariable Long id) {
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    //6、最终修改
    @ApiOperation("最终修改")
    @PostMapping("update")
    public Result updateRole(@RequestBody SysRole sysRole) {
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if (isSuccess) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //7、批量删除
    //根据id
    //json数组对应java的list集合
    @ApiOperation("批量删除")
    @DeleteMapping("batchremove")
    public Result batchremove(@RequestBody List<Long> idList) {
      sysRoleService.removeByIds(idList);
        return Result.ok();
    }
}
