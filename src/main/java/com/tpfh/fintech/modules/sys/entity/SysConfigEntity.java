/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableField
 *  com.baomidou.mybatisplus.annotations.TableId
 *  com.baomidou.mybatisplus.annotations.TableName
 *  javax.validation.constraints.NotBlank
 */
package com.tpfh.fintech.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.util.List;
import javax.validation.constraints.NotBlank;

@TableName(value="sys_config")
public class SysConfigEntity {
    @TableId
    private Long id;
    @NotBlank(message="\u53c2\u6570\u540d\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u53c2\u6570\u540d\u4e0d\u80fd\u4e3a\u7a7a") String key;
    @NotBlank(message="\u53c2\u6570\u503c\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u53c2\u6570\u503c\u4e0d\u80fd\u4e3a\u7a7a") String value;
    private String remark;
    private Long parentId;
    @TableField(exist=false)
    private String parentName;
    private Integer orderNum;
    private Boolean open;
    private List<?> list;

    public Long getId() {
        return this.id;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public String getRemark() {
        return this.remark;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public String getParentName() {
        return this.parentName;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }

    public Boolean getOpen() {
        return this.open;
    }

    public List<?> getList() {
        return this.list;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SysConfigEntity)) {
            return false;
        }
        SysConfigEntity other = (SysConfigEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$parentId = this.getParentId();
        Long other$parentId = other.getParentId();
        if (this$parentId == null ? other$parentId != null : !((Object)this$parentId).equals(other$parentId)) {
            return false;
        }
        Integer this$orderNum = this.getOrderNum();
        Integer other$orderNum = other.getOrderNum();
        if (this$orderNum == null ? other$orderNum != null : !((Object)this$orderNum).equals(other$orderNum)) {
            return false;
        }
        Boolean this$open = this.getOpen();
        Boolean other$open = other.getOpen();
        if (this$open == null ? other$open != null : !((Object)this$open).equals(other$open)) {
            return false;
        }
        String this$key = this.getKey();
        String other$key = other.getKey();
        if (this$key == null ? other$key != null : !this$key.equals(other$key)) {
            return false;
        }
        String this$value = this.getValue();
        String other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) {
            return false;
        }
        String this$remark = this.getRemark();
        String other$remark = other.getRemark();
        if (this$remark == null ? other$remark != null : !this$remark.equals(other$remark)) {
            return false;
        }
        String this$parentName = this.getParentName();
        String other$parentName = other.getParentName();
        if (this$parentName == null ? other$parentName != null : !this$parentName.equals(other$parentName)) {
            return false;
        }
        List<?> this$list = this.getList();
        List<?> other$list = other.getList();
        return !(this$list == null ? other$list != null : !((Object)this$list).equals(other$list));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SysConfigEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $parentId = this.getParentId();
        result = result * 59 + ($parentId == null ? 43 : ((Object)$parentId).hashCode());
        Integer $orderNum = this.getOrderNum();
        result = result * 59 + ($orderNum == null ? 43 : ((Object)$orderNum).hashCode());
        Boolean $open = this.getOpen();
        result = result * 59 + ($open == null ? 43 : ((Object)$open).hashCode());
        String $key = this.getKey();
        result = result * 59 + ($key == null ? 43 : $key.hashCode());
        String $value = this.getValue();
        result = result * 59 + ($value == null ? 43 : $value.hashCode());
        String $remark = this.getRemark();
        result = result * 59 + ($remark == null ? 43 : $remark.hashCode());
        String $parentName = this.getParentName();
        result = result * 59 + ($parentName == null ? 43 : $parentName.hashCode());
        List<?> $list = this.getList();
        result = result * 59 + ($list == null ? 43 : ((Object)$list).hashCode());
        return result;
    }

    public String toString() {
        return "SysConfigEntity(id=" + this.getId() + ", key=" + this.getKey() + ", value=" + this.getValue() + ", remark=" + this.getRemark() + ", parentId=" + this.getParentId() + ", parentName=" + this.getParentName() + ", orderNum=" + this.getOrderNum() + ", open=" + this.getOpen() + ", list=" + this.getList() + ")";
    }
}

