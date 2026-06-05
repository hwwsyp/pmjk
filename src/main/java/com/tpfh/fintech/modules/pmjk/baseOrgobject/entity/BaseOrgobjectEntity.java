/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.baseOrgobject.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="pmjk_base_orgobject")
public class BaseOrgobjectEntity {
    private Long id;
    private String orgname;
    private Long objecttype;
    private String description;
    private String unittype;
    private String userid;
    private String orgid;

    public Long getId() {
        return this.id;
    }

    public String getOrgname() {
        return this.orgname;
    }

    public Long getObjecttype() {
        return this.objecttype;
    }

    public String getDescription() {
        return this.description;
    }

    public String getUnittype() {
        return this.unittype;
    }

    public String getUserid() {
        return this.userid;
    }

    public String getOrgid() {
        return this.orgid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public void setObjecttype(Long objecttype) {
        this.objecttype = objecttype;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BaseOrgobjectEntity)) {
            return false;
        }
        BaseOrgobjectEntity other = (BaseOrgobjectEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$objecttype = this.getObjecttype();
        Long other$objecttype = other.getObjecttype();
        if (this$objecttype == null ? other$objecttype != null : !((Object)this$objecttype).equals(other$objecttype)) {
            return false;
        }
        String this$orgname = this.getOrgname();
        String other$orgname = other.getOrgname();
        if (this$orgname == null ? other$orgname != null : !this$orgname.equals(other$orgname)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        String this$unittype = this.getUnittype();
        String other$unittype = other.getUnittype();
        if (this$unittype == null ? other$unittype != null : !this$unittype.equals(other$unittype)) {
            return false;
        }
        String this$userid = this.getUserid();
        String other$userid = other.getUserid();
        if (this$userid == null ? other$userid != null : !this$userid.equals(other$userid)) {
            return false;
        }
        String this$orgid = this.getOrgid();
        String other$orgid = other.getOrgid();
        return !(this$orgid == null ? other$orgid != null : !this$orgid.equals(other$orgid));
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseOrgobjectEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $objecttype = this.getObjecttype();
        result = result * 59 + ($objecttype == null ? 43 : ((Object)$objecttype).hashCode());
        String $orgname = this.getOrgname();
        result = result * 59 + ($orgname == null ? 43 : $orgname.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        String $unittype = this.getUnittype();
        result = result * 59 + ($unittype == null ? 43 : $unittype.hashCode());
        String $userid = this.getUserid();
        result = result * 59 + ($userid == null ? 43 : $userid.hashCode());
        String $orgid = this.getOrgid();
        result = result * 59 + ($orgid == null ? 43 : $orgid.hashCode());
        return result;
    }

    public String toString() {
        return "BaseOrgobjectEntity(id=" + this.getId() + ", orgname=" + this.getOrgname() + ", objecttype=" + this.getObjecttype() + ", description=" + this.getDescription() + ", unittype=" + this.getUnittype() + ", userid=" + this.getUserid() + ", orgid=" + this.getOrgid() + ")";
    }
}

