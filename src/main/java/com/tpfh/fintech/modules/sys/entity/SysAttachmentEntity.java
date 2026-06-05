/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="sys_attachment")
public class SysAttachmentEntity {
    private Long id;
    private String code;
    private String srcFileName;
    private String destFileName;
    private String filePath;
    private String urlPath;
    private String uploadTime;
    private String uploadUser;

    public Long getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getSrcFileName() {
        return this.srcFileName;
    }

    public String getDestFileName() {
        return this.destFileName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getUrlPath() {
        return this.urlPath;
    }

    public String getUploadTime() {
        return this.uploadTime;
    }

    public String getUploadUser() {
        return this.uploadUser;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setSrcFileName(String srcFileName) {
        this.srcFileName = srcFileName;
    }

    public void setDestFileName(String destFileName) {
        this.destFileName = destFileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SysAttachmentEntity)) {
            return false;
        }
        SysAttachmentEntity other = (SysAttachmentEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        String this$code = this.getCode();
        String other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code)) {
            return false;
        }
        String this$srcFileName = this.getSrcFileName();
        String other$srcFileName = other.getSrcFileName();
        if (this$srcFileName == null ? other$srcFileName != null : !this$srcFileName.equals(other$srcFileName)) {
            return false;
        }
        String this$destFileName = this.getDestFileName();
        String other$destFileName = other.getDestFileName();
        if (this$destFileName == null ? other$destFileName != null : !this$destFileName.equals(other$destFileName)) {
            return false;
        }
        String this$filePath = this.getFilePath();
        String other$filePath = other.getFilePath();
        if (this$filePath == null ? other$filePath != null : !this$filePath.equals(other$filePath)) {
            return false;
        }
        String this$urlPath = this.getUrlPath();
        String other$urlPath = other.getUrlPath();
        if (this$urlPath == null ? other$urlPath != null : !this$urlPath.equals(other$urlPath)) {
            return false;
        }
        String this$uploadTime = this.getUploadTime();
        String other$uploadTime = other.getUploadTime();
        if (this$uploadTime == null ? other$uploadTime != null : !this$uploadTime.equals(other$uploadTime)) {
            return false;
        }
        String this$uploadUser = this.getUploadUser();
        String other$uploadUser = other.getUploadUser();
        return !(this$uploadUser == null ? other$uploadUser != null : !this$uploadUser.equals(other$uploadUser));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SysAttachmentEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        String $srcFileName = this.getSrcFileName();
        result = result * 59 + ($srcFileName == null ? 43 : $srcFileName.hashCode());
        String $destFileName = this.getDestFileName();
        result = result * 59 + ($destFileName == null ? 43 : $destFileName.hashCode());
        String $filePath = this.getFilePath();
        result = result * 59 + ($filePath == null ? 43 : $filePath.hashCode());
        String $urlPath = this.getUrlPath();
        result = result * 59 + ($urlPath == null ? 43 : $urlPath.hashCode());
        String $uploadTime = this.getUploadTime();
        result = result * 59 + ($uploadTime == null ? 43 : $uploadTime.hashCode());
        String $uploadUser = this.getUploadUser();
        result = result * 59 + ($uploadUser == null ? 43 : $uploadUser.hashCode());
        return result;
    }

    public String toString() {
        return "SysAttachmentEntity(id=" + this.getId() + ", code=" + this.getCode() + ", srcFileName=" + this.getSrcFileName() + ", destFileName=" + this.getDestFileName() + ", filePath=" + this.getFilePath() + ", urlPath=" + this.getUrlPath() + ", uploadTime=" + this.getUploadTime() + ", uploadUser=" + this.getUploadUser() + ")";
    }
}

