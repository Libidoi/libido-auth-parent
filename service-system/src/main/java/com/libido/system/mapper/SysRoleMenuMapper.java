package com.libido.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.libido.model.system.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
}
