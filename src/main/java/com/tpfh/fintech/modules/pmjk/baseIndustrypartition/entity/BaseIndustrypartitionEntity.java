/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.baseIndustrypartition.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="pmjk_base_industrypartition")
public class BaseIndustrypartitionEntity {
    private Long id;
    private Long partmode;
    private String pinyin;
    private String memo;
    private String industrycode;
    private String industryname;
    private String originalcode;

    public Long getId() {
        return this.id;
    }

    public Long getPartmode() {
        return this.partmode;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public String getMemo() {
        return this.memo;
    }

    public String getIndustrycode() {
        return this.industrycode;
    }

    public String getIndustryname() {
        return this.industryname;
    }

    public String getOriginalcode() {
        return this.originalcode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPartmode(Long partmode) {
        this.partmode = partmode;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setIndustrycode(String industrycode) {
        this.industrycode = industrycode;
    }

    public void setIndustryname(String industryname) {
        this.industryname = industryname;
    }

    public void setOriginalcode(String originalcode) {
        this.originalcode = originalcode;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BaseIndustrypartitionEntity)) {
            return false;
        }
        BaseIndustrypartitionEntity other = (BaseIndustrypartitionEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$partmode = this.getPartmode();
        Long other$partmode = other.getPartmode();
        if (this$partmode == null ? other$partmode != null : !((Object)this$partmode).equals(other$partmode)) {
            return false;
        }
        String this$pinyin = this.getPinyin();
        String other$pinyin = other.getPinyin();
        if (this$pinyin == null ? other$pinyin != null : !this$pinyin.equals(other$pinyin)) {
            return false;
        }
        String this$memo = this.getMemo();
        String other$memo = other.getMemo();
        if (this$memo == null ? other$memo != null : !this$memo.equals(other$memo)) {
            return false;
        }
        String this$industrycode = this.getIndustrycode();
        String other$industrycode = other.getIndustrycode();
        if (this$industrycode == null ? other$industrycode != null : !this$industrycode.equals(other$industrycode)) {
            return false;
        }
        String this$industryname = this.getIndustryname();
        String other$industryname = other.getIndustryname();
        if (this$industryname == null ? other$industryname != null : !this$industryname.equals(other$industryname)) {
            return false;
        }
        String this$originalcode = this.getOriginalcode();
        String other$originalcode = other.getOriginalcode();
        return !(this$originalcode == null ? other$originalcode != null : !this$originalcode.equals(other$originalcode));
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseIndustrypartitionEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $partmode = this.getPartmode();
        result = result * 59 + ($partmode == null ? 43 : ((Object)$partmode).hashCode());
        String $pinyin = this.getPinyin();
        result = result * 59 + ($pinyin == null ? 43 : $pinyin.hashCode());
        String $memo = this.getMemo();
        result = result * 59 + ($memo == null ? 43 : $memo.hashCode());
        String $industrycode = this.getIndustrycode();
        result = result * 59 + ($industrycode == null ? 43 : $industrycode.hashCode());
        String $industryname = this.getIndustryname();
        result = result * 59 + ($industryname == null ? 43 : $industryname.hashCode());
        String $originalcode = this.getOriginalcode();
        result = result * 59 + ($originalcode == null ? 43 : $originalcode.hashCode());
        return result;
    }

    public String toString() {
        return "BaseIndustrypartitionEntity(id=" + this.getId() + ", partmode=" + this.getPartmode() + ", pinyin=" + this.getPinyin() + ", memo=" + this.getMemo() + ", industrycode=" + this.getIndustrycode() + ", industryname=" + this.getIndustryname() + ", originalcode=" + this.getOriginalcode() + ")";
    }
}

