package com.tpfh.fintech.modules.pmjk.cashflowdivide.dto;

import java.math.BigDecimal;

public class CashflowDivideCalculateRequest {
    /** 待分配方式：TOTAL 人工总额 / RATE 统一固定比例 */
    private String allocateMode;
    private String stockDate;
    private String productShortName;
    private String secCode;
    /** 人工输入的待分配资金总额（TOTAL 时按库存占比分摊） */
    private BigDecimal totalAllocateAmount;
    private BigDecimal totalBankFee;
    /** 人工输入的待分担税费总额（按库存占比分摊，可为 0） */
    private BigDecimal totalTaxAmount;
    /** 统一固定比例（小数），毛分配 = 库存数量 × ratio（RATE 时使用） */
    private BigDecimal globalAllocateRatio;
    private String transferDate;
    private String tradeDate;
    private String cashAccount;
    private String descPrefix;
    private String tptPortCode;
    private String investmentManager;

    public String getAllocateMode() { return allocateMode; }
    public void setAllocateMode(String allocateMode) { this.allocateMode = allocateMode; }
    public String getStockDate() { return stockDate; }
    public void setStockDate(String stockDate) { this.stockDate = stockDate; }
    public String getProductShortName() { return productShortName; }
    public void setProductShortName(String productShortName) { this.productShortName = productShortName; }
    public String getSecCode() { return secCode; }
    public void setSecCode(String secCode) { this.secCode = secCode; }
    public BigDecimal getTotalAllocateAmount() { return totalAllocateAmount; }
    public void setTotalAllocateAmount(BigDecimal totalAllocateAmount) { this.totalAllocateAmount = totalAllocateAmount; }
    public BigDecimal getTotalBankFee() { return totalBankFee; }
    public void setTotalBankFee(BigDecimal totalBankFee) { this.totalBankFee = totalBankFee; }
    public BigDecimal getTotalTaxAmount() { return totalTaxAmount; }
    public void setTotalTaxAmount(BigDecimal totalTaxAmount) { this.totalTaxAmount = totalTaxAmount; }
    public BigDecimal getGlobalAllocateRatio() { return globalAllocateRatio; }
    public void setGlobalAllocateRatio(BigDecimal globalAllocateRatio) { this.globalAllocateRatio = globalAllocateRatio; }
    public String getTransferDate() { return transferDate; }
    public void setTransferDate(String transferDate) { this.transferDate = transferDate; }
    public String getTradeDate() { return tradeDate; }
    public void setTradeDate(String tradeDate) { this.tradeDate = tradeDate; }
    public String getCashAccount() { return cashAccount; }
    public void setCashAccount(String cashAccount) { this.cashAccount = cashAccount; }
    public String getDescPrefix() { return descPrefix; }
    public void setDescPrefix(String descPrefix) { this.descPrefix = descPrefix; }
    public String getTptPortCode() { return tptPortCode; }
    public void setTptPortCode(String tptPortCode) { this.tptPortCode = tptPortCode; }
    public String getInvestmentManager() { return investmentManager; }
    public void setInvestmentManager(String investmentManager) { this.investmentManager = investmentManager; }
}
