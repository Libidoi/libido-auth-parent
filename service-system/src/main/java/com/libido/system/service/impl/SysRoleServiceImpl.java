package com.libido.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.libido.model.system.SysRole;
import com.libido.model.vo.SysPostQueryVo;
import com.libido.model.vo.SysRoleQueryVo;
import com.libido.system.mapper.SysRoleMapper;
import com.libido.system.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    //条件分页查询
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParm, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPage(pageParm, sysRoleQueryVo);
        return pageModel;
    }
}
