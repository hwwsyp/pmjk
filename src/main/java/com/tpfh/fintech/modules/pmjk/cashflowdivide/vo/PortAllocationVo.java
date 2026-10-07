package com.tpfh.fintech.modules.pmjk.cashflowdivide.vo;

import java.math.BigDecimal;

public class PortAllocationVo {
    private String portCode;
    private BigDecimal stockAmount;
    private BigDecimal ratio;
    private BigDecimal grossAllocate;
    private BigDecimal bankFee;
    private BigDecimal netAmount;

    public String getPortCode() { return portCode; }
    public void setPortCode(String portCode) { this.portCode = portCode; }
    public BigDecimal getStockAmount() { return stockAmount; }
    public void setStockAmount(BigDecimal stockAmount) { this.stockAmount = stockAmount; }
    public BigDecimal getRatio() { return ratio; }
    public void setRatio(BigDecimal ratio) { this.ratio = ratio; }
    public BigDecimal getGrossAllocate() { return grossAllocate; }
    public void setGrossAllocate(BigDecimal grossAllocate) { this.grossAllocate = grossAllocate; }
    public BigDecimal getBankFee() { return bankFee; }
    public void setBankFee(BigDecimal bankFee) { this.bankFee = bankFee; }
    public BigDecimal getNetAmount() { return netAmount; }
    public void setNetAmount(BigDecimal netAmount) { this.netAmount = netAmount; }
}
