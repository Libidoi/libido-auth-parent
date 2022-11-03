package com.libido.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.libido.model.system.SysMenu;

import com.libido.model.system.SysRoleMenu;
import com.libido.model.vo.AssginMenuVo;
import com.libido.system.exception.libidoException;
import com.libido.system.mapper.SysMenuMapper;
import com.libido.system.mapper.SysRoleMenuMapper;
import com.libido.system.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.libido.system.utils.MenuHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-10-31
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        //获取所有菜单
        List<SysMenu> sysMenuList = baseMapper.selectList(null);

        List<SysMenu> resultList = MenuHelper.buildTree(sysMenuList);
        return resultList;

    }

    @Override
    public void removeMenuById(String id) {
        //查询当前删除菜单下面是否子菜单
        //根据id = parentid
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            //有子菜单
            throw new libidoException(201, "请先删除子菜单");
        }
        //调用删除
        baseMapper.deleteById(id);
    }

    @Override
    public List<SysMenu> findMenuByRoleId(String roleId) {
        //获取所有菜单
        QueryWrapper<SysMenu> wrapperMenu = new QueryWrapper<>();
        wrapperMenu.eq("status", 1);
        List<SysMenu> menuList = baseMapper.selectList(wrapperMenu);
        //根据角色id查询，获取角色分配所有菜单id
        QueryWrapper<SysRoleMenu> wrapperRoleMenu = new QueryWrapper<>();
        wrapperRoleMenu.eq("role_id", roleId);
        List<SysRoleMenu> roleMenus = sysRoleMenuMapper.selectList(wrapperRoleMenu);

        //从第二步查询列表中，获取角色分配所有菜单id
        List<String> roleMenuIds = new ArrayList<>();
        for (SysRoleMenu sysRoleMenu : roleMenus) {
            String  menuId = sysRoleMenu.getMenuId();
            roleMenuIds.add(String.valueOf(menuId));

        }
        //数据处理：isSelect 如果菜单选中true，否则false
        for (SysMenu sysMenu : menuList) {
            if (roleMenuIds.contains(sysMenu.getId())) {
                sysMenu.setSelect(true);
            } else {
                sysMenu.setSelect(false);
            }
        }
        //转换称树形结构
        List<SysMenu> sysMenus = MenuHelper.buildTree(menuList);
        return sysMenus;
    }

    //给角色分配菜单权限
    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //根据角色id删除菜单权限
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", assginMenuVo.getRoleId());
        sysRoleMenuMapper.delete(wrapper);
        //遍历菜单id的列表,一个一个进行添加
//        List<String> menuList = assginMenuVo.getMenuIdList();
//        for (String menuId : menuList) {
//            SysRoleMenu sysRoleMenu = new SysRoleMenu();
//            sysRoleMenu.setMenuId(menuId);
//            sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
//            sysRoleMenuMapper.insert(sysRoleMenu);
        for (String menuId : assginMenuVo.getMenuIdList()) {
            if(menuId != null){
                //创建SysRoleMenu对象
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
                //添加新权限
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
        }


    }
}
