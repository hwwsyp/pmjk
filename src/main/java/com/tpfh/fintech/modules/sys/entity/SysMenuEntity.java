/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableField
 *  com.baomidou.mybatisplus.annotations.TableId
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

@TableName(value="sys_menu")
public class SysMenuEntity
implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long menuId;
    private Long parentId;
    @TableField(exist=false)
    private String parentName;
    private String name;
    private String url;
    private String perms;
    private Integer type;
    private String icon;
    private Integer orderNum;
    @TableField(exist=false)
    private Boolean open;
    @TableField(exist=false)
    private List<?> list;

    public Long getMenuId() {
        return this.menuId;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public String getParentName() {
        return this.parentName;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public String getPerms() {
        return this.perms;
    }

    public Integer getType() {
        return this.type;
    }

    public String getIcon() {
        return this.icon;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }

    public Boolean getOpen() {
        return this.open;
    }

    public List<?> getList() {
        return this.list;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SysMenuEntity)) {
            return false;
        }
        SysMenuEntity other = (SysMenuEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$menuId = this.getMenuId();
        Long other$menuId = other.getMenuId();
        if (this$menuId == null ? other$menuId != null : !((Object)this$menuId).equals(other$menuId)) {
            return false;
        }
        Long this$parentId = this.getParentId();
        Long other$parentId = other.getParentId();
        if (this$parentId == null ? other$parentId != null : !((Object)this$parentId).equals(other$parentId)) {
            return false;
        }
        Integer this$type = this.getType();
        Integer other$type = other.getType();
        if (this$type == null ? other$type != null : !((Object)this$type).equals(other$type)) {
            return false;
        }
        Integer this$orderNum = this.getOrderNum();
        Integer other$orderNum = other.getOrderNum();
        if (this$orderNum == null ? other$orderNum != null : !((Object)this$orderNum).equals(other$orderNum)) {
            return false;
        }
        Boolean this$open = this.getOpen();
        Boolean other$open = other.getOpen();
        if (this$open == null ? other$open != null : !((Object)this$open).equals(other$open)) {
            return false;
        }
        String this$parentName = this.getParentName();
        String other$parentName = other.getParentName();
        if (this$parentName == null ? other$parentName != null : !this$parentName.equals(other$parentName)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$url = this.getUrl();
        String other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) {
            return false;
        }
        String this$perms = this.getPerms();
        String other$perms = other.getPerms();
        if (this$perms == null ? other$perms != null : !this$perms.equals(other$perms)) {
            return false;
        }
        String this$icon = this.getIcon();
        String other$icon = other.getIcon();
        if (this$icon == null ? other$icon != null : !this$icon.equals(other$icon)) {
            return false;
        }
        List<?> this$list = this.getList();
        List<?> other$list = other.getList();
        return !(this$list == null ? other$list != null : !((Object)this$list).equals(other$list));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SysMenuEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $menuId = this.getMenuId();
        result = result * 59 + ($menuId == null ? 43 : ((Object)$menuId).hashCode());
        Long $parentId = this.getParentId();
        result = result * 59 + ($parentId == null ? 43 : ((Object)$parentId).hashCode());
        Integer $type = this.getType();
        result = result * 59 + ($type == null ? 43 : ((Object)$type).hashCode());
        Integer $orderNum = this.getOrderNum();
        result = result * 59 + ($orderNum == null ? 43 : ((Object)$orderNum).hashCode());
        Boolean $open = this.getOpen();
        result = result * 59 + ($open == null ? 43 : ((Object)$open).hashCode());
        String $parentName = this.getParentName();
        result = result * 59 + ($parentName == null ? 43 : $parentName.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $url = this.getUrl();
        result = result * 59 + ($url == null ? 43 : $url.hashCode());
        String $perms = this.getPerms();
        result = result * 59 + ($perms == null ? 43 : $perms.hashCode());
        String $icon = this.getIcon();
        result = result * 59 + ($icon == null ? 43 : $icon.hashCode());
        List<?> $list = this.getList();
        result = result * 59 + ($list == null ? 43 : ((Object)$list).hashCode());
        return result;
    }

    public String toString() {
        return "SysMenuEntity(menuId=" + this.getMenuId() + ", parentId=" + this.getParentId() + ", parentName=" + this.getParentName() + ", name=" + this.getName() + ", url=" + this.getUrl() + ", perms=" + this.getPerms() + ", type=" + this.getType() + ", icon=" + this.getIcon() + ", orderNum=" + this.getOrderNum() + ", open=" + this.getOpen() + ", list=" + this.getList() + ")";
    }
}

