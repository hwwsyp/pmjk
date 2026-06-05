/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.institutionsrela.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="pmjk_institutionsrela")
public class InstitutionsrelaEntity {
    private Long id;
    private Long danbaorentype;
    private Long institutionsid;
    private String internalratinglevel;
    private String internalratinguser;
    private String internalratingdate;
    private String externalratingdesc;
    private Long creditenhanceid;

    public Long getId() {
        return this.id;
    }

    public Long getDanbaorentype() {
        return this.danbaorentype;
    }

    public Long getInstitutionsid() {
        return this.institutionsid;
    }

    public String getInternalratinglevel() {
        return this.internalratinglevel;
    }

    public String getInternalratinguser() {
        return this.internalratinguser;
    }

    public String getInternalratingdate() {
        return this.internalratingdate;
    }

    public String getExternalratingdesc() {
        return this.externalratingdesc;
    }

    public Long getCreditenhanceid() {
        return this.creditenhanceid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDanbaorentype(Long danbaorentype) {
        this.danbaorentype = danbaorentype;
    }

    public void setInstitutionsid(Long institutionsid) {
        this.institutionsid = institutionsid;
    }

    public void setInternalratinglevel(String internalratinglevel) {
        this.internalratinglevel = internalratinglevel;
    }

    public void setInternalratinguser(String internalratinguser) {
        this.internalratinguser = internalratinguser;
    }

    public void setInternalratingdate(String internalratingdate) {
        this.internalratingdate = internalratingdate;
    }

    public void setExternalratingdesc(String externalratingdesc) {
        this.externalratingdesc = externalratingdesc;
    }

    public void setCreditenhanceid(Long creditenhanceid) {
        this.creditenhanceid = creditenhanceid;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof InstitutionsrelaEntity)) {
            return false;
        }
        InstitutionsrelaEntity other = (InstitutionsrelaEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$danbaorentype = this.getDanbaorentype();
        Long other$danbaorentype = other.getDanbaorentype();
        if (this$danbaorentype == null ? other$danbaorentype != null : !((Object)this$danbaorentype).equals(other$danbaorentype)) {
            return false;
        }
        Long this$institutionsid = this.getInstitutionsid();
        Long other$institutionsid = other.getInstitutionsid();
        if (this$institutionsid == null ? other$institutionsid != null : !((Object)this$institutionsid).equals(other$institutionsid)) {
            return false;
        }
        Long this$creditenhanceid = this.getCreditenhanceid();
        Long other$creditenhanceid = other.getCreditenhanceid();
        if (this$creditenhanceid == null ? other$creditenhanceid != null : !((Object)this$creditenhanceid).equals(other$creditenhanceid)) {
            return false;
        }
        String this$internalratinglevel = this.getInternalratinglevel();
        String other$internalratinglevel = other.getInternalratinglevel();
        if (this$internalratinglevel == null ? other$internalratinglevel != null : !this$internalratinglevel.equals(other$internalratinglevel)) {
            return false;
        }
        String this$internalratinguser = this.getInternalratinguser();
        String other$internalratinguser = other.getInternalratinguser();
        if (this$internalratinguser == null ? other$internalratinguser != null : !this$internalratinguser.equals(other$internalratinguser)) {
            return false;
        }
        String this$internalratingdate = this.getInternalratingdate();
        String other$internalratingdate = other.getInternalratingdate();
        if (this$internalratingdate == null ? other$internalratingdate != null : !this$internalratingdate.equals(other$internalratingdate)) {
            return false;
        }
        String this$externalratingdesc = this.getExternalratingdesc();
        String other$externalratingdesc = other.getExternalratingdesc();
        return !(this$externalratingdesc == null ? other$externalratingdesc != null : !this$externalratingdesc.equals(other$externalratingdesc));
    }

    protected boolean canEqual(Object other) {
        return other instanceof InstitutionsrelaEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $danbaorentype = this.getDanbaorentype();
        result = result * 59 + ($danbaorentype == null ? 43 : ((Object)$danbaorentype).hashCode());
        Long $institutionsid = this.getInstitutionsid();
        result = result * 59 + ($institutionsid == null ? 43 : ((Object)$institutionsid).hashCode());
        Long $creditenhanceid = this.getCreditenhanceid();
        result = result * 59 + ($creditenhanceid == null ? 43 : ((Object)$creditenhanceid).hashCode());
        String $internalratinglevel = this.getInternalratinglevel();
        result = result * 59 + ($internalratinglevel == null ? 43 : $internalratinglevel.hashCode());
        String $internalratinguser = this.getInternalratinguser();
        result = result * 59 + ($internalratinguser == null ? 43 : $internalratinguser.hashCode());
        String $internalratingdate = this.getInternalratingdate();
        result = result * 59 + ($internalratingdate == null ? 43 : $internalratingdate.hashCode());
        String $externalratingdesc = this.getExternalratingdesc();
        result = result * 59 + ($externalratingdesc == null ? 43 : $externalratingdesc.hashCode());
        return result;
    }

    public String toString() {
        return "InstitutionsrelaEntity(id=" + this.getId() + ", danbaorentype=" + this.getDanbaorentype() + ", institutionsid=" + this.getInstitutionsid() + ", internalratinglevel=" + this.getInternalratinglevel() + ", internalratinguser=" + this.getInternalratinguser() + ", internalratingdate=" + this.getInternalratingdate() + ", externalratingdesc=" + this.getExternalratingdesc() + ", creditenhanceid=" + this.getCreditenhanceid() + ")";
    }
}

