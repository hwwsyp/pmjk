/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.borrowinfo.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="pmjk_borrowinfo")
public class BorrowinfoEntity {
    private Long id;
    private String type;
    private String content;
    private Long creditenhanceid;

    public Long getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String getContent() {
        return this.content;
    }

    public Long getCreditenhanceid() {
        return this.creditenhanceid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreditenhanceid(Long creditenhanceid) {
        this.creditenhanceid = creditenhanceid;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BorrowinfoEntity)) {
            return false;
        }
        BorrowinfoEntity other = (BorrowinfoEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$creditenhanceid = this.getCreditenhanceid();
        Long other$creditenhanceid = other.getCreditenhanceid();
        if (this$creditenhanceid == null ? other$creditenhanceid != null : !((Object)this$creditenhanceid).equals(other$creditenhanceid)) {
            return false;
        }
        String this$type = this.getType();
        String other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) {
            return false;
        }
        String this$content = this.getContent();
        String other$content = other.getContent();
        return !(this$content == null ? other$content != null : !this$content.equals(other$content));
    }

    protected boolean canEqual(Object other) {
        return other instanceof BorrowinfoEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $creditenhanceid = this.getCreditenhanceid();
        result = result * 59 + ($creditenhanceid == null ? 43 : ((Object)$creditenhanceid).hashCode());
        String $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        String $content = this.getContent();
        result = result * 59 + ($content == null ? 43 : $content.hashCode());
        return result;
    }

    public String toString() {
        return "BorrowinfoEntity(id=" + this.getId() + ", type=" + this.getType() + ", content=" + this.getContent() + ", creditenhanceid=" + this.getCreditenhanceid() + ")";
    }
}

