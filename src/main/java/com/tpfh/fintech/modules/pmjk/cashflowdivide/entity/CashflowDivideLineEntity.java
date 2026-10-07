package com.tpfh.fintech.modules.pmjk.cashflowdivide.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.math.BigDecimal;
import java.util.Date;

@TableName("pmjk_cashflow_divide_line")
public class CashflowDivideLineEntity {
    private Long id;
    private Long batchId;
    private Integer lineNo;
    private String bizType;
    private String bizSubtype;
    private Date transferDate;
    private Date tradeDate;
    private String flowDirection;
    private String portCode;
    private String investmentManager;
    private String cashAccount;
    private BigDecimal receivablePayable;
    private BigDecimal txAmount;
    private String feeChannel;
    private String secCode;
    private String description;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getBatchId() { return batchId; }
    public void setBatchId(Long batchId) { this.batchId = batchId; }
    public Integer getLineNo() { return lineNo; }
    public void setLineNo(Integer lineNo) { this.lineNo = lineNo; }
    public String getBizType() { return bizType; }
    public void setBizType(String bizType) { this.bizType = bizType; }
    public String getBizSubtype() { return bizSubtype; }
    public void setBizSubtype(String bizSubtype) { this.bizSubtype = bizSubtype; }
    public Date getTransferDate() { return transferDate; }
    public void setTransferDate(Date transferDate) { this.transferDate = transferDate; }
    public Date getTradeDate() { return tradeDate; }
    public void setTradeDate(Date tradeDate) { this.tradeDate = tradeDate; }
    public String getFlowDirection() { return flowDirection; }
    public void setFlowDirection(String flowDirection) { this.flowDirection = flowDirection; }
    public String getPortCode() { return portCode; }
    public void setPortCode(String portCode) { this.portCode = portCode; }
    public String getInvestmentManager() { return investmentManager; }
    public void setInvestmentManager(String investmentManager) { this.investmentManager = investmentManager; }
    public String getCashAccount() { return cashAccount; }
    public void setCashAccount(String cashAccount) { this.cashAccount = cashAccount; }
    public BigDecimal getReceivablePayable() { return receivablePayable; }
    public void setReceivablePayable(BigDecimal receivablePayable) { this.receivablePayable = receivablePayable; }
    public BigDecimal getTxAmount() { return txAmount; }
    public void setTxAmount(BigDecimal txAmount) { this.txAmount = txAmount; }
    public String getFeeChannel() { return feeChannel; }
    public void setFeeChannel(String feeChannel) { this.feeChannel = feeChannel; }
    public String getSecCode() { return secCode; }
    public void setSecCode(String secCode) { this.secCode = secCode; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
