-- 现金流分配（分红派息按组合库存比例拆分）

CREATE TABLE pmjk_cashflow_divide_batch (
    id                    NUMBER(19)      NOT NULL,
    stock_date            DATE,
    product_short_name    VARCHAR2(128),
    sec_code              VARCHAR2(64),
    sm_code               VARCHAR2(64),
    total_allocate_amount NUMBER(20, 4),
    total_bank_fee        NUMBER(20, 4),
    tax_mode              VARCHAR2(16),
    total_tax_amount      NUMBER(20, 4),
    global_tax_rate       NUMBER(12, 8),
    transfer_date         DATE,
    trade_date            DATE,
    cash_account          VARCHAR2(128),
    desc_prefix           VARCHAR2(256),
    tpt_port_code         VARCHAR2(64),
    investment_manager    VARCHAR2(256),
    createtimestamp       DATE,
    updatetimestamp       DATE,
    CONSTRAINT pk_pmjk_cashflow_divide_batch PRIMARY KEY (id)
);

CREATE TABLE pmjk_cashflow_divide_line (
    id                    NUMBER(19)      NOT NULL,
    batch_id              NUMBER(19)      NOT NULL,
    line_no               NUMBER(10),
    biz_type              VARCHAR2(16),
    biz_subtype           VARCHAR2(16),
    transfer_date         DATE,
    trade_date            DATE,
    flow_direction        VARCHAR2(16),
    port_code             VARCHAR2(64),
    investment_manager    VARCHAR2(256),
    cash_account          VARCHAR2(128),
    receivable_payable    NUMBER(20, 4),
    tx_amount             NUMBER(20, 4),
    fee_channel           VARCHAR2(64),
    sec_code              VARCHAR2(64),
    description           VARCHAR2(512),
    CONSTRAINT pk_pmjk_cashflow_divide_line PRIMARY KEY (id)
);

CREATE SEQUENCE pmjk_cashflow_divide_batch_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE SEQUENCE pmjk_cashflow_divide_line_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE OR REPLACE TRIGGER trg_pmjk_cf_divide_batch_id
BEFORE INSERT ON pmjk_cashflow_divide_batch FOR EACH ROW WHEN (NEW.id IS NULL)
BEGIN SELECT pmjk_cashflow_divide_batch_seq.NEXTVAL INTO :NEW.id FROM DUAL; END;
/

CREATE OR REPLACE TRIGGER trg_pmjk_cf_divide_line_id
BEFORE INSERT ON pmjk_cashflow_divide_line FOR EACH ROW WHEN (NEW.id IS NULL)
BEGIN SELECT pmjk_cashflow_divide_line_seq.NEXTVAL INTO :NEW.id FROM DUAL; END;
/

CREATE INDEX idx_pmjk_cf_divide_line_batch ON pmjk_cashflow_divide_line (batch_id);
