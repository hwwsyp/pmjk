package com.tpfh.fintech.modules.pmjk.cashflowdivide.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.dao.CashflowDivideBatchDao;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.dao.CashflowDivideLineDao;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.dto.CashflowDivideCalculateRequest;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.entity.CashflowDivideBatchEntity;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.entity.CashflowDivideLineEntity;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.service.CashflowDivideService;
import com.tpfh.fintech.modules.pmjk.cashflowdivide.vo.PortAllocationVo;
import com.tpfh.fintech.modules.pmjk.financialvariety.entity.FinancialVarietyEntity;
import com.tpfh.fintech.modules.pmjk.financialvariety.service.FinancialVarietyService;
import com.tpfh.fintech.modules.pmjk.portfoliostock.entity.PortfolioStockEntity;
import com.tpfh.fintech.modules.pmjk.portfoliostock.service.PortfolioStockService;
import com.tpfh.fintech.modules.pmjk.product.vo.ProductVo;
import com.tpfh.fintech.modules.pmjk.product.service.ProductService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "cashflowDivideService")
public class CashflowDivideServiceImpl implements CashflowDivideService {

    private static final int BIG_PORT_CODE_LEN = 6;
    private static final int STOCK_SCALE = 4;

    @Autowired
    private PortfolioStockService portfolioStockService;
    @Autowired
    private FinancialVarietyService financialVarietyService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CashflowDivideBatchDao cashflowDivideBatchDao;
    @Autowired
    private CashflowDivideLineDao cashflowDivideLineDao;

    @Override
    public PageUtils queryBatchPage(HashMap<String, Object> params) {
        Page<CashflowDivideBatchEntity> page = new Page<>();
        page.setCurrent(Integer.parseInt(params.get("page").toString()));
        page.setSize(Integer.parseInt(params.get("limit").toString()));
        page.setRecords(this.cashflowDivideBatchDao.getBatchListForPage(page, params));
        return new PageUtils(page);
    }

    @Override
    public Map<String, Object> calculate(CashflowDivideCalculateRequest request) {
        validateRequest(request);
        String secCode = resolveSecCode(request);
        Date transferDate = parseDate(request.getTransferDate(), "调拨日期");
        Date tradeDate = parseDate(request.getTradeDate(), "成交日期");
        String cashAccount = defaultIfBlank(request.getCashAccount(), "BOCHK-MK-USD-SA");
        String tptPortCode = defaultIfBlank(request.getTptPortCode(), "000000");
        String descPrefix = defaultIfBlank(request.getDescPrefix(), resolveProductName(request));
        String investmentManager = resolveInvestmentManager(request);

        List<PortAllocationVo> allocations = buildAllocations(request, secCode);
        List<CashflowDivideLineEntity> lines = buildEntryLines(
                allocations, secCode, transferDate, tradeDate, cashAccount, tptPortCode, descPrefix, investmentManager);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("secCode", secCode);
        result.put("allocations", allocations);
        result.put("lines", lines);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveBatch(CashflowDivideCalculateRequest request, List<CashflowDivideLineEntity> lines) {
        Map<String, Object> calc = calculate(request);
        if (lines == null || lines.isEmpty()) {
            lines = (List<CashflowDivideLineEntity>) calc.get("lines");
        }
        String secCode = (String) calc.get("secCode");
        Date now = new Date();
        CashflowDivideBatchEntity batch = new CashflowDivideBatchEntity();
        batch.setStockDate(parseDate(request.getStockDate(), "库存日期"));
        batch.setProductShortName(request.getProductShortName());
        batch.setSecCode(secCode);
        batch.setSmCode(request.getProductShortName());
        batch.setTotalAllocateAmount(request.getTotalAllocateAmount());
        batch.setTotalBankFee(request.getTotalBankFee());
        batch.setAllocateMode(normalizeAllocateMode(request.getAllocateMode()));
        batch.setTotalTaxAmount(request.getTotalTaxAmount());
        batch.setGlobalAllocateRatio(request.getGlobalAllocateRatio());
        batch.setTransferDate(parseDate(request.getTransferDate(), "调拨日期"));
        batch.setTradeDate(parseDate(request.getTradeDate(), "成交日期"));
        batch.setCashAccount(defaultIfBlank(request.getCashAccount(), "BOCHK-MK-USD-SA"));
        batch.setDescPrefix(defaultIfBlank(request.getDescPrefix(), resolveProductName(request)));
        batch.setTptPortCode(defaultIfBlank(request.getTptPortCode(), "000000"));
        batch.setInvestmentManager(resolveInvestmentManager(request));
        batch.setCreatetimestamp(now);
        batch.setUpdatetimestamp(now);
        this.cashflowDivideBatchDao.insert(batch);

        int lineNo = 1;
        for (CashflowDivideLineEntity line : lines) {
            line.setId(null);
            line.setBatchId(batch.getId());
            line.setLineNo(lineNo++);
            this.cashflowDivideLineDao.insert(line);
        }
        return batch.getId();
    }

    @Override
    public CashflowDivideBatchEntity getBatchById(Long id) {
        return this.cashflowDivideBatchDao.selectById(id);
    }

    @Override
    public List<CashflowDivideLineEntity> getLinesByBatchId(Long batchId) {
        return this.cashflowDivideLineDao.listByBatchId(batchId);
    }

    private void validateRequest(CashflowDivideCalculateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        if (StringUtils.isBlank(request.getStockDate())) {
            throw new IllegalArgumentException("库存日期不能为空");
        }
        if (request.getTotalBankFee() == null) {
            request.setTotalBankFee(BigDecimal.ZERO);
        }
        if (request.getTotalBankFee().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("银行手续费总额不能为负数");
        }
        if (request.getTotalTaxAmount() == null) {
            request.setTotalTaxAmount(BigDecimal.ZERO);
        }
        if (request.getTotalTaxAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("待分担税费总额不能为负数");
        }
        String allocateMode = normalizeAllocateMode(request.getAllocateMode());
        request.setAllocateMode(allocateMode);
        if ("TOTAL".equals(allocateMode)) {
            if (request.getTotalAllocateAmount() == null || request.getTotalAllocateAmount().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("人工总额模式下待分配资金总额必须大于0");
            }
        } else if ("RATE".equals(allocateMode)) {
            if (request.getGlobalAllocateRatio() == null) {
                throw new IllegalArgumentException("固定比例模式下请填写统一比例");
            }
        } else if (!"TOTAL".equals(allocateMode)) {
            throw new IllegalArgumentException("待分配方式仅支持 TOTAL 或 RATE");
        }
    }

    private FinancialVarietyEntity findVarietyBySmCode(String smCode) {
        if (StringUtils.isBlank(smCode)) {
            return null;
        }
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("smCode", smCode);
        List<FinancialVarietyEntity> list = this.financialVarietyService.getInfoList(params);
        if (list != null) {
            for (FinancialVarietyEntity item : list) {
                if (smCode.equals(item.getSmCode())) {
                    return item;
                }
            }
        }
        return null;
    }

    @Override
    public String resolveProductNameBySmCode(String smCode) {
        CashflowDivideCalculateRequest request = new CashflowDivideCalculateRequest();
        request.setProductShortName(smCode);
        return resolveProductName(request);
    }

    /** 产品名称（pmjk 产品概要 productname），用于描述前缀默认值 */
    private String resolveProductName(CashflowDivideCalculateRequest request) {
        String smCode = request.getProductShortName();
        if (StringUtils.isBlank(smCode)) {
            return "现金分配";
        }
        FinancialVarietyEntity variety = findVarietyBySmCode(smCode);
        if (variety != null && StringUtils.isNotBlank(variety.getSecName())) {
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("page", 1);
            params.put("limit", 5);
            params.put("productname", variety.getSecName());
            PageUtils page = this.productService.queryPage(params);
            if (page != null && page.getList() != null && !page.getList().isEmpty()) {
                ProductVo vo = (ProductVo) page.getList().get(0);
                if (StringUtils.isNotBlank(vo.getProductname())) {
                    return vo.getProductname();
                }
            }
        }
        HashMap<String, Object> byShort = new HashMap<String, Object>();
        byShort.put("page", 1);
        byShort.put("limit", 1);
        byShort.put("productshortname", smCode);
        PageUtils pageByShort = this.productService.queryPage(byShort);
        if (pageByShort != null && pageByShort.getList() != null && !pageByShort.getList().isEmpty()) {
            ProductVo vo = (ProductVo) pageByShort.getList().get(0);
            if (StringUtils.isNotBlank(vo.getProductname())) {
                return vo.getProductname();
            }
        }
        if (variety != null && StringUtils.isNotBlank(variety.getSecName())) {
            return variety.getSecName();
        }
        return smCode;
    }

    private String resolveSecCode(CashflowDivideCalculateRequest request) {
        if (StringUtils.isNotBlank(request.getSecCode())) {
            return request.getSecCode();
        }
        if (StringUtils.isBlank(request.getProductShortName())) {
            throw new IllegalArgumentException("请填写产品简称或证券代码");
        }
        FinancialVarietyEntity item = findVarietyBySmCode(request.getProductShortName());
        if (item != null && StringUtils.isNotBlank(item.getSecCode())) {
            return item.getSecCode();
        }
        throw new IllegalArgumentException("理财品种中未找到 SM 代码：" + request.getProductShortName());
    }

    private String resolveInvestmentManager(CashflowDivideCalculateRequest request) {
        if (StringUtils.isNotBlank(request.getInvestmentManager())) {
            return request.getInvestmentManager();
        }
        if (StringUtils.isBlank(request.getProductShortName())) {
            return "";
        }
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("productshortname", request.getProductShortName());
        params.put("page", 1);
        params.put("limit", 1);
        PageUtils page = this.productService.queryPage(params);
        if (page != null && page.getList() != null && !page.getList().isEmpty()) {
            ProductVo vo = (ProductVo) page.getList().get(0);
            return vo.getManagername() != null ? vo.getManagername() : "";
        }
        return "";
    }

    private List<PortAllocationVo> buildAllocations(CashflowDivideCalculateRequest request, String secCode) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("stockDate", request.getStockDate());
        params.put("secCode", secCode);
        params.put("kmCode", "1111.99.01");
        List<PortfolioStockEntity> stocks = this.portfolioStockService.getInfoList(params);
        if (stocks == null || stocks.isEmpty()) {
            throw new IllegalArgumentException("未找到组合库存，请先同步库存并确认日期与证券代码");
        }

        Map<String, BigDecimal> portSum = new LinkedHashMap<String, BigDecimal>();
        for (PortfolioStockEntity stock : stocks) {
            if (stock.getPortCode() == null || stock.getAmount() == null) {
                continue;
            }
            BigDecimal prev = portSum.get(stock.getPortCode());
            portSum.put(stock.getPortCode(), prev == null ? stock.getAmount() : prev.add(stock.getAmount()));
        }
        if (portSum.isEmpty()) {
            throw new IllegalArgumentException("组合库存数量为空");
        }

        BigDecimal totalStock = BigDecimal.ZERO;
        for (BigDecimal v : portSum.values()) {
            totalStock = totalStock.add(v);
        }

        String allocateMode = normalizeAllocateMode(request.getAllocateMode());

        List<PortAllocationVo> list = new ArrayList<PortAllocationVo>();
        BigDecimal totalAlloc = request.getTotalAllocateAmount() == null ? BigDecimal.ZERO : request.getTotalAllocateAmount();
        BigDecimal totalFee = request.getTotalBankFee();
        BigDecimal totalTax = request.getTotalTaxAmount();

        for (Map.Entry<String, BigDecimal> e : portSum.entrySet()) {
            BigDecimal ratio = e.getValue().divide(totalStock, 12, RoundingMode.HALF_UP);
            BigDecimal gross = computePortGross(request, allocateMode, e.getValue(), ratio, totalAlloc);
            BigDecimal fee = totalFee.multiply(ratio).setScale(2, RoundingMode.HALF_UP);
            BigDecimal tax = totalTax.multiply(ratio).setScale(2, RoundingMode.HALF_UP);
            BigDecimal net = gross.subtract(fee).subtract(tax).setScale(2, RoundingMode.HALF_UP);

            PortAllocationVo vo = new PortAllocationVo();
            vo.setPortCode(e.getKey());
            vo.setStockAmount(scaleStock(e.getValue()));
            vo.setRatio(ratio);
            vo.setGrossAllocate(gross);
            vo.setBankFee(fee);
            vo.setTaxAmount(tax);
            vo.setNetAmount(net);
            list.add(vo);
        }
        return list;
    }

    /** 待分配毛额：TOTAL 按库存占比分总额；RATE 为库存×统一比例 */
    private BigDecimal computePortGross(
            CashflowDivideCalculateRequest request,
            String allocateMode,
            BigDecimal stockAmount,
            BigDecimal stockRatio,
            BigDecimal totalAllocateAmount) {
        if ("RATE".equals(allocateMode)) {
            return stockAmount.multiply(request.getGlobalAllocateRatio()).setScale(2, RoundingMode.HALF_UP);
        }
        if ("TOTAL".equals(allocateMode)) {
            return totalAllocateAmount.multiply(stockRatio).setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    private String normalizeAllocateMode(String allocateMode) {
        if (StringUtils.isBlank(allocateMode)) {
            return "TOTAL";
        }
        return allocateMode.trim().toUpperCase();
    }

    private List<CashflowDivideLineEntity> buildEntryLines(
            List<PortAllocationVo> allocations,
            String secCode,
            Date transferDate,
            Date tradeDate,
            String cashAccount,
            String tptPortCode,
            String descPrefix,
            String investmentManager) {

        List<CashflowDivideLineEntity> lines = new ArrayList<CashflowDivideLineEntity>();

        for (PortAllocationVo p : allocations) {
            String stockSmallPort = p.getPortCode();
            BigDecimal net = p.getNetAmount();
            BigDecimal fee = p.getBankFee();
            BigDecimal tax = p.getTaxAmount() == null ? BigDecimal.ZERO : p.getTaxAmount();

            // 自库存小组合流出：手续费、税费(有则生成)、实收（大组合=左6位 + 库存小组合代码各一笔）
            if (isPositiveAmount(fee)) {
                addInventoryOutPair(lines, "03", "BKC", transferDate, tradeDate, stockSmallPort, cashAccount,
                        investmentManager, secCode, fee, "", "Bank Charge - " + descPrefix);
            }

            if (isPositiveAmount(tax)) {
                addInventoryOutPair(lines, "03", "QTFY", transferDate, tradeDate, stockSmallPort, cashAccount,
                        investmentManager, secCode, tax, "QTL_SF", "Non-resident Alien Tax - " + descPrefix);
            }

            addInventoryOutPair(lines, "04", "", transferDate, tradeDate, stockSmallPort, cashAccount, investmentManager,
                    secCode, net, "", descPrefix + " - AI to MK");
        }
        return lines;
    }

    private boolean isPositiveAmount(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }

    private BigDecimal scaleStock(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return value.setScale(STOCK_SCALE, RoundingMode.HALF_UP);
    }

    /** 大组合代码：库存小组合代码左侧 6 位 */
    private String resolveBigPortCode(String stockSmallPortCode) {
        if (StringUtils.isBlank(stockSmallPortCode)) {
            return "";
        }
        String trimmed = stockSmallPortCode.trim();
        if (trimmed.length() <= BIG_PORT_CODE_LEN) {
            return trimmed;
        }
        return trimmed.substring(0, BIG_PORT_CODE_LEN);
    }

    /** 库存侧流出：大组合(左6位) + 库存小组合代码各一笔 */
    private void addInventoryOutPair(List<CashflowDivideLineEntity> lines, String bizType, String bizSubtype,
            Date transferDate, Date tradeDate, String stockSmallPortCode, String cashAccount, String manager,
            String secCode, BigDecimal amount, String feeChannel, String desc) {
        String bigPort = resolveBigPortCode(stockSmallPortCode);
        addLine(lines, bizType, bizSubtype, transferDate, tradeDate, "流出", bigPort, cashAccount, manager, secCode,
                amount, feeChannel, desc);
        addLine(lines, bizType, bizSubtype, transferDate, tradeDate, "流出", stockSmallPortCode, cashAccount, manager,
                secCode, amount, feeChannel, desc);
    }

    private void addLine(List<CashflowDivideLineEntity> lines, String bizType, String bizSubtype,
            Date transferDate, Date tradeDate, String flow, String portCode, String cashAccount, String manager,
            String secCode, BigDecimal amount, String feeChannel, String desc) {
        CashflowDivideLineEntity line = new CashflowDivideLineEntity();
        line.setBizType(bizType);
        line.setBizSubtype(bizSubtype == null ? "" : bizSubtype);
        line.setTransferDate(transferDate);
        line.setTradeDate(tradeDate);
        line.setFlowDirection(flow);
        line.setPortCode(portCode);
        line.setInvestmentManager(manager);
        line.setCashAccount(cashAccount);
        line.setReceivablePayable(null);
        line.setTxAmount(amount);
        line.setFeeChannel(feeChannel);
        line.setSecCode(secCode);
        line.setDescription(desc);
        lines.add(line);
    }

    private Date parseDate(String text, String label) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException(label + "不能为空");
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(text);
        } catch (ParseException e) {
            throw new IllegalArgumentException(label + "格式应为 yyyy-MM-dd");
        }
    }

    private String defaultIfBlank(String value, String defaultValue) {
        return StringUtils.isBlank(value) ? defaultValue : value;
    }
}
