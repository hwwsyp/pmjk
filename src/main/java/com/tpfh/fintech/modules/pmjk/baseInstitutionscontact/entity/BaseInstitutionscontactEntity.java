/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.baseInstitutionscontact.entity;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value="pmjk_base_institutionscontact")
public class BaseInstitutionscontactEntity {
    private Long id;
    private String tel;
    private String mobile;
    private String email;
    private String address;
    private String postcode;
    private String name;
    private Long institutionsid;

    public Long getId() {
        return this.id;
    }

    public String getTel() {
        return this.tel;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public String getName() {
        return this.name;
    }

    public Long getInstitutionsid() {
        return this.institutionsid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstitutionsid(Long institutionsid) {
        this.institutionsid = institutionsid;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BaseInstitutionscontactEntity)) {
            return false;
        }
        BaseInstitutionscontactEntity other = (BaseInstitutionscontactEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        Long this$institutionsid = this.getInstitutionsid();
        Long other$institutionsid = other.getInstitutionsid();
        if (this$institutionsid == null ? other$institutionsid != null : !((Object)this$institutionsid).equals(other$institutionsid)) {
            return false;
        }
        String this$tel = this.getTel();
        String other$tel = other.getTel();
        if (this$tel == null ? other$tel != null : !this$tel.equals(other$tel)) {
            return false;
        }
        String this$mobile = this.getMobile();
        String other$mobile = other.getMobile();
        if (this$mobile == null ? other$mobile != null : !this$mobile.equals(other$mobile)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$address = this.getAddress();
        String other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) {
            return false;
        }
        String this$postcode = this.getPostcode();
        String other$postcode = other.getPostcode();
        if (this$postcode == null ? other$postcode != null : !this$postcode.equals(other$postcode)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        return !(this$name == null ? other$name != null : !this$name.equals(other$name));
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseInstitutionscontactEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        Long $institutionsid = this.getInstitutionsid();
        result = result * 59 + ($institutionsid == null ? 43 : ((Object)$institutionsid).hashCode());
        String $tel = this.getTel();
        result = result * 59 + ($tel == null ? 43 : $tel.hashCode());
        String $mobile = this.getMobile();
        result = result * 59 + ($mobile == null ? 43 : $mobile.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $address = this.getAddress();
        result = result * 59 + ($address == null ? 43 : $address.hashCode());
        String $postcode = this.getPostcode();
        result = result * 59 + ($postcode == null ? 43 : $postcode.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "BaseInstitutionscontactEntity(id=" + this.getId() + ", tel=" + this.getTel() + ", mobile=" + this.getMobile() + ", email=" + this.getEmail() + ", address=" + this.getAddress() + ", postcode=" + this.getPostcode() + ", name=" + this.getName() + ", institutionsid=" + this.getInstitutionsid() + ")";
    }
}

