-- 理财品种（来源：vas9.t_p_sv_sec_base，经同步写入本表）
-- 另类系统产品简称 ↔ c_sm_code（sm_code）

CREATE TABLE pmjk_financial_variety (
    id              NUMBER(19)      NOT NULL,
    sec_code        VARCHAR2(64),
    sec_mkt_code    VARCHAR2(64),
    sec_isin_code   VARCHAR2(64),
    sm_code         VARCHAR2(64),
    sec_name        VARCHAR2(256),
    dc_code         VARCHAR2(32),
    sec_var_code    VARCHAR2(32),
    mkt_code        VARCHAR2(32),
    createtimestamp DATE,
    updatetimestamp DATE,
    islock          VARCHAR2(8),
    CONSTRAINT pk_pmjk_financial_variety PRIMARY KEY (id)
);

COMMENT ON TABLE pmjk_financial_variety IS '理财品种';
COMMENT ON COLUMN pmjk_financial_variety.sec_code IS '证券代码 c_sec_code';
COMMENT ON COLUMN pmjk_financial_variety.sec_mkt_code IS '证券行情代码 c_sec_mkt_code';
COMMENT ON COLUMN pmjk_financial_variety.sec_isin_code IS 'ISIN c_sec_isin_code';
COMMENT ON COLUMN pmjk_financial_variety.sm_code IS 'SM代码 c_sm_code，对应另类产品简称';
COMMENT ON COLUMN pmjk_financial_variety.sec_name IS '证券名称 c_sec_name';
COMMENT ON COLUMN pmjk_financial_variety.dc_code IS '币种 c_dc_code';
COMMENT ON COLUMN pmjk_financial_variety.sec_var_code IS '品种代码 c_sec_var_code';
COMMENT ON COLUMN pmjk_financial_variety.mkt_code IS '市场代码 c_mkt_code';

CREATE SEQUENCE pmjk_financial_variety_seq
    START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE OR REPLACE TRIGGER trg_pmjk_financial_variety_id
BEFORE INSERT ON pmjk_financial_variety
FOR EACH ROW
WHEN (NEW.id IS NULL)
BEGIN
    SELECT pmjk_financial_variety_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

CREATE INDEX idx_pmjk_fin_variety_sm ON pmjk_financial_variety (sm_code);
CREATE INDEX idx_pmjk_fin_variety_sec ON pmjk_financial_variety (sec_code);
