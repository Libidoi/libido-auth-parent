package com.libido.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.libido.model.system.SysMenu;
import com.libido.model.vo.AssginMenuVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-10-31
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findNodes();

    void removeMenuById(String id);

    List<SysMenu> findMenuByRoleId(String roleId);

    void doAssign(AssginMenuVo assginMenuVo);
}

/**
 * 菜单树形数据
 *
 * @return
 */




