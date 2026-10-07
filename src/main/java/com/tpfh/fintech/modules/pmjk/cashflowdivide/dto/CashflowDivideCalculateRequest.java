package com.tpfh.fintech.modules.pmjk.cashflowdivide.dto;

import java.math.BigDecimal;
import java.util.List;

public class CashflowDivideCalculateRequest {
    /** 税费方式：NONE / TOTAL / RATE / MIXED */
    private String taxMode;
    private String stockDate;
    private String productShortName;
    private String secCode;
    private BigDecimal totalAllocateAmount;
    private BigDecimal totalBankFee;
    /** 待分担税费总额（TOTAL、MIXED 时使用，按库存占比分摊） */
    private BigDecimal totalTaxAmount;
    /** 统一固定税率，小数，如 0.0264 */
    private BigDecimal globalTaxRate;
    /** 指定组合的固定税率 */
    private List<PortTaxRateItem> portTaxRates;
    private String transferDate;
    private String tradeDate;
    private String cashAccount;
    private String descPrefix;
    private String tptPortCode;
    private String investmentManager;

    public String getTaxMode() { return taxMode; }
    public void setTaxMode(String taxMode) { this.taxMode = taxMode; }
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
    public BigDecimal getGlobalTaxRate() { return globalTaxRate; }
    public void setGlobalTaxRate(BigDecimal globalTaxRate) { this.globalTaxRate = globalTaxRate; }
    public List<PortTaxRateItem> getPortTaxRates() { return portTaxRates; }
    public void setPortTaxRates(List<PortTaxRateItem> portTaxRates) { this.portTaxRates = portTaxRates; }
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
