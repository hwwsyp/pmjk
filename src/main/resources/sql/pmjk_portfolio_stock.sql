-- 组合库存（来源：外部库 t_d_ai_stock，经同步写入本表）
-- 业务库：Oracle（与 pmjk 其它表一致，连接 spring.datasource.druid.bbg）

-- ========== Oracle ==========
CREATE TABLE pmjk_portfolio_stock (
    id              NUMBER(19)      NOT NULL,
    port_code       VARCHAR2(64),
    stock_date      DATE,
    sec_code        VARCHAR2(64),
    dc_code         VARCHAR2(32),
    amount          NUMBER(20, 4),
    km_code         VARCHAR2(64),
    km_name         VARCHAR2(256),
    createtimestamp DATE,
    updatetimestamp DATE,
    islock          VARCHAR2(8),
    CONSTRAINT pk_pmjk_portfolio_stock PRIMARY KEY (id)
);

COMMENT ON TABLE pmjk_portfolio_stock IS '组合库存';
COMMENT ON COLUMN pmjk_portfolio_stock.port_code IS '组合代码 c_port_code';
COMMENT ON COLUMN pmjk_portfolio_stock.stock_date IS '库存日期 d_stock';
COMMENT ON COLUMN pmjk_portfolio_stock.sec_code IS '证券代码 c_sec_code';
COMMENT ON COLUMN pmjk_portfolio_stock.dc_code IS '币种/市场代码 c_dc_code';
COMMENT ON COLUMN pmjk_portfolio_stock.amount IS '数量 n_amount';
COMMENT ON COLUMN pmjk_portfolio_stock.km_code IS '科目代码 c_km_code';
COMMENT ON COLUMN pmjk_portfolio_stock.km_name IS '科目名称 c_km_name';

CREATE SEQUENCE pmjk_portfolio_stock_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_pmjk_portfolio_stock_id
BEFORE INSERT ON pmjk_portfolio_stock
FOR EACH ROW
WHEN (NEW.id IS NULL)
BEGIN
    SELECT pmjk_portfolio_stock_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

CREATE INDEX idx_pmjk_portfolio_stock_date ON pmjk_portfolio_stock (stock_date);
CREATE INDEX idx_pmjk_portfolio_stock_port ON pmjk_portfolio_stock (port_code);

-- ========== MySQL（若本地仅用 MySQL 验证，可选用；生产仍以 Oracle 为准）==========
/*
CREATE TABLE pmjk_portfolio_stock (
    id              BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    port_code       VARCHAR(64)     DEFAULT NULL COMMENT '组合代码',
    stock_date      DATE            DEFAULT NULL COMMENT '库存日期',
    sec_code        VARCHAR(64)     DEFAULT NULL COMMENT '证券代码',
    dc_code         VARCHAR(32)     DEFAULT NULL COMMENT '币种/市场代码',
    amount          DECIMAL(20, 4)  DEFAULT NULL COMMENT '数量',
    km_code         VARCHAR(64)     DEFAULT NULL COMMENT '科目代码',
    km_name         VARCHAR(256)    DEFAULT NULL COMMENT '科目名称',
    createtimestamp DATETIME        DEFAULT NULL,
    updatetimestamp DATETIME        DEFAULT NULL,
    islock          VARCHAR(8)      DEFAULT NULL,
    KEY idx_pmjk_portfolio_stock_date (stock_date),
    KEY idx_pmjk_portfolio_stock_port (port_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组合库存';
*/

-- 菜单需在「系统管理-菜单管理」中配置，示例（请按实际 parent_id 调整）：
-- 一级：现金流分配  url 留空或目录
-- 二级：组合库存  url /pmjk/cashflowalloc/portfoliostock  权限 pmjk:portfoliostock:list 等
