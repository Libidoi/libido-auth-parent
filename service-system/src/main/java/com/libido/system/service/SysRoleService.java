package com.libido.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.libido.model.system.SysRole;
import com.libido.model.vo.SysPostQueryVo;
import com.libido.model.vo.SysRoleQueryVo;

public interface SysRoleService extends IService<SysRole> {
    //条件分页查询
    IPage<SysRole> selectPage(Page<SysRole> pageParm, SysRoleQueryVo sysRoleQueryVo);

    
}
