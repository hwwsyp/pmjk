/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.stakeholder.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;

@TableName(value="pmjk_stakeholder")
public class StakeholderEntity {
    private Long id;
    private Long investmentmanagerid;
    private Long administrationmanagerid;
    private Long investmentadviserid;
    private Long custodianbankid;
    private Long tradingbrokerid;
    private Long financingsubjectid;
    private Long repaymentsubjectid;
    private Long independentsupervisorid;
    private Long trusteemanagerid;
    private Long originalholderid;
    private Long coinitiatorid;
    private Long projectorgid;
    private Long fundmanagerid;
    private Long trustcompanyid;
    private Long generalpartnerid;
    private Long specialpartnerid;
    private Long versionnum;
    private Long productid;
    private Date createtimestamp;
    private Date updatetimestamp;
    private String islock;

    public Long getId() {
        return this.id;
    }

    public Long getInvestmentmanagerid() {
        return this.investmentmanagerid;
    }

    public Long getAdministrationmanagerid() {
        return this.administrationmanagerid;
    }

    public Long getInvestmentadviserid() {
        return this.investmentadviserid;
    }

    public Long getCustodianbankid() {
        return this.custodianbankid;
    }

    public Long getTradingbrokerid() {
        return this.tradingbrokerid;
    }

    public Long getFinancingsubjectid() {
        return this.financingsubjectid;
    }

    public Long getRepaymentsubjectid() {
        return this.repaymentsubjectid;
    }

    public Long getIndependentsupervisorid() {
        return this.independentsupervisorid;
    }

    public Long getTrusteemanagerid() {
        return this.trusteemanagerid;
    }

    public Long getOriginalholderid() {
        return this.originalholderid;
    }

    public Long getCoinitiatorid() {
        return this.coinitiatorid;
    }

    public Long getProjectorgid() {
        return this.projectorgid;
    }

    public Long getFundmanagerid() {
        return this.fundmanagerid;
    }

    public Long getTrustcompanyid() {
        return this.trustcompanyid;
    }

    public Long getGeneralpartnerid() {
        return this.generalpartnerid;
    }

    public Long getSpecialpartnerid() {
        return this.specialpartnerid;
    }

    public Long getVersionnum() {
        return this.versionnum;
    }

    public Long getProductid() {
        return this.productid;
    }

    public Date getCreatetimestamp() {
        return this.createtimestamp;
    }

    public Date getUpdatetimestamp() {
        return this.updatetimestamp;
    }

    public String getIslock() {
        return this.islock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInvestmentmanagerid(Long investmentmanagerid) {
        this.investmentmanagerid = investmentmanagerid;
    }

    public void setAdministrationmanagerid(Long administrationmanagerid) {
        this.administrationmanagerid = administrationmanagerid;
    }

    public void setInvestmentadviserid(Long investmentadviserid) {
        this.investmentadviserid = investmentadviserid;
    }

    public void setCustodianbankid(Long custodianbankid) {
        this.custodianbankid = custodianbankid;
    }

    public void setTradingbrokerid(Long tradingbrokerid) {
        this.tradingbrokerid = tradingbrokerid;
    }

    public void setFinancingsubjectid(Long financingsubjectid) {
        this.financingsubjectid = financingsubjectid;
    }

    public void setRepaymentsubjectid(Long repaymentsubjectid) {
        this.repaymentsubjectid = repaymentsubjectid;
    }

    public void setIndependentsupervisorid(Long independentsupervisorid) {
        this.independentsupervisorid = independentsupervisorid;
    }

    public void setTrusteemanagerid(Long trusteemanagerid) {
        this.trusteemanagerid = trusteemanagerid;
    }

    public void setOriginalholderid(Long originalholderid) {
        this.originalholderid = originalholderid;
    }

    public void setCoinitiatorid(Long coinitiatorid) {
        this.coinitiatorid = coinitiatorid;
    }

    public void setProjectorgid(Long projectorgid) {
        this.projectorgid = projectorgid;
    }

    public void setFundmanagerid(Long fundmanagerid) {
        this.fundmanagerid = fundmanagerid;
    }

    public void setTrustcompanyid(Long trustcompanyid) {
        this.trustcompanyid = trustcompanyid;
    }

    public void setGeneralpartnerid(Long generalpartnerid) {
        this.generalpartnerid = generalpartnerid;
    }

    public void setSpecialpartnerid(Long specialpartnerid) {
        this.specialpartnerid = specialpartnerid;
    }

    public void setVersionnum(Long versionnum) {
        this.versionnum = versionnum;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public void setCreatetimestamp(Date createtimestamp) {
        this.createtimestamp = createtimestamp;
    }

    public void setUpdatetimestamp(Date updatetimestamp) {
        this.updatetimestamp = updatetimestamp;
    }

    public void setIslock(String islock) {
        this.islock = islock;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof StakeholderEntity)) {
            return false;
        }
        StakeholderEntity other = (StakeholderEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$investmentmanagerid = this.getInvestmentmanagerid();
        Long other$investmentmanagerid = other.getInvestmentmanagerid();
        if (this$investmentmanagerid == null ? other$investmentmanagerid != null : !((Object)this$investmentmanagerid).equals(other$investmentmanagerid)) {
            return false;
        }
        Long this$administrationmanagerid = this.getAdministrationmanagerid();
        Long other$administrationmanagerid = other.getAdministrationmanagerid();
        if (this$administrationmanagerid == null ? other$administrationmanagerid != null : !((Object)this$administrationmanagerid).equals(other$administrationmanagerid)) {
            return false;
        }
        Long this$investmentadviserid = this.getInvestmentadviserid();
        Long other$investmentadviserid = other.getInvestmentadviserid();
        if (this$investmentadviserid == null ? other$investmentadviserid != null : !((Object)this$investmentadviserid).equals(other$investmentadviserid)) {
            return false;
        }
        Long this$custodianbankid = this.getCustodianbankid();
        Long other$custodianbankid = other.getCustodianbankid();
        if (this$custodianbankid == null ? other$custodianbankid != null : !((Object)this$custodianbankid).equals(other$custodianbankid)) {
            return false;
        }
        Long this$tradingbrokerid = this.getTradingbrokerid();
        Long other$tradingbrokerid = other.getTradingbrokerid();
        if (this$tradingbrokerid == null ? other$tradingbrokerid != null : !((Object)this$tradingbrokerid).equals(other$tradingbrokerid)) {
            return false;
        }
        Long this$financingsubjectid = this.getFinancingsubjectid();
        Long other$financingsubjectid = other.getFinancingsubjectid();
        if (this$financingsubjectid == null ? other$financingsubjectid != null : !((Object)this$financingsubjectid).equals(other$financingsubjectid)) {
            return false;
        }
        Long this$repaymentsubjectid = this.getRepaymentsubjectid();
        Long other$repaymentsubjectid = other.getRepaymentsubjectid();
        if (this$repaymentsubjectid == null ? other$repaymentsubjectid != null : !((Object)this$repaymentsubjectid).equals(other$repaymentsubjectid)) {
            return false;
        }
        Long this$independentsupervisorid = this.getIndependentsupervisorid();
        Long other$independentsupervisorid = other.getIndependentsupervisorid();
        if (this$independentsupervisorid == null ? other$independentsupervisorid != null : !((Object)this$independentsupervisorid).equals(other$independentsupervisorid)) {
            return false;
        }
        Long this$trusteemanagerid = this.getTrusteemanagerid();
        Long other$trusteemanagerid = other.getTrusteemanagerid();
        if (this$trusteemanagerid == null ? other$trusteemanagerid != null : !((Object)this$trusteemanagerid).equals(other$trusteemanagerid)) {
            return false;
        }
        Long this$originalholderid = this.getOriginalholderid();
        Long other$originalholderid = other.getOriginalholderid();
        if (this$originalholderid == null ? other$originalholderid != null : !((Object)this$originalholderid).equals(other$originalholderid)) {
            return false;
        }
        Long this$coinitiatorid = this.getCoinitiatorid();
        Long other$coinitiatorid = other.getCoinitiatorid();
        if (this$coinitiatorid == null ? other$coinitiatorid != null : !((Object)this$coinitiatorid).equals(other$coinitiatorid)) {
            return false;
        }
        Long this$projectorgid = this.getProjectorgid();
        Long other$projectorgid = other.getProjectorgid();
        if (this$projectorgid == null ? other$projectorgid != null : !((Object)this$projectorgid).equals(other$projectorgid)) {
            return false;
        }
        Long this$fundmanagerid = this.getFundmanagerid();
        Long other$fundmanagerid = other.getFundmanagerid();
        if (this$fundmanagerid == null ? other$fundmanagerid != null : !((Object)this$fundmanagerid).equals(other$fundmanagerid)) {
            return false;
        }
        Long this$trustcompanyid = this.getTrustcompanyid();
        Long other$trustcompanyid = other.getTrustcompanyid();
        if (this$trustcompanyid == null ? other$trustcompanyid != null : !((Object)this$trustcompanyid).equals(other$trustcompanyid)) {
            return false;
        }
        Long this$generalpartnerid = this.getGeneralpartnerid();
        Long other$generalpartnerid = other.getGeneralpartnerid();
        if (this$generalpartnerid == null ? other$generalpartnerid != null : !((Object)this$generalpartnerid).equals(other$generalpartnerid)) {
            return false;
        }
        Long this$specialpartnerid = this.getSpecialpartnerid();
        Long other$specialpartnerid = other.getSpecialpartnerid();
        if (this$specialpartnerid == null ? other$specialpartnerid != null : !((Object)this$specialpartnerid).equals(other$specialpartnerid)) {
            return false;
        }
        Long this$versionnum = this.getVersionnum();
        Long other$versionnum = other.getVersionnum();
        if (this$versionnum == null ? other$versionnum != null : !((Object)this$versionnum).equals(other$versionnum)) {
            return false;
        }
        Long this$productid = this.getProductid();
        Long other$productid = other.getProductid();
        if (this$productid == null ? other$productid != null : !((Object)this$productid).equals(other$productid)) {
            return false;
        }
        Date this$createtimestamp = this.getCreatetimestamp();
        Date other$createtimestamp = other.getCreatetimestamp();
        if (this$createtimestamp == null ? other$createtimestamp != null : !((Object)this$createtimestamp).equals(other$createtimestamp)) {
            return false;
        }
        Date this$updatetimestamp = this.getUpdatetimestamp();
        Date other$updatetimestamp = other.getUpdatetimestamp();
        if (this$updatetimestamp == null ? other$updatetimestamp != null : !((Object)this$updatetimestamp).equals(other$updatetimestamp)) {
            return false;
        }
        String this$islock = this.getIslock();
        String other$islock = other.getIslock();
        return !(this$islock == null ? other$islock != null : !this$islock.equals(other$islock));
    }

    protected boolean canEqual(Object other) {
        return other instanceof StakeholderEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $investmentmanagerid = this.getInvestmentmanagerid();
        result = result * 59 + ($investmentmanagerid == null ? 43 : ((Object)$investmentmanagerid).hashCode());
        Long $administrationmanagerid = this.getAdministrationmanagerid();
        result = result * 59 + ($administrationmanagerid == null ? 43 : ((Object)$administrationmanagerid).hashCode());
        Long $investmentadviserid = this.getInvestmentadviserid();
        result = result * 59 + ($investmentadviserid == null ? 43 : ((Object)$investmentadviserid).hashCode());
        Long $custodianbankid = this.getCustodianbankid();
        result = result * 59 + ($custodianbankid == null ? 43 : ((Object)$custodianbankid).hashCode());
        Long $tradingbrokerid = this.getTradingbrokerid();
        result = result * 59 + ($tradingbrokerid == null ? 43 : ((Object)$tradingbrokerid).hashCode());
        Long $financingsubjectid = this.getFinancingsubjectid();
        result = result * 59 + ($financingsubjectid == null ? 43 : ((Object)$financingsubjectid).hashCode());
        Long $repaymentsubjectid = this.getRepaymentsubjectid();
        result = result * 59 + ($repaymentsubjectid == null ? 43 : ((Object)$repaymentsubjectid).hashCode());
        Long $independentsupervisorid = this.getIndependentsupervisorid();
        result = result * 59 + ($independentsupervisorid == null ? 43 : ((Object)$independentsupervisorid).hashCode());
        Long $trusteemanagerid = this.getTrusteemanagerid();
        result = result * 59 + ($trusteemanagerid == null ? 43 : ((Object)$trusteemanagerid).hashCode());
        Long $originalholderid = this.getOriginalholderid();
        result = result * 59 + ($originalholderid == null ? 43 : ((Object)$originalholderid).hashCode());
        Long $coinitiatorid = this.getCoinitiatorid();
        result = result * 59 + ($coinitiatorid == null ? 43 : ((Object)$coinitiatorid).hashCode());
        Long $projectorgid = this.getProjectorgid();
        result = result * 59 + ($projectorgid == null ? 43 : ((Object)$projectorgid).hashCode());
        Long $fundmanagerid = this.getFundmanagerid();
        result = result * 59 + ($fundmanagerid == null ? 43 : ((Object)$fundmanagerid).hashCode());
        Long $trustcompanyid = this.getTrustcompanyid();
        result = result * 59 + ($trustcompanyid == null ? 43 : ((Object)$trustcompanyid).hashCode());
        Long $generalpartnerid = this.getGeneralpartnerid();
        result = result * 59 + ($generalpartnerid == null ? 43 : ((Object)$generalpartnerid).hashCode());
        Long $specialpartnerid = this.getSpecialpartnerid();
        result = result * 59 + ($specialpartnerid == null ? 43 : ((Object)$specialpartnerid).hashCode());
        Long $versionnum = this.getVersionnum();
        result = result * 59 + ($versionnum == null ? 43 : ((Object)$versionnum).hashCode());
        Long $productid = this.getProductid();
        result = result * 59 + ($productid == null ? 43 : ((Object)$productid).hashCode());
        Date $createtimestamp = this.getCreatetimestamp();
        result = result * 59 + ($createtimestamp == null ? 43 : ((Object)$createtimestamp).hashCode());
        Date $updatetimestamp = this.getUpdatetimestamp();
        result = result * 59 + ($updatetimestamp == null ? 43 : ((Object)$updatetimestamp).hashCode());
        String $islock = this.getIslock();
        result = result * 59 + ($islock == null ? 43 : $islock.hashCode());
        return result;
    }

    public String toString() {
        return "StakeholderEntity(id=" + this.getId() + ", investmentmanagerid=" + this.getInvestmentmanagerid() + ", administrationmanagerid=" + this.getAdministrationmanagerid() + ", investmentadviserid=" + this.getInvestmentadviserid() + ", custodianbankid=" + this.getCustodianbankid() + ", tradingbrokerid=" + this.getTradingbrokerid() + ", financingsubjectid=" + this.getFinancingsubjectid() + ", repaymentsubjectid=" + this.getRepaymentsubjectid() + ", independentsupervisorid=" + this.getIndependentsupervisorid() + ", trusteemanagerid=" + this.getTrusteemanagerid() + ", originalholderid=" + this.getOriginalholderid() + ", coinitiatorid=" + this.getCoinitiatorid() + ", projectorgid=" + this.getProjectorgid() + ", fundmanagerid=" + this.getFundmanagerid() + ", trustcompanyid=" + this.getTrustcompanyid() + ", generalpartnerid=" + this.getGeneralpartnerid() + ", specialpartnerid=" + this.getSpecialpartnerid() + ", versionnum=" + this.getVersionnum() + ", productid=" + this.getProductid() + ", createtimestamp=" + this.getCreatetimestamp() + ", updatetimestamp=" + this.getUpdatetimestamp() + ", islock=" + this.getIslock() + ")";
    }
}

