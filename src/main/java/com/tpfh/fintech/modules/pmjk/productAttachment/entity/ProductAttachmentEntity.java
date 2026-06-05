/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 *  com.fasterxml.jackson.annotation.JsonFormat
 */
package com.tpfh.fintech.modules.pmjk.productAttachment.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

@TableName(value="pmjk_product_attachment")
public class ProductAttachmentEntity {
    private Long id;
    private String productid;
    private String type;
    private String filepath;
    private String url;
    private String srcname;
    private String destname;
    private String uploaduser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date uploadtime;

    public Long getId() {
        return this.id;
    }

    public String getProductid() {
        return this.productid;
    }

    public String getType() {
        return this.type;
    }

    public String getFilepath() {
        return this.filepath;
    }

    public String getUrl() {
        return this.url;
    }

    public String getSrcname() {
        return this.srcname;
    }

    public String getDestname() {
        return this.destname;
    }

    public String getUploaduser() {
        return this.uploaduser;
    }

    public Date getUploadtime() {
        return this.uploadtime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSrcname(String srcname) {
        this.srcname = srcname;
    }

    public void setDestname(String destname) {
        this.destname = destname;
    }

    public void setUploaduser(String uploaduser) {
        this.uploaduser = uploaduser;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProductAttachmentEntity)) {
            return false;
        }
        ProductAttachmentEntity other = (ProductAttachmentEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        String this$productid = this.getProductid();
        String other$productid = other.getProductid();
        if (this$productid == null ? other$productid != null : !this$productid.equals(other$productid)) {
            return false;
        }
        String this$type = this.getType();
        String other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) {
            return false;
        }
        String this$filepath = this.getFilepath();
        String other$filepath = other.getFilepath();
        if (this$filepath == null ? other$filepath != null : !this$filepath.equals(other$filepath)) {
            return false;
        }
        String this$url = this.getUrl();
        String other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) {
            return false;
        }
        String this$srcname = this.getSrcname();
        String other$srcname = other.getSrcname();
        if (this$srcname == null ? other$srcname != null : !this$srcname.equals(other$srcname)) {
            return false;
        }
        String this$destname = this.getDestname();
        String other$destname = other.getDestname();
        if (this$destname == null ? other$destname != null : !this$destname.equals(other$destname)) {
            return false;
        }
        String this$uploaduser = this.getUploaduser();
        String other$uploaduser = other.getUploaduser();
        if (this$uploaduser == null ? other$uploaduser != null : !this$uploaduser.equals(other$uploaduser)) {
            return false;
        }
        Date this$uploadtime = this.getUploadtime();
        Date other$uploadtime = other.getUploadtime();
        return !(this$uploadtime == null ? other$uploadtime != null : !((Object)this$uploadtime).equals(other$uploadtime));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProductAttachmentEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $productid = this.getProductid();
        result = result * 59 + ($productid == null ? 43 : $productid.hashCode());
        String $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        String $filepath = this.getFilepath();
        result = result * 59 + ($filepath == null ? 43 : $filepath.hashCode());
        String $url = this.getUrl();
        result = result * 59 + ($url == null ? 43 : $url.hashCode());
        String $srcname = this.getSrcname();
        result = result * 59 + ($srcname == null ? 43 : $srcname.hashCode());
        String $destname = this.getDestname();
        result = result * 59 + ($destname == null ? 43 : $destname.hashCode());
        String $uploaduser = this.getUploaduser();
        result = result * 59 + ($uploaduser == null ? 43 : $uploaduser.hashCode());
        Date $uploadtime = this.getUploadtime();
        result = result * 59 + ($uploadtime == null ? 43 : ((Object)$uploadtime).hashCode());
        return result;
    }

    public String toString() {
        return "ProductAttachmentEntity(id=" + this.getId() + ", productid=" + this.getProductid() + ", type=" + this.getType() + ", filepath=" + this.getFilepath() + ", url=" + this.getUrl() + ", srcname=" + this.getSrcname() + ", destname=" + this.getDestname() + ", uploaduser=" + this.getUploaduser() + ", uploadtime=" + this.getUploadtime() + ")";
    }
}

