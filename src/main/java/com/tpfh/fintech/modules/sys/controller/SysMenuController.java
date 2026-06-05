/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.EntityWrapper
 *  com.baomidou.mybatisplus.mapper.Wrapper
 *  org.apache.commons.lang.StringUtils
 *  org.apache.shiro.authz.annotation.RequiresPermissions
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package com.tpfh.fintech.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.exception.TpfhException;
import com.tpfh.fintech.common.utils.Constant;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.sys.controller.AbstractController;
import com.tpfh.fintech.modules.sys.entity.SysMenuEntity;
import com.tpfh.fintech.modules.sys.service.ShiroService;
import com.tpfh.fintech.modules.sys.service.SysMenuService;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/sys/menu"})
public class SysMenuController
extends AbstractController {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private ShiroService shiroService;

    @GetMapping(value={"/nav"})
    public R nav() {
        List<SysMenuEntity> menuList = this.sysMenuService.getUserMenuList(this.getUserId());
        Set<String> permissions = this.shiroService.getUserPermissions(this.getUserId());
        return R.ok().put("menuList", (Object)menuList).put("permissions", (Object)permissions);
    }

    @GetMapping(value={"/list"})
    @RequiresPermissions(value={"sys:menu:list"})
    public List<SysMenuEntity> list() {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.orderAsc(Arrays.asList("type", "order_num"));
        List menuList = this.sysMenuService.selectList((Wrapper)wrapper);
        for (SysMenuEntity sysMenuEntity : menuList) {
            System.out.println(String.valueOf(sysMenuEntity.getName()) + "," + sysMenuEntity.getParentName() + "," + sysMenuEntity.getParentId());
            SysMenuEntity parentMenuEntity = (SysMenuEntity)this.sysMenuService.selectById(sysMenuEntity.getParentId());
            if (parentMenuEntity == null) continue;
            System.out.println(parentMenuEntity.getName());
            sysMenuEntity.setParentName(parentMenuEntity.getName());
        }
        return menuList;
    }

    @GetMapping(value={"/select"})
    @RequiresPermissions(value={"sys:menu:select"})
    public R select() {
        List<SysMenuEntity> menuList = this.sysMenuService.queryNotButtonList();
        SysMenuEntity root = new SysMenuEntity();
        root.setMenuId(0L);
        root.setName("\u4e00\u7ea7\u83dc\u5355");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);
        return R.ok().put("menuList", (Object)menuList);
    }

    @GetMapping(value={"/info/{menuId}"})
    @RequiresPermissions(value={"sys:menu:info"})
    public R info(@PathVariable(value="menuId") Long menuId) {
        SysMenuEntity menu = (SysMenuEntity)this.sysMenuService.selectById(menuId);
        SysMenuEntity parentMenu = (SysMenuEntity)this.sysMenuService.selectById(menu.getParentId());
        if (parentMenu != null) {
            menu.setParentName(parentMenu.getName());
        }
        return R.ok().put("menu", (Object)menu);
    }

    @SysLog(value="\u4fdd\u5b58\u83dc\u5355")
    @PostMapping(value={"/save"})
    @RequiresPermissions(value={"sys:menu:save"})
    public R save(@RequestBody SysMenuEntity menu) {
        this.verifyForm(menu);
        this.sysMenuService.insert(menu);
        return R.ok();
    }

    @SysLog(value="\u4fee\u6539\u83dc\u5355")
    @PostMapping(value={"/update"})
    @RequiresPermissions(value={"sys:menu:update"})
    public R update(@RequestBody SysMenuEntity menu) {
        this.verifyForm(menu);
        this.sysMenuService.updateById(menu);
        return R.ok();
    }

    @SysLog(value="\u5220\u9664\u83dc\u5355")
    @PostMapping(value={"/delete/{menuId}"})
    @RequiresPermissions(value={"sys:menu:delete"})
    public R delete(@PathVariable(value="menuId") long menuId) {
        if (menuId <= 31L) {
            return R.error("\u7cfb\u7edf\u83dc\u5355\uff0c\u4e0d\u80fd\u5220\u9664");
        }
        List<SysMenuEntity> menuList = this.sysMenuService.queryListParentId(menuId);
        if (menuList.size() > 0) {
            return R.error("\u8bf7\u5148\u5220\u9664\u5b50\u83dc\u5355\u6216\u6309\u94ae");
        }
        this.sysMenuService.delete(menuId);
        return R.ok();
    }

    private void verifyForm(SysMenuEntity menu) {
        if (StringUtils.isBlank((String)menu.getName())) {
            throw new TpfhException("\u83dc\u5355\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a");
        }
        if (menu.getParentId() == null) {
            throw new TpfhException("\u4e0a\u7ea7\u83dc\u5355\u4e0d\u80fd\u4e3a\u7a7a");
        }
        if (menu.getType().intValue() == Constant.MenuType.MENU.getValue() && StringUtils.isBlank((String)menu.getUrl())) {
            throw new TpfhException("\u83dc\u5355URL\u4e0d\u80fd\u4e3a\u7a7a");
        }
        int parentType = Constant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0L) {
            SysMenuEntity parentMenu = (SysMenuEntity)this.sysMenuService.selectById(menu.getParentId());
            parentType = parentMenu.getType();
        }
        if (menu.getType().intValue() == Constant.MenuType.CATALOG.getValue() || menu.getType().intValue() == Constant.MenuType.MENU.getValue()) {
            if (parentType != Constant.MenuType.CATALOG.getValue()) {
                throw new TpfhException("\u4e0a\u7ea7\u83dc\u5355\u53ea\u80fd\u4e3a\u76ee\u5f55\u7c7b\u578b");
            }
            return;
        }
        if (menu.getType().intValue() == Constant.MenuType.BUTTON.getValue()) {
            if (parentType != Constant.MenuType.MENU.getValue()) {
                throw new TpfhException("\u4e0a\u7ea7\u83dc\u5355\u53ea\u80fd\u4e3a\u83dc\u5355\u7c7b\u578b");
            }
            return;
        }
    }
}

