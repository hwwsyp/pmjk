package com.tpfh.fintech.modules.pmjk.cashflowdivide.dto;

import java.math.BigDecimal;

public class PortTaxRateItem {
    private String portCode;
    /** 税率，小数形式，如 0.0264 表示 2.64% */
    private BigDecimal taxRate;

    public String getPortCode() { return portCode; }
    public void setPortCode(String portCode) { this.portCode = portCode; }
    public BigDecimal getTaxRate() { return taxRate; }
    public void setTaxRate(BigDecimal taxRate) { this.taxRate = taxRate; }
}
