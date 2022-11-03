package com.libido.system.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.libido.model.system.SysUser;
import com.libido.model.vo.SysUserQueryVo;
import com.libido.system.mapper.SysUserMapper;
import com.libido.system.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.PrimitiveIterator;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-10-30
 */
@Transactional
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    //用户列表
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParm, SysUserQueryVo sysUserQueryVo) {
        return baseMapper.selectPage(pageParm, sysUserQueryVo);
    }

    @Override
    public void updateStatus(String id, Integer status) {
        SysUser sysUser  =baseMapper.selectById(id);
        sysUser.setStatus(status);
        baseMapper.updateById(sysUser);
    }


}
