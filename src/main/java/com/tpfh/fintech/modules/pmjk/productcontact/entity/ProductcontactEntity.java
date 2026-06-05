/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.productcontact.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="pmjk_productcontact")
public class ProductcontactEntity {
    private Long id;
    private Long contactid;
    private Long sourceid;
    private String sourcename;

    public Long getId() {
        return this.id;
    }

    public Long getContactid() {
        return this.contactid;
    }

    public Long getSourceid() {
        return this.sourceid;
    }

    public String getSourcename() {
        return this.sourcename;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContactid(Long contactid) {
        this.contactid = contactid;
    }

    public void setSourceid(Long sourceid) {
        this.sourceid = sourceid;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProductcontactEntity)) {
            return false;
        }
        ProductcontactEntity other = (ProductcontactEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$contactid = this.getContactid();
        Long other$contactid = other.getContactid();
        if (this$contactid == null ? other$contactid != null : !((Object)this$contactid).equals(other$contactid)) {
            return false;
        }
        Long this$sourceid = this.getSourceid();
        Long other$sourceid = other.getSourceid();
        if (this$sourceid == null ? other$sourceid != null : !((Object)this$sourceid).equals(other$sourceid)) {
            return false;
        }
        String this$sourcename = this.getSourcename();
        String other$sourcename = other.getSourcename();
        return !(this$sourcename == null ? other$sourcename != null : !this$sourcename.equals(other$sourcename));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProductcontactEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $contactid = this.getContactid();
        result = result * 59 + ($contactid == null ? 43 : ((Object)$contactid).hashCode());
        Long $sourceid = this.getSourceid();
        result = result * 59 + ($sourceid == null ? 43 : ((Object)$sourceid).hashCode());
        String $sourcename = this.getSourcename();
        result = result * 59 + ($sourcename == null ? 43 : $sourcename.hashCode());
        return result;
    }

    public String toString() {
        return "ProductcontactEntity(id=" + this.getId() + ", contactid=" + this.getContactid() + ", sourceid=" + this.getSourceid() + ", sourcename=" + this.getSourcename() + ")";
    }
}

