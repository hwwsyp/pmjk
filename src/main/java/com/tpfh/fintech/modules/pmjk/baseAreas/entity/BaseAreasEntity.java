/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.baseAreas.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="pmjk_base_areas")
public class BaseAreasEntity {
    private Long id;
    private String name;
    private String code;
    private Long levelnum;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public Long getLevelnum() {
        return this.levelnum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLevelnum(Long levelnum) {
        this.levelnum = levelnum;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BaseAreasEntity)) {
            return false;
        }
        BaseAreasEntity other = (BaseAreasEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$levelnum = this.getLevelnum();
        Long other$levelnum = other.getLevelnum();
        if (this$levelnum == null ? other$levelnum != null : !((Object)this$levelnum).equals(other$levelnum)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$code = this.getCode();
        String other$code = other.getCode();
        return !(this$code == null ? other$code != null : !this$code.equals(other$code));
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseAreasEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $levelnum = this.getLevelnum();
        result = result * 59 + ($levelnum == null ? 43 : ((Object)$levelnum).hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        return result;
    }

    public String toString() {
        return "BaseAreasEntity(id=" + this.getId() + ", name=" + this.getName() + ", code=" + this.getCode() + ", levelnum=" + this.getLevelnum() + ")";
    }
}

