package com.libido.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.libido.model.system.SysRole;
import com.libido.model.vo.SysRoleQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
    //条件分页查询
    public IPage<SysRole> selectPage(Page<SysRole> pageParm, @Param("vo") SysRoleQueryVo sysRoleQueryVo);

}
