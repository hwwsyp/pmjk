/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 *  com.fasterxml.jackson.annotation.JsonFormat
 */
package com.tpfh.fintech.modules.pmjk.productrisk.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

@TableName(value="pmjk_productrisk")
public class ProductriskEntity {
    private Long id;
    private String breif;
    private Long status;
    private Long preriskid;
    private Long risklevel;
    private Long productid;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date createtimestamp;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date updatetimestamp;
    private String submitter;

    public Long getId() {
        return this.id;
    }

    public String getBreif() {
        return this.breif;
    }

    public Long getStatus() {
        return this.status;
    }

    public Long getPreriskid() {
        return this.preriskid;
    }

    public Long getRisklevel() {
        return this.risklevel;
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

    public String getSubmitter() {
        return this.submitter;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBreif(String breif) {
        this.breif = breif;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public void setPreriskid(Long preriskid) {
        this.preriskid = preriskid;
    }

    public void setRisklevel(Long risklevel) {
        this.risklevel = risklevel;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    public void setCreatetimestamp(Date createtimestamp) {
        this.createtimestamp = createtimestamp;
    }

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    public void setUpdatetimestamp(Date updatetimestamp) {
        this.updatetimestamp = updatetimestamp;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProductriskEntity)) {
            return false;
        }
        ProductriskEntity other = (ProductriskEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$status = this.getStatus();
        Long other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)this$status).equals(other$status)) {
            return false;
        }
        Long this$preriskid = this.getPreriskid();
        Long other$preriskid = other.getPreriskid();
        if (this$preriskid == null ? other$preriskid != null : !((Object)this$preriskid).equals(other$preriskid)) {
            return false;
        }
        Long this$risklevel = this.getRisklevel();
        Long other$risklevel = other.getRisklevel();
        if (this$risklevel == null ? other$risklevel != null : !((Object)this$risklevel).equals(other$risklevel)) {
            return false;
        }
        Long this$productid = this.getProductid();
        Long other$productid = other.getProductid();
        if (this$productid == null ? other$productid != null : !((Object)this$productid).equals(other$productid)) {
            return false;
        }
        String this$breif = this.getBreif();
        String other$breif = other.getBreif();
        if (this$breif == null ? other$breif != null : !this$breif.equals(other$breif)) {
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
        String this$submitter = this.getSubmitter();
        String other$submitter = other.getSubmitter();
        return !(this$submitter == null ? other$submitter != null : !this$submitter.equals(other$submitter));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProductriskEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)$status).hashCode());
        Long $preriskid = this.getPreriskid();
        result = result * 59 + ($preriskid == null ? 43 : ((Object)$preriskid).hashCode());
        Long $risklevel = this.getRisklevel();
        result = result * 59 + ($risklevel == null ? 43 : ((Object)$risklevel).hashCode());
        Long $productid = this.getProductid();
        result = result * 59 + ($productid == null ? 43 : ((Object)$productid).hashCode());
        String $breif = this.getBreif();
        result = result * 59 + ($breif == null ? 43 : $breif.hashCode());
        Date $createtimestamp = this.getCreatetimestamp();
        result = result * 59 + ($createtimestamp == null ? 43 : ((Object)$createtimestamp).hashCode());
        Date $updatetimestamp = this.getUpdatetimestamp();
        result = result * 59 + ($updatetimestamp == null ? 43 : ((Object)$updatetimestamp).hashCode());
        String $submitter = this.getSubmitter();
        result = result * 59 + ($submitter == null ? 43 : $submitter.hashCode());
        return result;
    }

    public String toString() {
        return "ProductriskEntity(id=" + this.getId() + ", breif=" + this.getBreif() + ", status=" + this.getStatus() + ", preriskid=" + this.getPreriskid() + ", risklevel=" + this.getRisklevel() + ", productid=" + this.getProductid() + ", createtimestamp=" + this.getCreatetimestamp() + ", updatetimestamp=" + this.getUpdatetimestamp() + ", submitter=" + this.getSubmitter() + ")";
    }
}

