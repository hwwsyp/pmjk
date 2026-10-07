package com.tpfh.fintech.modules.pmjk.financialvariety.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;

@TableName(value = "pmjk_financial_variety")
public class FinancialVarietyEntity {
    private Long id;
    private String secCode;
    private String secMktCode;
    private String secIsinCode;
    private String smCode;
    private String secName;
    private String dcCode;
    private String secVarCode;
    private String mktCode;
    private Date createtimestamp;
    private Date updatetimestamp;
    private String islock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getSecMktCode() {
        return secMktCode;
    }

    public void setSecMktCode(String secMktCode) {
        this.secMktCode = secMktCode;
    }

    public String getSecIsinCode() {
        return secIsinCode;
    }

    public void setSecIsinCode(String secIsinCode) {
        this.secIsinCode = secIsinCode;
    }

    public String getSmCode() {
        return smCode;
    }

    public void setSmCode(String smCode) {
        this.smCode = smCode;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getDcCode() {
        return dcCode;
    }

    public void setDcCode(String dcCode) {
        this.dcCode = dcCode;
    }

    public String getSecVarCode() {
        return secVarCode;
    }

    public void setSecVarCode(String secVarCode) {
        this.secVarCode = secVarCode;
    }

    public String getMktCode() {
        return mktCode;
    }

    public void setMktCode(String mktCode) {
        this.mktCode = mktCode;
    }

    public Date getCreatetimestamp() {
        return createtimestamp;
    }

    public void setCreatetimestamp(Date createtimestamp) {
        this.createtimestamp = createtimestamp;
    }

    public Date getUpdatetimestamp() {
        return updatetimestamp;
    }

    public void setUpdatetimestamp(Date updatetimestamp) {
        this.updatetimestamp = updatetimestamp;
    }

    public String getIslock() {
        return islock;
    }

    public void setIslock(String islock) {
        this.islock = islock;
    }
}
