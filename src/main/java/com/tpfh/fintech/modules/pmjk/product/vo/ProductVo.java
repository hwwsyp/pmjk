/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonFormat
 */
package com.tpfh.fintech.modules.pmjk.product.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class ProductVo {
    private Long id;
    private String producttype;
    private String productname;
    private String productshortname;
    private String manager;
    private String releasename;
    private String removeflag;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date createtimestamp;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date updatetimestamp;
    private String managername;
    private Integer productinfoversion;

    public Long getId() {
        return this.id;
    }

    public String getProducttype() {
        return this.producttype;
    }

    public String getProductname() {
        return this.productname;
    }

    public String getProductshortname() {
        return this.productshortname;
    }

    public String getManager() {
        return this.manager;
    }

    public String getReleasename() {
        return this.releasename;
    }

    public String getRemoveflag() {
        return this.removeflag;
    }

    public Date getCreatetimestamp() {
        return this.createtimestamp;
    }

    public Date getUpdatetimestamp() {
        return this.updatetimestamp;
    }

    public String getManagername() {
        return this.managername;
    }

    public Integer getProductinfoversion() {
        return this.productinfoversion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setProductshortname(String productshortname) {
        this.productshortname = productshortname;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setReleasename(String releasename) {
        this.releasename = releasename;
    }

    public void setRemoveflag(String removeflag) {
        this.removeflag = removeflag;
    }

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    public void setCreatetimestamp(Date createtimestamp) {
        this.createtimestamp = createtimestamp;
    }

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    public void setUpdatetimestamp(Date updatetimestamp) {
        this.updatetimestamp = updatetimestamp;
    }

    public void setManagername(String managername) {
        this.managername = managername;
    }

    public void setProductinfoversion(Integer productinfoversion) {
        this.productinfoversion = productinfoversion;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProductVo)) {
            return false;
        }
        ProductVo other = (ProductVo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Integer this$productinfoversion = this.getProductinfoversion();
        Integer other$productinfoversion = other.getProductinfoversion();
        if (this$productinfoversion == null ? other$productinfoversion != null : !((Object)this$productinfoversion).equals(other$productinfoversion)) {
            return false;
        }
        String this$producttype = this.getProducttype();
        String other$producttype = other.getProducttype();
        if (this$producttype == null ? other$producttype != null : !this$producttype.equals(other$producttype)) {
            return false;
        }
        String this$productname = this.getProductname();
        String other$productname = other.getProductname();
        if (this$productname == null ? other$productname != null : !this$productname.equals(other$productname)) {
            return false;
        }
        String this$productshortname = this.getProductshortname();
        String other$productshortname = other.getProductshortname();
        if (this$productshortname == null ? other$productshortname != null : !this$productshortname.equals(other$productshortname)) {
            return false;
        }
        String this$manager = this.getManager();
        String other$manager = other.getManager();
        if (this$manager == null ? other$manager != null : !this$manager.equals(other$manager)) {
            return false;
        }
        String this$releasename = this.getReleasename();
        String other$releasename = other.getReleasename();
        if (this$releasename == null ? other$releasename != null : !this$releasename.equals(other$releasename)) {
            return false;
        }
        String this$removeflag = this.getRemoveflag();
        String other$removeflag = other.getRemoveflag();
        if (this$removeflag == null ? other$removeflag != null : !this$removeflag.equals(other$removeflag)) {
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
        String this$managername = this.getManagername();
        String other$managername = other.getManagername();
        return !(this$managername == null ? other$managername != null : !this$managername.equals(other$managername));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProductVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Integer $productinfoversion = this.getProductinfoversion();
        result = result * 59 + ($productinfoversion == null ? 43 : ((Object)$productinfoversion).hashCode());
        String $producttype = this.getProducttype();
        result = result * 59 + ($producttype == null ? 43 : $producttype.hashCode());
        String $productname = this.getProductname();
        result = result * 59 + ($productname == null ? 43 : $productname.hashCode());
        String $productshortname = this.getProductshortname();
        result = result * 59 + ($productshortname == null ? 43 : $productshortname.hashCode());
        String $manager = this.getManager();
        result = result * 59 + ($manager == null ? 43 : $manager.hashCode());
        String $releasename = this.getReleasename();
        result = result * 59 + ($releasename == null ? 43 : $releasename.hashCode());
        String $removeflag = this.getRemoveflag();
        result = result * 59 + ($removeflag == null ? 43 : $removeflag.hashCode());
        Date $createtimestamp = this.getCreatetimestamp();
        result = result * 59 + ($createtimestamp == null ? 43 : ((Object)$createtimestamp).hashCode());
        Date $updatetimestamp = this.getUpdatetimestamp();
        result = result * 59 + ($updatetimestamp == null ? 43 : ((Object)$updatetimestamp).hashCode());
        String $managername = this.getManagername();
        result = result * 59 + ($managername == null ? 43 : $managername.hashCode());
        return result;
    }

    public String toString() {
        return "ProductVo(id=" + this.getId() + ", producttype=" + this.getProducttype() + ", productname=" + this.getProductname() + ", productshortname=" + this.getProductshortname() + ", manager=" + this.getManager() + ", releasename=" + this.getReleasename() + ", removeflag=" + this.getRemoveflag() + ", createtimestamp=" + this.getCreatetimestamp() + ", updatetimestamp=" + this.getUpdatetimestamp() + ", managername=" + this.getManagername() + ", productinfoversion=" + this.getProductinfoversion() + ")";
    }
}

