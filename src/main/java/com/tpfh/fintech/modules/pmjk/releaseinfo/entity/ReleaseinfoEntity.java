/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.releaseinfo.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="pmjk_releaseinfo")
public class ReleaseinfoEntity {
    private Long id;
    private Long productid;
    private String issuesecurity;
    private Long issueway;
    private Long issuemarketid;
    private String issuemarketname;
    private Long issueid;
    private Long issuecountryid;
    private String issueindustryid;
    private Long ratingtype;
    private Long ratinglevel;
    private Long versionnum;

    public Long getId() {
        return this.id;
    }

    public Long getProductid() {
        return this.productid;
    }

    public String getIssuesecurity() {
        return this.issuesecurity;
    }

    public Long getIssueway() {
        return this.issueway;
    }

    public Long getIssuemarketid() {
        return this.issuemarketid;
    }

    public String getIssuemarketname() {
        return this.issuemarketname;
    }

    public Long getIssueid() {
        return this.issueid;
    }

    public Long getIssuecountryid() {
        return this.issuecountryid;
    }

    public String getIssueindustryid() {
        return this.issueindustryid;
    }

    public Long getRatingtype() {
        return this.ratingtype;
    }

    public Long getRatinglevel() {
        return this.ratinglevel;
    }

    public Long getVersionnum() {
        return this.versionnum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public void setIssuesecurity(String issuesecurity) {
        this.issuesecurity = issuesecurity;
    }

    public void setIssueway(Long issueway) {
        this.issueway = issueway;
    }

    public void setIssuemarketid(Long issuemarketid) {
        this.issuemarketid = issuemarketid;
    }

    public void setIssuemarketname(String issuemarketname) {
        this.issuemarketname = issuemarketname;
    }

    public void setIssueid(Long issueid) {
        this.issueid = issueid;
    }

    public void setIssuecountryid(Long issuecountryid) {
        this.issuecountryid = issuecountryid;
    }

    public void setIssueindustryid(String issueindustryid) {
        this.issueindustryid = issueindustryid;
    }

    public void setRatingtype(Long ratingtype) {
        this.ratingtype = ratingtype;
    }

    public void setRatinglevel(Long ratinglevel) {
        this.ratinglevel = ratinglevel;
    }

    public void setVersionnum(Long versionnum) {
        this.versionnum = versionnum;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReleaseinfoEntity)) {
            return false;
        }
        ReleaseinfoEntity other = (ReleaseinfoEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$productid = this.getProductid();
        Long other$productid = other.getProductid();
        if (this$productid == null ? other$productid != null : !((Object)this$productid).equals(other$productid)) {
            return false;
        }
        Long this$issueway = this.getIssueway();
        Long other$issueway = other.getIssueway();
        if (this$issueway == null ? other$issueway != null : !((Object)this$issueway).equals(other$issueway)) {
            return false;
        }
        Long this$issuemarketid = this.getIssuemarketid();
        Long other$issuemarketid = other.getIssuemarketid();
        if (this$issuemarketid == null ? other$issuemarketid != null : !((Object)this$issuemarketid).equals(other$issuemarketid)) {
            return false;
        }
        Long this$issueid = this.getIssueid();
        Long other$issueid = other.getIssueid();
        if (this$issueid == null ? other$issueid != null : !((Object)this$issueid).equals(other$issueid)) {
            return false;
        }
        Long this$issuecountryid = this.getIssuecountryid();
        Long other$issuecountryid = other.getIssuecountryid();
        if (this$issuecountryid == null ? other$issuecountryid != null : !((Object)this$issuecountryid).equals(other$issuecountryid)) {
            return false;
        }
        Long this$ratingtype = this.getRatingtype();
        Long other$ratingtype = other.getRatingtype();
        if (this$ratingtype == null ? other$ratingtype != null : !((Object)this$ratingtype).equals(other$ratingtype)) {
            return false;
        }
        Long this$ratinglevel = this.getRatinglevel();
        Long other$ratinglevel = other.getRatinglevel();
        if (this$ratinglevel == null ? other$ratinglevel != null : !((Object)this$ratinglevel).equals(other$ratinglevel)) {
            return false;
        }
        Long this$versionnum = this.getVersionnum();
        Long other$versionnum = other.getVersionnum();
        if (this$versionnum == null ? other$versionnum != null : !((Object)this$versionnum).equals(other$versionnum)) {
            return false;
        }
        String this$issuesecurity = this.getIssuesecurity();
        String other$issuesecurity = other.getIssuesecurity();
        if (this$issuesecurity == null ? other$issuesecurity != null : !this$issuesecurity.equals(other$issuesecurity)) {
            return false;
        }
        String this$issuemarketname = this.getIssuemarketname();
        String other$issuemarketname = other.getIssuemarketname();
        if (this$issuemarketname == null ? other$issuemarketname != null : !this$issuemarketname.equals(other$issuemarketname)) {
            return false;
        }
        String this$issueindustryid = this.getIssueindustryid();
        String other$issueindustryid = other.getIssueindustryid();
        return !(this$issueindustryid == null ? other$issueindustryid != null : !this$issueindustryid.equals(other$issueindustryid));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ReleaseinfoEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $productid = this.getProductid();
        result = result * 59 + ($productid == null ? 43 : ((Object)$productid).hashCode());
        Long $issueway = this.getIssueway();
        result = result * 59 + ($issueway == null ? 43 : ((Object)$issueway).hashCode());
        Long $issuemarketid = this.getIssuemarketid();
        result = result * 59 + ($issuemarketid == null ? 43 : ((Object)$issuemarketid).hashCode());
        Long $issueid = this.getIssueid();
        result = result * 59 + ($issueid == null ? 43 : ((Object)$issueid).hashCode());
        Long $issuecountryid = this.getIssuecountryid();
        result = result * 59 + ($issuecountryid == null ? 43 : ((Object)$issuecountryid).hashCode());
        Long $ratingtype = this.getRatingtype();
        result = result * 59 + ($ratingtype == null ? 43 : ((Object)$ratingtype).hashCode());
        Long $ratinglevel = this.getRatinglevel();
        result = result * 59 + ($ratinglevel == null ? 43 : ((Object)$ratinglevel).hashCode());
        Long $versionnum = this.getVersionnum();
        result = result * 59 + ($versionnum == null ? 43 : ((Object)$versionnum).hashCode());
        String $issuesecurity = this.getIssuesecurity();
        result = result * 59 + ($issuesecurity == null ? 43 : $issuesecurity.hashCode());
        String $issuemarketname = this.getIssuemarketname();
        result = result * 59 + ($issuemarketname == null ? 43 : $issuemarketname.hashCode());
        String $issueindustryid = this.getIssueindustryid();
        result = result * 59 + ($issueindustryid == null ? 43 : $issueindustryid.hashCode());
        return result;
    }

    public String toString() {
        return "ReleaseinfoEntity(id=" + this.getId() + ", productid=" + this.getProductid() + ", issuesecurity=" + this.getIssuesecurity() + ", issueway=" + this.getIssueway() + ", issuemarketid=" + this.getIssuemarketid() + ", issuemarketname=" + this.getIssuemarketname() + ", issueid=" + this.getIssueid() + ", issuecountryid=" + this.getIssuecountryid() + ", issueindustryid=" + this.getIssueindustryid() + ", ratingtype=" + this.getRatingtype() + ", ratinglevel=" + this.getRatinglevel() + ", versionnum=" + this.getVersionnum() + ")";
    }
}

