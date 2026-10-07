-- 现金流分配：按组合库存拆分待分配资金，生成估值录入风格明细
-- 业务库：Oracle（与 pmjk 其它表一致，连接 spring.datasource.druid.bbg）
-- 说明：待分配方式 TOTAL/RATE，批次表保存计算参数快照

-- ========== Oracle ==========

CREATE TABLE pmjk_cashflow_divide_batch (
    id                      NUMBER(19)      NOT NULL,
    stock_date              DATE,
    product_short_name      VARCHAR2(128),
    sec_code                VARCHAR2(64),
    sm_code                 VARCHAR2(64),
    total_allocate_amount   NUMBER(20, 4),
    total_bank_fee          NUMBER(20, 4),
    allocate_mode           VARCHAR2(16),
    total_tax_amount        NUMBER(20, 4),
    global_allocate_ratio   NUMBER(12, 8),
    transfer_date           DATE,
    trade_date              DATE,
    cash_account            VARCHAR2(128),
    desc_prefix             VARCHAR2(256),
    tpt_port_code           VARCHAR2(64),
    investment_manager      VARCHAR2(256),
    createtimestamp         DATE,
    updatetimestamp         DATE,
    CONSTRAINT pk_pmjk_cashflow_divide_batch PRIMARY KEY (id)
);

COMMENT ON TABLE pmjk_cashflow_divide_batch IS '现金流分配-保存批次';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.stock_date IS '组合库存日期';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.product_short_name IS '产品简称(SM 代码)';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.sec_code IS '证券代码';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.sm_code IS 'SM 代码(同 product_short_name)';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.total_allocate_amount IS '人工输入待分配总额(TOTAL 时使用)';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.total_bank_fee IS '银行手续费总额，按库存占比分摊';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.allocate_mode IS '待分配方式 TOTAL/RATE';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.total_tax_amount IS '人工输入待分担税费总额，按库存占比分摊';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.global_allocate_ratio IS '统一固定比例(小数)，毛分配=库存×比例(RATE 时使用)';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.transfer_date IS '调拨日期';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.trade_date IS '成交日期';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.cash_account IS '现金账户';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.desc_prefix IS '录入描述前缀';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.tpt_port_code IS 'TPT 组合代码';
COMMENT ON COLUMN pmjk_cashflow_divide_batch.investment_manager IS '投资经理';

CREATE TABLE pmjk_cashflow_divide_line (
    id                      NUMBER(19)      NOT NULL,
    batch_id                NUMBER(19)      NOT NULL,
    line_no                 NUMBER(10),
    biz_type                VARCHAR2(16),
    biz_subtype             VARCHAR2(16),
    transfer_date           DATE,
    trade_date              DATE,
    flow_direction          VARCHAR2(16),
    port_code               VARCHAR2(64),
    investment_manager      VARCHAR2(256),
    cash_account            VARCHAR2(128),
    receivable_payable      NUMBER(20, 4),
    tx_amount               NUMBER(20, 4),
    fee_channel             VARCHAR2(64),
    sec_code                VARCHAR2(64),
    description             VARCHAR2(512),
    CONSTRAINT pk_pmjk_cashflow_divide_line PRIMARY KEY (id)
);

COMMENT ON TABLE pmjk_cashflow_divide_line IS '现金流分配-录入明细行';
COMMENT ON COLUMN pmjk_cashflow_divide_line.batch_id IS '批次 ID';
COMMENT ON COLUMN pmjk_cashflow_divide_line.line_no IS '行号';
COMMENT ON COLUMN pmjk_cashflow_divide_line.biz_type IS '业务类型 如 03/04';
COMMENT ON COLUMN pmjk_cashflow_divide_line.biz_subtype IS '业务子类型 如 BKC/QTFY';
COMMENT ON COLUMN pmjk_cashflow_divide_line.flow_direction IS '流入/流出';
COMMENT ON COLUMN pmjk_cashflow_divide_line.port_code IS '组合代码(含 _MK_01/_AI_01 后缀行)';
COMMENT ON COLUMN pmjk_cashflow_divide_line.tx_amount IS '交易金额';
COMMENT ON COLUMN pmjk_cashflow_divide_line.fee_channel IS '费用渠道 如 QTL_SF';
COMMENT ON COLUMN pmjk_cashflow_divide_line.description IS '描述';

CREATE SEQUENCE pmjk_cashflow_divide_batch_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE pmjk_cashflow_divide_line_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_pmjk_cf_divide_batch_id
BEFORE INSERT ON pmjk_cashflow_divide_batch
FOR EACH ROW
WHEN (NEW.id IS NULL)
BEGIN
    SELECT pmjk_cashflow_divide_batch_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER trg_pmjk_cf_divide_line_id
BEFORE INSERT ON pmjk_cashflow_divide_line
FOR EACH ROW
WHEN (NEW.id IS NULL)
BEGIN
    SELECT pmjk_cashflow_divide_line_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

CREATE INDEX idx_pmjk_cf_divide_line_batch ON pmjk_cashflow_divide_line (batch_id);
CREATE INDEX idx_pmjk_cf_divide_batch_stock_date ON pmjk_cashflow_divide_batch (stock_date);
