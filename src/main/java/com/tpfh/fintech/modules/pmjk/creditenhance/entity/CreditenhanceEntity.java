/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.creditenhance.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="pmjk_creditenhance")
public class CreditenhanceEntity {
    private Long productid;
    private Long versionnum;
    private Long id;
    private String type;
    private String description;
    private String juniortypedesc;
    private String othertypedesc;

    public Long getProductid() {
        return this.productid;
    }

    public Long getVersionnum() {
        return this.versionnum;
    }

    public Long getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public String getJuniortypedesc() {
        return this.juniortypedesc;
    }

    public String getOthertypedesc() {
        return this.othertypedesc;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public void setVersionnum(Long versionnum) {
        this.versionnum = versionnum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setJuniortypedesc(String juniortypedesc) {
        this.juniortypedesc = juniortypedesc;
    }

    public void setOthertypedesc(String othertypedesc) {
        this.othertypedesc = othertypedesc;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CreditenhanceEntity)) {
            return false;
        }
        CreditenhanceEntity other = (CreditenhanceEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$productid = this.getProductid();
        Long other$productid = other.getProductid();
        if (this$productid == null ? other$productid != null : !((Object)this$productid).equals(other$productid)) {
            return false;
        }
        Long this$versionnum = this.getVersionnum();
        Long other$versionnum = other.getVersionnum();
        if (this$versionnum == null ? other$versionnum != null : !((Object)this$versionnum).equals(other$versionnum)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        String this$type = this.getType();
        String other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        String this$juniortypedesc = this.getJuniortypedesc();
        String other$juniortypedesc = other.getJuniortypedesc();
        if (this$juniortypedesc == null ? other$juniortypedesc != null : !this$juniortypedesc.equals(other$juniortypedesc)) {
            return false;
        }
        String this$othertypedesc = this.getOthertypedesc();
        String other$othertypedesc = other.getOthertypedesc();
        return !(this$othertypedesc == null ? other$othertypedesc != null : !this$othertypedesc.equals(other$othertypedesc));
    }

    protected boolean canEqual(Object other) {
        return other instanceof CreditenhanceEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $productid = this.getProductid();
        result = result * 59 + ($productid == null ? 43 : ((Object)$productid).hashCode());
        Long $versionnum = this.getVersionnum();
        result = result * 59 + ($versionnum == null ? 43 : ((Object)$versionnum).hashCode());
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        String $juniortypedesc = this.getJuniortypedesc();
        result = result * 59 + ($juniortypedesc == null ? 43 : $juniortypedesc.hashCode());
        String $othertypedesc = this.getOthertypedesc();
        result = result * 59 + ($othertypedesc == null ? 43 : $othertypedesc.hashCode());
        return result;
    }

    public String toString() {
        return "CreditenhanceEntity(productid=" + this.getProductid() + ", versionnum=" + this.getVersionnum() + ", id=" + this.getId() + ", type=" + this.getType() + ", description=" + this.getDescription() + ", juniortypedesc=" + this.getJuniortypedesc() + ", othertypedesc=" + this.getOthertypedesc() + ")";
    }
}

