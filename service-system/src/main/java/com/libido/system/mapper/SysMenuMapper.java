package com.libido.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.libido.model.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-10-31
 */
@Repository
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

}
