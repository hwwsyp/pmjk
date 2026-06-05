/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.otherinfo.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="pmjk_otherinfo")
public class OtherinfoEntity {
    private Long id;
    private String basicinfo;
    private String bottomprojectinfo;
    private String repaymentsource;
    private String repaymentmethod;
    private String capitalpurpose;
    private String incomedistributioncluase;
    private String coreterms;
    private String otherimportterms;
    private String othersubscribers;
    private String remark;
    private Long versionnum;
    private Long productid;

    public Long getId() {
        return this.id;
    }

    public String getBasicinfo() {
        return this.basicinfo;
    }

    public String getBottomprojectinfo() {
        return this.bottomprojectinfo;
    }

    public String getRepaymentsource() {
        return this.repaymentsource;
    }

    public String getRepaymentmethod() {
        return this.repaymentmethod;
    }

    public String getCapitalpurpose() {
        return this.capitalpurpose;
    }

    public String getIncomedistributioncluase() {
        return this.incomedistributioncluase;
    }

    public String getCoreterms() {
        return this.coreterms;
    }

    public String getOtherimportterms() {
        return this.otherimportterms;
    }

    public String getOthersubscribers() {
        return this.othersubscribers;
    }

    public String getRemark() {
        return this.remark;
    }

    public Long getVersionnum() {
        return this.versionnum;
    }

    public Long getProductid() {
        return this.productid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBasicinfo(String basicinfo) {
        this.basicinfo = basicinfo;
    }

    public void setBottomprojectinfo(String bottomprojectinfo) {
        this.bottomprojectinfo = bottomprojectinfo;
    }

    public void setRepaymentsource(String repaymentsource) {
        this.repaymentsource = repaymentsource;
    }

    public void setRepaymentmethod(String repaymentmethod) {
        this.repaymentmethod = repaymentmethod;
    }

    public void setCapitalpurpose(String capitalpurpose) {
        this.capitalpurpose = capitalpurpose;
    }

    public void setIncomedistributioncluase(String incomedistributioncluase) {
        this.incomedistributioncluase = incomedistributioncluase;
    }

    public void setCoreterms(String coreterms) {
        this.coreterms = coreterms;
    }

    public void setOtherimportterms(String otherimportterms) {
        this.otherimportterms = otherimportterms;
    }

    public void setOthersubscribers(String othersubscribers) {
        this.othersubscribers = othersubscribers;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setVersionnum(Long versionnum) {
        this.versionnum = versionnum;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OtherinfoEntity)) {
            return false;
        }
        OtherinfoEntity other = (OtherinfoEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
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
        String this$basicinfo = this.getBasicinfo();
        String other$basicinfo = other.getBasicinfo();
        if (this$basicinfo == null ? other$basicinfo != null : !this$basicinfo.equals(other$basicinfo)) {
            return false;
        }
        String this$bottomprojectinfo = this.getBottomprojectinfo();
        String other$bottomprojectinfo = other.getBottomprojectinfo();
        if (this$bottomprojectinfo == null ? other$bottomprojectinfo != null : !this$bottomprojectinfo.equals(other$bottomprojectinfo)) {
            return false;
        }
        String this$repaymentsource = this.getRepaymentsource();
        String other$repaymentsource = other.getRepaymentsource();
        if (this$repaymentsource == null ? other$repaymentsource != null : !this$repaymentsource.equals(other$repaymentsource)) {
            return false;
        }
        String this$repaymentmethod = this.getRepaymentmethod();
        String other$repaymentmethod = other.getRepaymentmethod();
        if (this$repaymentmethod == null ? other$repaymentmethod != null : !this$repaymentmethod.equals(other$repaymentmethod)) {
            return false;
        }
        String this$capitalpurpose = this.getCapitalpurpose();
        String other$capitalpurpose = other.getCapitalpurpose();
        if (this$capitalpurpose == null ? other$capitalpurpose != null : !this$capitalpurpose.equals(other$capitalpurpose)) {
            return false;
        }
        String this$incomedistributioncluase = this.getIncomedistributioncluase();
        String other$incomedistributioncluase = other.getIncomedistributioncluase();
        if (this$incomedistributioncluase == null ? other$incomedistributioncluase != null : !this$incomedistributioncluase.equals(other$incomedistributioncluase)) {
            return false;
        }
        String this$coreterms = this.getCoreterms();
        String other$coreterms = other.getCoreterms();
        if (this$coreterms == null ? other$coreterms != null : !this$coreterms.equals(other$coreterms)) {
            return false;
        }
        String this$otherimportterms = this.getOtherimportterms();
        String other$otherimportterms = other.getOtherimportterms();
        if (this$otherimportterms == null ? other$otherimportterms != null : !this$otherimportterms.equals(other$otherimportterms)) {
            return false;
        }
        String this$othersubscribers = this.getOthersubscribers();
        String other$othersubscribers = other.getOthersubscribers();
        if (this$othersubscribers == null ? other$othersubscribers != null : !this$othersubscribers.equals(other$othersubscribers)) {
            return false;
        }
        String this$remark = this.getRemark();
        String other$remark = other.getRemark();
        return !(this$remark == null ? other$remark != null : !this$remark.equals(other$remark));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OtherinfoEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $versionnum = this.getVersionnum();
        result = result * 59 + ($versionnum == null ? 43 : ((Object)$versionnum).hashCode());
        Long $productid = this.getProductid();
        result = result * 59 + ($productid == null ? 43 : ((Object)$productid).hashCode());
        String $basicinfo = this.getBasicinfo();
        result = result * 59 + ($basicinfo == null ? 43 : $basicinfo.hashCode());
        String $bottomprojectinfo = this.getBottomprojectinfo();
        result = result * 59 + ($bottomprojectinfo == null ? 43 : $bottomprojectinfo.hashCode());
        String $repaymentsource = this.getRepaymentsource();
        result = result * 59 + ($repaymentsource == null ? 43 : $repaymentsource.hashCode());
        String $repaymentmethod = this.getRepaymentmethod();
        result = result * 59 + ($repaymentmethod == null ? 43 : $repaymentmethod.hashCode());
        String $capitalpurpose = this.getCapitalpurpose();
        result = result * 59 + ($capitalpurpose == null ? 43 : $capitalpurpose.hashCode());
        String $incomedistributioncluase = this.getIncomedistributioncluase();
        result = result * 59 + ($incomedistributioncluase == null ? 43 : $incomedistributioncluase.hashCode());
        String $coreterms = this.getCoreterms();
        result = result * 59 + ($coreterms == null ? 43 : $coreterms.hashCode());
        String $otherimportterms = this.getOtherimportterms();
        result = result * 59 + ($otherimportterms == null ? 43 : $otherimportterms.hashCode());
        String $othersubscribers = this.getOthersubscribers();
        result = result * 59 + ($othersubscribers == null ? 43 : $othersubscribers.hashCode());
        String $remark = this.getRemark();
        result = result * 59 + ($remark == null ? 43 : $remark.hashCode());
        return result;
    }

    public String toString() {
        return "OtherinfoEntity(id=" + this.getId() + ", basicinfo=" + this.getBasicinfo() + ", bottomprojectinfo=" + this.getBottomprojectinfo() + ", repaymentsource=" + this.getRepaymentsource() + ", repaymentmethod=" + this.getRepaymentmethod() + ", capitalpurpose=" + this.getCapitalpurpose() + ", incomedistributioncluase=" + this.getIncomedistributioncluase() + ", coreterms=" + this.getCoreterms() + ", otherimportterms=" + this.getOtherimportterms() + ", othersubscribers=" + this.getOthersubscribers() + ", remark=" + this.getRemark() + ", versionnum=" + this.getVersionnum() + ", productid=" + this.getProductid() + ")";
    }
}

