package com.tpfh.fintech.modules.pmjk.portfoliostock.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.math.BigDecimal;
import java.util.Date;

@TableName(value = "pmjk_portfolio_stock")
public class PortfolioStockEntity {
    private Long id;
    private String portCode;
    private Date stockDate;
    private String secCode;
    private String dcCode;
    private BigDecimal amount;
    private String kmCode;
    private String kmName;
    private Date createtimestamp;
    private Date updatetimestamp;
    private String islock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getDcCode() {
        return dcCode;
    }

    public void setDcCode(String dcCode) {
        this.dcCode = dcCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getKmCode() {
        return kmCode;
    }

    public void setKmCode(String kmCode) {
        this.kmCode = kmCode;
    }

    public String getKmName() {
        return kmName;
    }

    public void setKmName(String kmName) {
        this.kmName = kmName;
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
