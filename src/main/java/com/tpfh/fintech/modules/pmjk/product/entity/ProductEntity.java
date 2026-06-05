/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.product.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="pmjk_product")
public class ProductEntity {
    private Long id;
    private String producttype;
    private Long productinfoversion;
    private Long stakeholderversion;
    private Long releaseversion;
    private Long creditenhanceversion;
    private Long otherversion;
    private String createuser;
    private String removeflag;

    public Long getId() {
        return this.id;
    }

    public String getProducttype() {
        return this.producttype;
    }

    public Long getProductinfoversion() {
        return this.productinfoversion;
    }

    public Long getStakeholderversion() {
        return this.stakeholderversion;
    }

    public Long getReleaseversion() {
        return this.releaseversion;
    }

    public Long getCreditenhanceversion() {
        return this.creditenhanceversion;
    }

    public Long getOtherversion() {
        return this.otherversion;
    }

    public String getCreateuser() {
        return this.createuser;
    }

    public String getRemoveflag() {
        return this.removeflag;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public void setProductinfoversion(Long productinfoversion) {
        this.productinfoversion = productinfoversion;
    }

    public void setStakeholderversion(Long stakeholderversion) {
        this.stakeholderversion = stakeholderversion;
    }

    public void setReleaseversion(Long releaseversion) {
        this.releaseversion = releaseversion;
    }

    public void setCreditenhanceversion(Long creditenhanceversion) {
        this.creditenhanceversion = creditenhanceversion;
    }

    public void setOtherversion(Long otherversion) {
        this.otherversion = otherversion;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public void setRemoveflag(String removeflag) {
        this.removeflag = removeflag;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProductEntity)) {
            return false;
        }
        ProductEntity other = (ProductEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$productinfoversion = this.getProductinfoversion();
        Long other$productinfoversion = other.getProductinfoversion();
        if (this$productinfoversion == null ? other$productinfoversion != null : !((Object)this$productinfoversion).equals(other$productinfoversion)) {
            return false;
        }
        Long this$stakeholderversion = this.getStakeholderversion();
        Long other$stakeholderversion = other.getStakeholderversion();
        if (this$stakeholderversion == null ? other$stakeholderversion != null : !((Object)this$stakeholderversion).equals(other$stakeholderversion)) {
            return false;
        }
        Long this$releaseversion = this.getReleaseversion();
        Long other$releaseversion = other.getReleaseversion();
        if (this$releaseversion == null ? other$releaseversion != null : !((Object)this$releaseversion).equals(other$releaseversion)) {
            return false;
        }
        Long this$creditenhanceversion = this.getCreditenhanceversion();
        Long other$creditenhanceversion = other.getCreditenhanceversion();
        if (this$creditenhanceversion == null ? other$creditenhanceversion != null : !((Object)this$creditenhanceversion).equals(other$creditenhanceversion)) {
            return false;
        }
        Long this$otherversion = this.getOtherversion();
        Long other$otherversion = other.getOtherversion();
        if (this$otherversion == null ? other$otherversion != null : !((Object)this$otherversion).equals(other$otherversion)) {
            return false;
        }
        String this$producttype = this.getProducttype();
        String other$producttype = other.getProducttype();
        if (this$producttype == null ? other$producttype != null : !this$producttype.equals(other$producttype)) {
            return false;
        }
        String this$createuser = this.getCreateuser();
        String other$createuser = other.getCreateuser();
        if (this$createuser == null ? other$createuser != null : !this$createuser.equals(other$createuser)) {
            return false;
        }
        String this$removeflag = this.getRemoveflag();
        String other$removeflag = other.getRemoveflag();
        return !(this$removeflag == null ? other$removeflag != null : !this$removeflag.equals(other$removeflag));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProductEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $productinfoversion = this.getProductinfoversion();
        result = result * 59 + ($productinfoversion == null ? 43 : ((Object)$productinfoversion).hashCode());
        Long $stakeholderversion = this.getStakeholderversion();
        result = result * 59 + ($stakeholderversion == null ? 43 : ((Object)$stakeholderversion).hashCode());
        Long $releaseversion = this.getReleaseversion();
        result = result * 59 + ($releaseversion == null ? 43 : ((Object)$releaseversion).hashCode());
        Long $creditenhanceversion = this.getCreditenhanceversion();
        result = result * 59 + ($creditenhanceversion == null ? 43 : ((Object)$creditenhanceversion).hashCode());
        Long $otherversion = this.getOtherversion();
        result = result * 59 + ($otherversion == null ? 43 : ((Object)$otherversion).hashCode());
        String $producttype = this.getProducttype();
        result = result * 59 + ($producttype == null ? 43 : $producttype.hashCode());
        String $createuser = this.getCreateuser();
        result = result * 59 + ($createuser == null ? 43 : $createuser.hashCode());
        String $removeflag = this.getRemoveflag();
        result = result * 59 + ($removeflag == null ? 43 : $removeflag.hashCode());
        return result;
    }

    public String toString() {
        return "ProductEntity(id=" + this.getId() + ", producttype=" + this.getProducttype() + ", productinfoversion=" + this.getProductinfoversion() + ", stakeholderversion=" + this.getStakeholderversion() + ", releaseversion=" + this.getReleaseversion() + ", creditenhanceversion=" + this.getCreditenhanceversion() + ", otherversion=" + this.getOtherversion() + ", createuser=" + this.getCreateuser() + ", removeflag=" + this.getRemoveflag() + ")";
    }
}

