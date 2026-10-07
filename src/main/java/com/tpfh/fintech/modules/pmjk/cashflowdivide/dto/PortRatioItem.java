package com.tpfh.fintech.modules.pmjk.cashflowdivide.dto;

import java.math.BigDecimal;

/** 组合固定比例（小数），待分配毛额 = 库存数量 × ratio */
public class PortRatioItem {
    private String portCode;
    private BigDecimal ratio;

    public String getPortCode() { return portCode; }
    public void setPortCode(String portCode) { this.portCode = portCode; }
    public BigDecimal getRatio() { return ratio; }
    public void setRatio(BigDecimal ratio) { this.ratio = ratio; }
}
