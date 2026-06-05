/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.groupvotinginfo.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="pmjk_groupvotinginfo")
public class GroupvotinginfoEntity {
    private Long id;
    private Long isinvest;
    private Long budgettype;
    private String purchasemyselfbuild;
    private String owncapital;
    private String nonowncaptial;
    private String groupcaptialsource;
    private Long productid;
    private String submitter;
    private String mettingtime;
    private String freshflag;

    public Long getId() {
        return this.id;
    }

    public Long getIsinvest() {
        return this.isinvest;
    }

    public Long getBudgettype() {
        return this.budgettype;
    }

    public String getPurchasemyselfbuild() {
        return this.purchasemyselfbuild;
    }

    public String getOwncapital() {
        return this.owncapital;
    }

    public String getNonowncaptial() {
        return this.nonowncaptial;
    }

    public String getGroupcaptialsource() {
        return this.groupcaptialsource;
    }

    public Long getProductid() {
        return this.productid;
    }

    public String getSubmitter() {
        return this.submitter;
    }

    public String getMettingtime() {
        return this.mettingtime;
    }

    public String getFreshflag() {
        return this.freshflag;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsinvest(Long isinvest) {
        this.isinvest = isinvest;
    }

    public void setBudgettype(Long budgettype) {
        this.budgettype = budgettype;
    }

    public void setPurchasemyselfbuild(String purchasemyselfbuild) {
        this.purchasemyselfbuild = purchasemyselfbuild;
    }

    public void setOwncapital(String owncapital) {
        this.owncapital = owncapital;
    }

    public void setNonowncaptial(String nonowncaptial) {
        this.nonowncaptial = nonowncaptial;
    }

    public void setGroupcaptialsource(String groupcaptialsource) {
        this.groupcaptialsource = groupcaptialsource;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public void setMettingtime(String mettingtime) {
        this.mettingtime = mettingtime;
    }

    public void setFreshflag(String freshflag) {
        this.freshflag = freshflag;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GroupvotinginfoEntity)) {
            return false;
        }
        GroupvotinginfoEntity other = (GroupvotinginfoEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$isinvest = this.getIsinvest();
        Long other$isinvest = other.getIsinvest();
        if (this$isinvest == null ? other$isinvest != null : !((Object)this$isinvest).equals(other$isinvest)) {
            return false;
        }
        Long this$budgettype = this.getBudgettype();
        Long other$budgettype = other.getBudgettype();
        if (this$budgettype == null ? other$budgettype != null : !((Object)this$budgettype).equals(other$budgettype)) {
            return false;
        }
        Long this$productid = this.getProductid();
        Long other$productid = other.getProductid();
        if (this$productid == null ? other$productid != null : !((Object)this$productid).equals(other$productid)) {
            return false;
        }
        String this$purchasemyselfbuild = this.getPurchasemyselfbuild();
        String other$purchasemyselfbuild = other.getPurchasemyselfbuild();
        if (this$purchasemyselfbuild == null ? other$purchasemyselfbuild != null : !this$purchasemyselfbuild.equals(other$purchasemyselfbuild)) {
            return false;
        }
        String this$owncapital = this.getOwncapital();
        String other$owncapital = other.getOwncapital();
        if (this$owncapital == null ? other$owncapital != null : !this$owncapital.equals(other$owncapital)) {
            return false;
        }
        String this$nonowncaptial = this.getNonowncaptial();
        String other$nonowncaptial = other.getNonowncaptial();
        if (this$nonowncaptial == null ? other$nonowncaptial != null : !this$nonowncaptial.equals(other$nonowncaptial)) {
            return false;
        }
        String this$groupcaptialsource = this.getGroupcaptialsource();
        String other$groupcaptialsource = other.getGroupcaptialsource();
        if (this$groupcaptialsource == null ? other$groupcaptialsource != null : !this$groupcaptialsource.equals(other$groupcaptialsource)) {
            return false;
        }
        String this$submitter = this.getSubmitter();
        String other$submitter = other.getSubmitter();
        if (this$submitter == null ? other$submitter != null : !this$submitter.equals(other$submitter)) {
            return false;
        }
        String this$mettingtime = this.getMettingtime();
        String other$mettingtime = other.getMettingtime();
        if (this$mettingtime == null ? other$mettingtime != null : !this$mettingtime.equals(other$mettingtime)) {
            return false;
        }
        String this$freshflag = this.getFreshflag();
        String other$freshflag = other.getFreshflag();
        return !(this$freshflag == null ? other$freshflag != null : !this$freshflag.equals(other$freshflag));
    }

    protected boolean canEqual(Object other) {
        return other instanceof GroupvotinginfoEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $isinvest = this.getIsinvest();
        result = result * 59 + ($isinvest == null ? 43 : ((Object)$isinvest).hashCode());
        Long $budgettype = this.getBudgettype();
        result = result * 59 + ($budgettype == null ? 43 : ((Object)$budgettype).hashCode());
        Long $productid = this.getProductid();
        result = result * 59 + ($productid == null ? 43 : ((Object)$productid).hashCode());
        String $purchasemyselfbuild = this.getPurchasemyselfbuild();
        result = result * 59 + ($purchasemyselfbuild == null ? 43 : $purchasemyselfbuild.hashCode());
        String $owncapital = this.getOwncapital();
        result = result * 59 + ($owncapital == null ? 43 : $owncapital.hashCode());
        String $nonowncaptial = this.getNonowncaptial();
        result = result * 59 + ($nonowncaptial == null ? 43 : $nonowncaptial.hashCode());
        String $groupcaptialsource = this.getGroupcaptialsource();
        result = result * 59 + ($groupcaptialsource == null ? 43 : $groupcaptialsource.hashCode());
        String $submitter = this.getSubmitter();
        result = result * 59 + ($submitter == null ? 43 : $submitter.hashCode());
        String $mettingtime = this.getMettingtime();
        result = result * 59 + ($mettingtime == null ? 43 : $mettingtime.hashCode());
        String $freshflag = this.getFreshflag();
        result = result * 59 + ($freshflag == null ? 43 : $freshflag.hashCode());
        return result;
    }

    public String toString() {
        return "GroupvotinginfoEntity(id=" + this.getId() + ", isinvest=" + this.getIsinvest() + ", budgettype=" + this.getBudgettype() + ", purchasemyselfbuild=" + this.getPurchasemyselfbuild() + ", owncapital=" + this.getOwncapital() + ", nonowncaptial=" + this.getNonowncaptial() + ", groupcaptialsource=" + this.getGroupcaptialsource() + ", productid=" + this.getProductid() + ", submitter=" + this.getSubmitter() + ", mettingtime=" + this.getMettingtime() + ", freshflag=" + this.getFreshflag() + ")";
    }
}

