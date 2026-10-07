package com.tpfh.fintech.modules.pmjk.cashflowdivide.dto;

import java.math.BigDecimal;

public class CashflowDivideCalculateRequest {
    private String stockDate;
    private String productShortName;
    private String secCode;
    private BigDecimal totalAllocateAmount;
    private BigDecimal totalBankFee;
    private String transferDate;
    private String tradeDate;
    private String cashAccount;
    private String descPrefix;
    private String tptPortCode;
    private String investmentManager;

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
