-- 现金流分配批次表增加税费相关字段（已建表环境执行）

ALTER TABLE pmjk_cashflow_divide_batch ADD (
    tax_mode          VARCHAR2(16),
    total_tax_amount  NUMBER(20, 4),
    global_tax_rate   NUMBER(12, 8)
);

COMMENT ON COLUMN pmjk_cashflow_divide_batch.tax_mode IS '税费方式 NONE/TOTAL/RATE/MIXED';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.total_tax_amount IS '待分担税费总额';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.global_tax_rate IS '统一固定税率(小数)';
