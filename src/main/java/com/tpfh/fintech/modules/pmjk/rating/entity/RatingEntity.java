/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.rating.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="pmjk_rating")
public class RatingEntity {
    private Long id;
    private String lev;
    private String ratingtime;
    private String ratinguser;
    private String ratingtype;
    private String ratingtx;
    private String bodytype;
    private String bodyname;
    private String productid;
    private String targettype;
    private String orgid;
    private String ratingdate;
    private String saveflag;
    private String historyflag;

    public Long getId() {
        return this.id;
    }

    public String getLev() {
        return this.lev;
    }

    public String getRatingtime() {
        return this.ratingtime;
    }

    public String getRatinguser() {
        return this.ratinguser;
    }

    public String getRatingtype() {
        return this.ratingtype;
    }

    public String getRatingtx() {
        return this.ratingtx;
    }

    public String getBodytype() {
        return this.bodytype;
    }

    public String getBodyname() {
        return this.bodyname;
    }

    public String getProductid() {
        return this.productid;
    }

    public String getTargettype() {
        return this.targettype;
    }

    public String getOrgid() {
        return this.orgid;
    }

    public String getRatingdate() {
        return this.ratingdate;
    }

    public String getSaveflag() {
        return this.saveflag;
    }

    public String getHistoryflag() {
        return this.historyflag;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLev(String lev) {
        this.lev = lev;
    }

    public void setRatingtime(String ratingtime) {
        this.ratingtime = ratingtime;
    }

    public void setRatinguser(String ratinguser) {
        this.ratinguser = ratinguser;
    }

    public void setRatingtype(String ratingtype) {
        this.ratingtype = ratingtype;
    }

    public void setRatingtx(String ratingtx) {
        this.ratingtx = ratingtx;
    }

    public void setBodytype(String bodytype) {
        this.bodytype = bodytype;
    }

    public void setBodyname(String bodyname) {
        this.bodyname = bodyname;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public void setTargettype(String targettype) {
        this.targettype = targettype;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public void setRatingdate(String ratingdate) {
        this.ratingdate = ratingdate;
    }

    public void setSaveflag(String saveflag) {
        this.saveflag = saveflag;
    }

    public void setHistoryflag(String historyflag) {
        this.historyflag = historyflag;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RatingEntity)) {
            return false;
        }
        RatingEntity other = (RatingEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        String this$lev = this.getLev();
        String other$lev = other.getLev();
        if (this$lev == null ? other$lev != null : !this$lev.equals(other$lev)) {
            return false;
        }
        String this$ratingtime = this.getRatingtime();
        String other$ratingtime = other.getRatingtime();
        if (this$ratingtime == null ? other$ratingtime != null : !this$ratingtime.equals(other$ratingtime)) {
            return false;
        }
        String this$ratinguser = this.getRatinguser();
        String other$ratinguser = other.getRatinguser();
        if (this$ratinguser == null ? other$ratinguser != null : !this$ratinguser.equals(other$ratinguser)) {
            return false;
        }
        String this$ratingtype = this.getRatingtype();
        String other$ratingtype = other.getRatingtype();
        if (this$ratingtype == null ? other$ratingtype != null : !this$ratingtype.equals(other$ratingtype)) {
            return false;
        }
        String this$ratingtx = this.getRatingtx();
        String other$ratingtx = other.getRatingtx();
        if (this$ratingtx == null ? other$ratingtx != null : !this$ratingtx.equals(other$ratingtx)) {
            return false;
        }
        String this$bodytype = this.getBodytype();
        String other$bodytype = other.getBodytype();
        if (this$bodytype == null ? other$bodytype != null : !this$bodytype.equals(other$bodytype)) {
            return false;
        }
        String this$bodyname = this.getBodyname();
        String other$bodyname = other.getBodyname();
        if (this$bodyname == null ? other$bodyname != null : !this$bodyname.equals(other$bodyname)) {
            return false;
        }
        String this$productid = this.getProductid();
        String other$productid = other.getProductid();
        if (this$productid == null ? other$productid != null : !this$productid.equals(other$productid)) {
            return false;
        }
        String this$targettype = this.getTargettype();
        String other$targettype = other.getTargettype();
        if (this$targettype == null ? other$targettype != null : !this$targettype.equals(other$targettype)) {
            return false;
        }
        String this$orgid = this.getOrgid();
        String other$orgid = other.getOrgid();
        if (this$orgid == null ? other$orgid != null : !this$orgid.equals(other$orgid)) {
            return false;
        }
        String this$ratingdate = this.getRatingdate();
        String other$ratingdate = other.getRatingdate();
        if (this$ratingdate == null ? other$ratingdate != null : !this$ratingdate.equals(other$ratingdate)) {
            return false;
        }
        String this$saveflag = this.getSaveflag();
        String other$saveflag = other.getSaveflag();
        if (this$saveflag == null ? other$saveflag != null : !this$saveflag.equals(other$saveflag)) {
            return false;
        }
        String this$historyflag = this.getHistoryflag();
        String other$historyflag = other.getHistoryflag();
        return !(this$historyflag == null ? other$historyflag != null : !this$historyflag.equals(other$historyflag));
    }

    protected boolean canEqual(Object other) {
        return other instanceof RatingEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $lev = this.getLev();
        result = result * 59 + ($lev == null ? 43 : $lev.hashCode());
        String $ratingtime = this.getRatingtime();
        result = result * 59 + ($ratingtime == null ? 43 : $ratingtime.hashCode());
        String $ratinguser = this.getRatinguser();
        result = result * 59 + ($ratinguser == null ? 43 : $ratinguser.hashCode());
        String $ratingtype = this.getRatingtype();
        result = result * 59 + ($ratingtype == null ? 43 : $ratingtype.hashCode());
        String $ratingtx = this.getRatingtx();
        result = result * 59 + ($ratingtx == null ? 43 : $ratingtx.hashCode());
        String $bodytype = this.getBodytype();
        result = result * 59 + ($bodytype == null ? 43 : $bodytype.hashCode());
        String $bodyname = this.getBodyname();
        result = result * 59 + ($bodyname == null ? 43 : $bodyname.hashCode());
        String $productid = this.getProductid();
        result = result * 59 + ($productid == null ? 43 : $productid.hashCode());
        String $targettype = this.getTargettype();
        result = result * 59 + ($targettype == null ? 43 : $targettype.hashCode());
        String $orgid = this.getOrgid();
        result = result * 59 + ($orgid == null ? 43 : $orgid.hashCode());
        String $ratingdate = this.getRatingdate();
        result = result * 59 + ($ratingdate == null ? 43 : $ratingdate.hashCode());
        String $saveflag = this.getSaveflag();
        result = result * 59 + ($saveflag == null ? 43 : $saveflag.hashCode());
        String $historyflag = this.getHistoryflag();
        result = result * 59 + ($historyflag == null ? 43 : $historyflag.hashCode());
        return result;
    }

    public String toString() {
        return "RatingEntity(id=" + this.getId() + ", lev=" + this.getLev() + ", ratingtime=" + this.getRatingtime() + ", ratinguser=" + this.getRatinguser() + ", ratingtype=" + this.getRatingtype() + ", ratingtx=" + this.getRatingtx() + ", bodytype=" + this.getBodytype() + ", bodyname=" + this.getBodyname() + ", productid=" + this.getProductid() + ", targettype=" + this.getTargettype() + ", orgid=" + this.getOrgid() + ", ratingdate=" + this.getRatingdate() + ", saveflag=" + this.getSaveflag() + ", historyflag=" + this.getHistoryflag() + ")";
    }
}

