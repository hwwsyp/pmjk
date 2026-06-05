/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 *  com.fasterxml.jackson.annotation.JsonFormat
 */
package com.tpfh.fintech.modules.pmjk.cashflowdata.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

@TableName(value="pmjk_cashflowdata")
public class CashflowdataEntity {
    private Long id;
    private String productshortname;
    private String txamount;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date txdate;
    private String txtype;
    private String currency;
    private String beneficiary;
    private String interate;
    private String integain;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startdate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date enddate;
    private String remark;

    public Long getId() {
        return this.id;
    }

    public String getProductshortname() {
        return this.productshortname;
    }

    public String getTxamount() {
        return this.txamount;
    }

    public Date getTxdate() {
        return this.txdate;
    }

    public String getTxtype() {
        return this.txtype;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getBeneficiary() {
        return this.beneficiary;
    }

    public String getInterate() {
        return this.interate;
    }

    public String getIntegain() {
        return this.integain;
    }

    public Date getStartdate() {
        return this.startdate;
    }

    public Date getEnddate() {
        return this.enddate;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductshortname(String productshortname) {
        this.productshortname = productshortname;
    }

    public void setTxamount(String txamount) {
        this.txamount = txamount;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public void setTxdate(Date txdate) {
        this.txdate = txdate;
    }

    public void setTxtype(String txtype) {
        this.txtype = txtype;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public void setInterate(String interate) {
        this.interate = interate;
    }

    public void setIntegain(String integain) {
        this.integain = integain;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CashflowdataEntity)) {
            return false;
        }
        CashflowdataEntity other = (CashflowdataEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        String this$productshortname = this.getProductshortname();
        String other$productshortname = other.getProductshortname();
        if (this$productshortname == null ? other$productshortname != null : !this$productshortname.equals(other$productshortname)) {
            return false;
        }
        String this$txamount = this.getTxamount();
        String other$txamount = other.getTxamount();
        if (this$txamount == null ? other$txamount != null : !this$txamount.equals(other$txamount)) {
            return false;
        }
        Date this$txdate = this.getTxdate();
        Date other$txdate = other.getTxdate();
        if (this$txdate == null ? other$txdate != null : !((Object)this$txdate).equals(other$txdate)) {
            return false;
        }
        String this$txtype = this.getTxtype();
        String other$txtype = other.getTxtype();
        if (this$txtype == null ? other$txtype != null : !this$txtype.equals(other$txtype)) {
            return false;
        }
        String this$currency = this.getCurrency();
        String other$currency = other.getCurrency();
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) {
            return false;
        }
        String this$beneficiary = this.getBeneficiary();
        String other$beneficiary = other.getBeneficiary();
        if (this$beneficiary == null ? other$beneficiary != null : !this$beneficiary.equals(other$beneficiary)) {
            return false;
        }
        String this$interate = this.getInterate();
        String other$interate = other.getInterate();
        if (this$interate == null ? other$interate != null : !this$interate.equals(other$interate)) {
            return false;
        }
        String this$integain = this.getIntegain();
        String other$integain = other.getIntegain();
        if (this$integain == null ? other$integain != null : !this$integain.equals(other$integain)) {
            return false;
        }
        Date this$startdate = this.getStartdate();
        Date other$startdate = other.getStartdate();
        if (this$startdate == null ? other$startdate != null : !((Object)this$startdate).equals(other$startdate)) {
            return false;
        }
        Date this$enddate = this.getEnddate();
        Date other$enddate = other.getEnddate();
        if (this$enddate == null ? other$enddate != null : !((Object)this$enddate).equals(other$enddate)) {
            return false;
        }
        String this$remark = this.getRemark();
        String other$remark = other.getRemark();
        return !(this$remark == null ? other$remark != null : !this$remark.equals(other$remark));
    }

    protected boolean canEqual(Object other) {
        return other instanceof CashflowdataEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $productshortname = this.getProductshortname();
        result = result * 59 + ($productshortname == null ? 43 : $productshortname.hashCode());
        String $txamount = this.getTxamount();
        result = result * 59 + ($txamount == null ? 43 : $txamount.hashCode());
        Date $txdate = this.getTxdate();
        result = result * 59 + ($txdate == null ? 43 : ((Object)$txdate).hashCode());
        String $txtype = this.getTxtype();
        result = result * 59 + ($txtype == null ? 43 : $txtype.hashCode());
        String $currency = this.getCurrency();
        result = result * 59 + ($currency == null ? 43 : $currency.hashCode());
        String $beneficiary = this.getBeneficiary();
        result = result * 59 + ($beneficiary == null ? 43 : $beneficiary.hashCode());
        String $interate = this.getInterate();
        result = result * 59 + ($interate == null ? 43 : $interate.hashCode());
        String $integain = this.getIntegain();
        result = result * 59 + ($integain == null ? 43 : $integain.hashCode());
        Date $startdate = this.getStartdate();
        result = result * 59 + ($startdate == null ? 43 : ((Object)$startdate).hashCode());
        Date $enddate = this.getEnddate();
        result = result * 59 + ($enddate == null ? 43 : ((Object)$enddate).hashCode());
        String $remark = this.getRemark();
        result = result * 59 + ($remark == null ? 43 : $remark.hashCode());
        return result;
    }

    public String toString() {
        return "CashflowdataEntity(id=" + this.getId() + ", productshortname=" + this.getProductshortname() + ", txamount=" + this.getTxamount() + ", txdate=" + this.getTxdate() + ", txtype=" + this.getTxtype() + ", currency=" + this.getCurrency() + ", beneficiary=" + this.getBeneficiary() + ", interate=" + this.getInterate() + ", integain=" + this.getIntegain() + ", startdate=" + this.getStartdate() + ", enddate=" + this.getEnddate() + ", remark=" + this.getRemark() + ")";
    }
}

