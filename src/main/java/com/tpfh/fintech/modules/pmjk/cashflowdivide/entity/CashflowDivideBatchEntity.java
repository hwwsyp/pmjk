package com.tpfh.fintech.modules.pmjk.cashflowdivide.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.math.BigDecimal;
import java.util.Date;

@TableName("pmjk_cashflow_divide_batch")
public class CashflowDivideBatchEntity {
    private Long id;
    private Date stockDate;
    private String productShortName;
    private String secCode;
    private String smCode;
    private BigDecimal totalAllocateAmount;
    private BigDecimal totalBankFee;
    private Date transferDate;
    private Date tradeDate;
    private String cashAccount;
    private String descPrefix;
    private String tptPortCode;
    private String investmentManager;
    private Date createtimestamp;
    private Date updatetimestamp;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getStockDate() { return stockDate; }
    public void setStockDate(Date stockDate) { this.stockDate = stockDate; }
    public String getProductShortName() { return productShortName; }
    public void setProductShortName(String productShortName) { this.productShortName = productShortName; }
    public String getSecCode() { return secCode; }
    public void setSecCode(String secCode) { this.secCode = secCode; }
    public String getSmCode() { return smCode; }
    public void setSmCode(String smCode) { this.smCode = smCode; }
    public BigDecimal getTotalAllocateAmount() { return totalAllocateAmount; }
    public void setTotalAllocateAmount(BigDecimal totalAllocateAmount) { this.totalAllocateAmount = totalAllocateAmount; }
    public BigDecimal getTotalBankFee() { return totalBankFee; }
    public void setTotalBankFee(BigDecimal totalBankFee) { this.totalBankFee = totalBankFee; }
    public Date getTransferDate() { return transferDate; }
    public void setTransferDate(Date transferDate) { this.transferDate = transferDate; }
    public Date getTradeDate() { return tradeDate; }
    public void setTradeDate(Date tradeDate) { this.tradeDate = tradeDate; }
    public String getCashAccount() { return cashAccount; }
    public void setCashAccount(String cashAccount) { this.cashAccount = cashAccount; }
    public String getDescPrefix() { return descPrefix; }
    public void setDescPrefix(String descPrefix) { this.descPrefix = descPrefix; }
    public String getTptPortCode() { return tptPortCode; }
    public void setTptPortCode(String tptPortCode) { this.tptPortCode = tptPortCode; }
    public String getInvestmentManager() { return investmentManager; }
    public void setInvestmentManager(String investmentManager) { this.investmentManager = investmentManager; }
    public Date getCreatetimestamp() { return createtimestamp; }
    public void setCreatetimestamp(Date createtimestamp) { this.createtimestamp = createtimestamp; }
    public Date getUpdatetimestamp() { return updatetimestamp; }
    public void setUpdatetimestamp(Date updatetimestamp) { this.updatetimestamp = updatetimestamp; }
}
