/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableField
 *  com.baomidou.mybatisplus.annotations.TableId
 *  com.baomidou.mybatisplus.annotations.TableName
 *  com.fasterxml.jackson.annotation.JsonFormat
 *  javax.validation.constraints.Email
 *  javax.validation.constraints.NotBlank
 */
package com.tpfh.fintech.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tpfh.fintech.common.validator.group.AddGroup;
import com.tpfh.fintech.common.validator.group.UpdateGroup;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@TableName(value="sys_user")
public class SysUserEntity
implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long userId;
    @NotBlank(message="\u7528\u6237\u540d\u4e0d\u80fd\u4e3a\u7a7a", groups={AddGroup.class, UpdateGroup.class})
    private @NotBlank(message="\u7528\u6237\u540d\u4e0d\u80fd\u4e3a\u7a7a", groups={AddGroup.class, UpdateGroup.class}) String username;
    @TableField
    private String password;
    private String salt;
    @NotBlank(message="\u90ae\u7bb1\u4e0d\u80fd\u4e3a\u7a7a", groups={AddGroup.class, UpdateGroup.class})
    @Email(message="\u90ae\u7bb1\u683c\u5f0f\u4e0d\u6b63\u786e", groups={AddGroup.class, UpdateGroup.class})
    private @NotBlank(message="\u90ae\u7bb1\u4e0d\u80fd\u4e3a\u7a7a", groups={AddGroup.class, UpdateGroup.class}) @Email(message="\u90ae\u7bb1\u683c\u5f0f\u4e0d\u6b63\u786e", groups={AddGroup.class, UpdateGroup.class}) String email;
    private String mobile;
    private String name;
    private Integer type;
    private Integer status;
    @TableField(exist=false)
    private List<Long> roleIdList;
    @TableField(exist=false)
    private String deptName;
    @TableField(exist=false)
    private String roleName;
    private Long createUserId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
    private String deptId;
    @TableField(exist=false)
    private String deptParentId;
    @TableField(exist=false)
    private Integer oaUserId;

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSalt() {
        return this.salt;
    }

    public String getEmail() {
        return this.email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getName() {
        return this.name;
    }

    public Integer getStatus() {
        return this.status;
    }

    public List<Long> getRoleIdList() {
        return this.roleIdList;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public Long getCreateUserId() {
        return this.createUserId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getDeptId() {
        return this.deptId;
    }

    public String getDeptParentId() {
        return this.deptParentId;
    }

    public Integer getOaUserId() {
        return this.oaUserId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void setDeptParentId(String deptParentId) {
        this.deptParentId = deptParentId;
    }

    public void setOaUserId(Integer oaUserId) {
        this.oaUserId = oaUserId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SysUserEntity)) {
            return false;
        }
        SysUserEntity other = (SysUserEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$userId = this.getUserId();
        Long other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !((Object)this$userId).equals(other$userId)) {
            return false;
        }
        Integer this$type = this.getType();
        Integer other$type = other.getType();
        if (this$type == null ? other$type != null : !((Object)this$type).equals(other$type)) {
            return false;
        }
        Integer this$status = this.getStatus();
        Integer other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)this$status).equals(other$status)) {
            return false;
        }
        Long this$createUserId = this.getCreateUserId();
        Long other$createUserId = other.getCreateUserId();
        if (this$createUserId == null ? other$createUserId != null : !((Object)this$createUserId).equals(other$createUserId)) {
            return false;
        }
        Integer this$oaUserId = this.getOaUserId();
        Integer other$oaUserId = other.getOaUserId();
        if (this$oaUserId == null ? other$oaUserId != null : !((Object)this$oaUserId).equals(other$oaUserId)) {
            return false;
        }
        String this$username = this.getUsername();
        String other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) {
            return false;
        }
        String this$password = this.getPassword();
        String other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) {
            return false;
        }
        String this$salt = this.getSalt();
        String other$salt = other.getSalt();
        if (this$salt == null ? other$salt != null : !this$salt.equals(other$salt)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$mobile = this.getMobile();
        String other$mobile = other.getMobile();
        if (this$mobile == null ? other$mobile != null : !this$mobile.equals(other$mobile)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        List<Long> this$roleIdList = this.getRoleIdList();
        List<Long> other$roleIdList = other.getRoleIdList();
        if (this$roleIdList == null ? other$roleIdList != null : !((Object)this$roleIdList).equals(other$roleIdList)) {
            return false;
        }
        String this$deptName = this.getDeptName();
        String other$deptName = other.getDeptName();
        if (this$deptName == null ? other$deptName != null : !this$deptName.equals(other$deptName)) {
            return false;
        }
        String this$roleName = this.getRoleName();
        String other$roleName = other.getRoleName();
        if (this$roleName == null ? other$roleName != null : !this$roleName.equals(other$roleName)) {
            return false;
        }
        Date this$createTime = this.getCreateTime();
        Date other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !((Object)this$createTime).equals(other$createTime)) {
            return false;
        }
        String this$deptId = this.getDeptId();
        String other$deptId = other.getDeptId();
        if (this$deptId == null ? other$deptId != null : !this$deptId.equals(other$deptId)) {
            return false;
        }
        String this$deptParentId = this.getDeptParentId();
        String other$deptParentId = other.getDeptParentId();
        return !(this$deptParentId == null ? other$deptParentId != null : !this$deptParentId.equals(other$deptParentId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SysUserEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Integer $type = this.getType();
        result = result * 59 + ($type == null ? 43 : ((Object)$type).hashCode());
        Integer $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)$status).hashCode());
        Long $createUserId = this.getCreateUserId();
        result = result * 59 + ($createUserId == null ? 43 : ((Object)$createUserId).hashCode());
        Integer $oaUserId = this.getOaUserId();
        result = result * 59 + ($oaUserId == null ? 43 : ((Object)$oaUserId).hashCode());
        String $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        String $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        String $salt = this.getSalt();
        result = result * 59 + ($salt == null ? 43 : $salt.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $mobile = this.getMobile();
        result = result * 59 + ($mobile == null ? 43 : $mobile.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        List<Long> $roleIdList = this.getRoleIdList();
        result = result * 59 + ($roleIdList == null ? 43 : ((Object)$roleIdList).hashCode());
        String $deptName = this.getDeptName();
        result = result * 59 + ($deptName == null ? 43 : $deptName.hashCode());
        String $roleName = this.getRoleName();
        result = result * 59 + ($roleName == null ? 43 : $roleName.hashCode());
        Date $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : ((Object)$createTime).hashCode());
        String $deptId = this.getDeptId();
        result = result * 59 + ($deptId == null ? 43 : $deptId.hashCode());
        String $deptParentId = this.getDeptParentId();
        result = result * 59 + ($deptParentId == null ? 43 : $deptParentId.hashCode());
        return result;
    }

    public String toString() {
        return "SysUserEntity(userId=" + this.getUserId() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", salt=" + this.getSalt() + ", email=" + this.getEmail() + ", mobile=" + this.getMobile() + ", name=" + this.getName() + ", type=" + this.getType() + ", status=" + this.getStatus() + ", roleIdList=" + this.getRoleIdList() + ", deptName=" + this.getDeptName() + ", roleName=" + this.getRoleName() + ", createUserId=" + this.getCreateUserId() + ", createTime=" + this.getCreateTime() + ", deptId=" + this.getDeptId() + ", deptParentId=" + this.getDeptParentId() + ", oaUserId=" + this.getOaUserId() + ")";
    }
}

