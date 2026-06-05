/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.baseInstitutions.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="pmjk_base_institutions")
public class BaseInstitutionsEntity {
    private Long id;
    private String name;
    private Long institutionstype;
    private String swcode;
    private String gicscode;
    private String countrycode;
    private Long isgroupcustomer;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Long getInstitutionstype() {
        return this.institutionstype;
    }

    public String getSwcode() {
        return this.swcode;
    }

    public String getGicscode() {
        return this.gicscode;
    }

    public String getCountrycode() {
        return this.countrycode;
    }

    public Long getIsgroupcustomer() {
        return this.isgroupcustomer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstitutionstype(Long institutionstype) {
        this.institutionstype = institutionstype;
    }

    public void setSwcode(String swcode) {
        this.swcode = swcode;
    }

    public void setGicscode(String gicscode) {
        this.gicscode = gicscode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public void setIsgroupcustomer(Long isgroupcustomer) {
        this.isgroupcustomer = isgroupcustomer;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BaseInstitutionsEntity)) {
            return false;
        }
        BaseInstitutionsEntity other = (BaseInstitutionsEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$institutionstype = this.getInstitutionstype();
        Long other$institutionstype = other.getInstitutionstype();
        if (this$institutionstype == null ? other$institutionstype != null : !((Object)this$institutionstype).equals(other$institutionstype)) {
            return false;
        }
        Long this$isgroupcustomer = this.getIsgroupcustomer();
        Long other$isgroupcustomer = other.getIsgroupcustomer();
        if (this$isgroupcustomer == null ? other$isgroupcustomer != null : !((Object)this$isgroupcustomer).equals(other$isgroupcustomer)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$swcode = this.getSwcode();
        String other$swcode = other.getSwcode();
        if (this$swcode == null ? other$swcode != null : !this$swcode.equals(other$swcode)) {
            return false;
        }
        String this$gicscode = this.getGicscode();
        String other$gicscode = other.getGicscode();
        if (this$gicscode == null ? other$gicscode != null : !this$gicscode.equals(other$gicscode)) {
            return false;
        }
        String this$countrycode = this.getCountrycode();
        String other$countrycode = other.getCountrycode();
        return !(this$countrycode == null ? other$countrycode != null : !this$countrycode.equals(other$countrycode));
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseInstitutionsEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $institutionstype = this.getInstitutionstype();
        result = result * 59 + ($institutionstype == null ? 43 : ((Object)$institutionstype).hashCode());
        Long $isgroupcustomer = this.getIsgroupcustomer();
        result = result * 59 + ($isgroupcustomer == null ? 43 : ((Object)$isgroupcustomer).hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $swcode = this.getSwcode();
        result = result * 59 + ($swcode == null ? 43 : $swcode.hashCode());
        String $gicscode = this.getGicscode();
        result = result * 59 + ($gicscode == null ? 43 : $gicscode.hashCode());
        String $countrycode = this.getCountrycode();
        result = result * 59 + ($countrycode == null ? 43 : $countrycode.hashCode());
        return result;
    }

    public String toString() {
        return "BaseInstitutionsEntity(id=" + this.getId() + ", name=" + this.getName() + ", institutionstype=" + this.getInstitutionstype() + ", swcode=" + this.getSwcode() + ", gicscode=" + this.getGicscode() + ", countrycode=" + this.getCountrycode() + ", isgroupcustomer=" + this.getIsgroupcustomer() + ")";
    }
}

