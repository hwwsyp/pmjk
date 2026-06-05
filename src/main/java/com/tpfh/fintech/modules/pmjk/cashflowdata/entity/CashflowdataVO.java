/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.pmjk.cashflowdata.entity;

public class CashflowdataVO {
    private String beneficiary;
    private String subtotal;
    private String total;
    private String ratio;

    public String getBeneficiary() {
        return this.beneficiary;
    }

    public String getSubtotal() {
        return this.subtotal;
    }

    public String getTotal() {
        return this.total;
    }

    public String getRatio() {
        return this.ratio;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CashflowdataVO)) {
            return false;
        }
        CashflowdataVO other = (CashflowdataVO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$beneficiary = this.getBeneficiary();
        String other$beneficiary = other.getBeneficiary();
        if (this$beneficiary == null ? other$beneficiary != null : !this$beneficiary.equals(other$beneficiary)) {
            return false;
        }
        String this$subtotal = this.getSubtotal();
        String other$subtotal = other.getSubtotal();
        if (this$subtotal == null ? other$subtotal != null : !this$subtotal.equals(other$subtotal)) {
            return false;
        }
        String this$total = this.getTotal();
        String other$total = other.getTotal();
        if (this$total == null ? other$total != null : !this$total.equals(other$total)) {
            return false;
        }
        String this$ratio = this.getRatio();
        String other$ratio = other.getRatio();
        return !(this$ratio == null ? other$ratio != null : !this$ratio.equals(other$ratio));
    }

    protected boolean canEqual(Object other) {
        return other instanceof CashflowdataVO;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $beneficiary = this.getBeneficiary();
        result = result * 59 + ($beneficiary == null ? 43 : $beneficiary.hashCode());
        String $subtotal = this.getSubtotal();
        result = result * 59 + ($subtotal == null ? 43 : $subtotal.hashCode());
        String $total = this.getTotal();
        result = result * 59 + ($total == null ? 43 : $total.hashCode());
        String $ratio = this.getRatio();
        result = result * 59 + ($ratio == null ? 43 : $ratio.hashCode());
        return result;
    }

    public String toString() {
        return "CashflowdataVO(beneficiary=" + this.getBeneficiary() + ", subtotal=" + this.getSubtotal() + ", total=" + this.getTotal() + ", ratio=" + this.getRatio() + ")";
    }
}

