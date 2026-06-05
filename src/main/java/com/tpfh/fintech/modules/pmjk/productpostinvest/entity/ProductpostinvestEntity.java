/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 *  com.fasterxml.jackson.annotation.JsonFormat
 */
package com.tpfh.fintech.modules.pmjk.productpostinvest.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

@TableName(value="pmjk_productpostinvest")
public class ProductpostinvestEntity {
    private Long id;
    private Long productid;
    private String matter;
    private String matterremark;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date createtimestamp;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date updatetimestamp;
    private String submitter;

    public Long getId() {
        return this.id;
    }

    public Long getProductid() {
        return this.productid;
    }

    public String getMatter() {
        return this.matter;
    }

    public String getMatterremark() {
        return this.matterremark;
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

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public void setMatterremark(String matterremark) {
        this.matterremark = matterremark;
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
        if (!(o instanceof ProductpostinvestEntity)) {
            return false;
        }
        ProductpostinvestEntity other = (ProductpostinvestEntity)o;
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
        String this$matter = this.getMatter();
        String other$matter = other.getMatter();
        if (this$matter == null ? other$matter != null : !this$matter.equals(other$matter)) {
            return false;
        }
        String this$matterremark = this.getMatterremark();
        String other$matterremark = other.getMatterremark();
        if (this$matterremark == null ? other$matterremark != null : !this$matterremark.equals(other$matterremark)) {
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
        return other instanceof ProductpostinvestEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $productid = this.getProductid();
        result = result * 59 + ($productid == null ? 43 : ((Object)$productid).hashCode());
        String $matter = this.getMatter();
        result = result * 59 + ($matter == null ? 43 : $matter.hashCode());
        String $matterremark = this.getMatterremark();
        result = result * 59 + ($matterremark == null ? 43 : $matterremark.hashCode());
        Date $createtimestamp = this.getCreatetimestamp();
        result = result * 59 + ($createtimestamp == null ? 43 : ((Object)$createtimestamp).hashCode());
        Date $updatetimestamp = this.getUpdatetimestamp();
        result = result * 59 + ($updatetimestamp == null ? 43 : ((Object)$updatetimestamp).hashCode());
        String $submitter = this.getSubmitter();
        result = result * 59 + ($submitter == null ? 43 : $submitter.hashCode());
        return result;
    }

    public String toString() {
        return "ProductpostinvestEntity(id=" + this.getId() + ", productid=" + this.getProductid() + ", matter=" + this.getMatter() + ", matterremark=" + this.getMatterremark() + ", createtimestamp=" + this.getCreatetimestamp() + ", updatetimestamp=" + this.getUpdatetimestamp() + ", submitter=" + this.getSubmitter() + ")";
    }
}

