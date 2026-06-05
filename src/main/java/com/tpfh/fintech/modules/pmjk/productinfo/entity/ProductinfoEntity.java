/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.productinfo.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.math.BigDecimal;

@TableName(value="pmjk_productinfo")
public class ProductinfoEntity {
    private String glf;
    private String currency;
    private String enddate;
    private String country;
    private String region;
    private String registration;
    private Long pmtype;
    private Long expectedrate;
    private Long capitalsource;
    private Long isend;
    private String investmentdeadline;
    private Long periodtype;
    private Long issuancescale;
    private Long couponfrequency;
    private String ratetypename;
    private Long isimportant;
    private String priorityrate;
    private String gicsindustry;
    private Long buyprice;
    private Long projecttype;
    private String capitalcap;
    private String startdate;
    private String productcode;
    private String productname;
    private Long servenationalstrategyflag;
    private Long groupbuyscale;
    private Long investtype;
    private Long ratetype;
    private Long isexpire;
    private Long servenationalstrategytype;
    private Long versionnum;
    private BigDecimal buyrate;
    private Long buylevel;
    private Long realestateprojectflag;
    private String otherprojecttypename;
    private Long productid;
    private String manager;
    private Long buyscale;
    private String keytime;
    private String expectedexitway;
    private Long valuation;
    private Long syncinvestsystemflag;
    private String servenationalstrategyfield;
    private Long id;
    private String industry;
    private String cashdividend;
    private String productshortname;

    public String getGlf() {
        return this.glf;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getEnddate() {
        return this.enddate;
    }

    public String getCountry() {
        return this.country;
    }

    public String getRegion() {
        return this.region;
    }

    public String getRegistration() {
        return this.registration;
    }

    public Long getPmtype() {
        return this.pmtype;
    }

    public Long getExpectedrate() {
        return this.expectedrate;
    }

    public Long getCapitalsource() {
        return this.capitalsource;
    }

    public Long getIsend() {
        return this.isend;
    }

    public String getInvestmentdeadline() {
        return this.investmentdeadline;
    }

    public Long getPeriodtype() {
        return this.periodtype;
    }

    public Long getIssuancescale() {
        return this.issuancescale;
    }

    public Long getCouponfrequency() {
        return this.couponfrequency;
    }

    public String getRatetypename() {
        return this.ratetypename;
    }

    public Long getIsimportant() {
        return this.isimportant;
    }

    public String getPriorityrate() {
        return this.priorityrate;
    }

    public String getGicsindustry() {
        return this.gicsindustry;
    }

    public Long getBuyprice() {
        return this.buyprice;
    }

    public Long getProjecttype() {
        return this.projecttype;
    }

    public String getCapitalcap() {
        return this.capitalcap;
    }

    public String getStartdate() {
        return this.startdate;
    }

    public String getProductcode() {
        return this.productcode;
    }

    public String getProductname() {
        return this.productname;
    }

    public Long getServenationalstrategyflag() {
        return this.servenationalstrategyflag;
    }

    public Long getGroupbuyscale() {
        return this.groupbuyscale;
    }

    public Long getInvesttype() {
        return this.investtype;
    }

    public Long getRatetype() {
        return this.ratetype;
    }

    public Long getIsexpire() {
        return this.isexpire;
    }

    public Long getServenationalstrategytype() {
        return this.servenationalstrategytype;
    }

    public Long getVersionnum() {
        return this.versionnum;
    }

    public BigDecimal getBuyrate() {
        return this.buyrate;
    }

    public Long getBuylevel() {
        return this.buylevel;
    }

    public Long getRealestateprojectflag() {
        return this.realestateprojectflag;
    }

    public String getOtherprojecttypename() {
        return this.otherprojecttypename;
    }

    public Long getProductid() {
        return this.productid;
    }

    public String getManager() {
        return this.manager;
    }

    public Long getBuyscale() {
        return this.buyscale;
    }

    public String getKeytime() {
        return this.keytime;
    }

    public String getExpectedexitway() {
        return this.expectedexitway;
    }

    public Long getValuation() {
        return this.valuation;
    }

    public Long getSyncinvestsystemflag() {
        return this.syncinvestsystemflag;
    }

    public String getServenationalstrategyfield() {
        return this.servenationalstrategyfield;
    }

    public Long getId() {
        return this.id;
    }

    public String getIndustry() {
        return this.industry;
    }

    public String getCashdividend() {
        return this.cashdividend;
    }

    public String getProductshortname() {
        return this.productshortname;
    }

    public void setGlf(String glf) {
        this.glf = glf;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public void setPmtype(Long pmtype) {
        this.pmtype = pmtype;
    }

    public void setExpectedrate(Long expectedrate) {
        this.expectedrate = expectedrate;
    }

    public void setCapitalsource(Long capitalsource) {
        this.capitalsource = capitalsource;
    }

    public void setIsend(Long isend) {
        this.isend = isend;
    }

    public void setInvestmentdeadline(String investmentdeadline) {
        this.investmentdeadline = investmentdeadline;
    }

    public void setPeriodtype(Long periodtype) {
        this.periodtype = periodtype;
    }

    public void setIssuancescale(Long issuancescale) {
        this.issuancescale = issuancescale;
    }

    public void setCouponfrequency(Long couponfrequency) {
        this.couponfrequency = couponfrequency;
    }

    public void setRatetypename(String ratetypename) {
        this.ratetypename = ratetypename;
    }

    public void setIsimportant(Long isimportant) {
        this.isimportant = isimportant;
    }

    public void setPriorityrate(String priorityrate) {
        this.priorityrate = priorityrate;
    }

    public void setGicsindustry(String gicsindustry) {
        this.gicsindustry = gicsindustry;
    }

    public void setBuyprice(Long buyprice) {
        this.buyprice = buyprice;
    }

    public void setProjecttype(Long projecttype) {
        this.projecttype = projecttype;
    }

    public void setCapitalcap(String capitalcap) {
        this.capitalcap = capitalcap;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setServenationalstrategyflag(Long servenationalstrategyflag) {
        this.servenationalstrategyflag = servenationalstrategyflag;
    }

    public void setGroupbuyscale(Long groupbuyscale) {
        this.groupbuyscale = groupbuyscale;
    }

    public void setInvesttype(Long investtype) {
        this.investtype = investtype;
    }

    public void setRatetype(Long ratetype) {
        this.ratetype = ratetype;
    }

    public void setIsexpire(Long isexpire) {
        this.isexpire = isexpire;
    }

    public void setServenationalstrategytype(Long servenationalstrategytype) {
        this.servenationalstrategytype = servenationalstrategytype;
    }

    public void setVersionnum(Long versionnum) {
        this.versionnum = versionnum;
    }

    public void setBuyrate(BigDecimal buyrate) {
        this.buyrate = buyrate;
    }

    public void setBuylevel(Long buylevel) {
        this.buylevel = buylevel;
    }

    public void setRealestateprojectflag(Long realestateprojectflag) {
        this.realestateprojectflag = realestateprojectflag;
    }

    public void setOtherprojecttypename(String otherprojecttypename) {
        this.otherprojecttypename = otherprojecttypename;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setBuyscale(Long buyscale) {
        this.buyscale = buyscale;
    }

    public void setKeytime(String keytime) {
        this.keytime = keytime;
    }

    public void setExpectedexitway(String expectedexitway) {
        this.expectedexitway = expectedexitway;
    }

    public void setValuation(Long valuation) {
        this.valuation = valuation;
    }

    public void setSyncinvestsystemflag(Long syncinvestsystemflag) {
        this.syncinvestsystemflag = syncinvestsystemflag;
    }

    public void setServenationalstrategyfield(String servenationalstrategyfield) {
        this.servenationalstrategyfield = servenationalstrategyfield;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setCashdividend(String cashdividend) {
        this.cashdividend = cashdividend;
    }

    public void setProductshortname(String productshortname) {
        this.productshortname = productshortname;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProductinfoEntity)) {
            return false;
        }
        ProductinfoEntity other = (ProductinfoEntity)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Long this$pmtype = this.getPmtype();
        Long other$pmtype = other.getPmtype();
        if (this$pmtype == null ? other$pmtype != null : !((Object)this$pmtype).equals(other$pmtype)) {
            return false;
        }
        Long this$expectedrate = this.getExpectedrate();
        Long other$expectedrate = other.getExpectedrate();
        if (this$expectedrate == null ? other$expectedrate != null : !((Object)this$expectedrate).equals(other$expectedrate)) {
            return false;
        }
        Long this$capitalsource = this.getCapitalsource();
        Long other$capitalsource = other.getCapitalsource();
        if (this$capitalsource == null ? other$capitalsource != null : !((Object)this$capitalsource).equals(other$capitalsource)) {
            return false;
        }
        Long this$isend = this.getIsend();
        Long other$isend = other.getIsend();
        if (this$isend == null ? other$isend != null : !((Object)this$isend).equals(other$isend)) {
            return false;
        }
        Long this$periodtype = this.getPeriodtype();
        Long other$periodtype = other.getPeriodtype();
        if (this$periodtype == null ? other$periodtype != null : !((Object)this$periodtype).equals(other$periodtype)) {
            return false;
        }
        Long this$issuancescale = this.getIssuancescale();
        Long other$issuancescale = other.getIssuancescale();
        if (this$issuancescale == null ? other$issuancescale != null : !((Object)this$issuancescale).equals(other$issuancescale)) {
            return false;
        }
        Long this$couponfrequency = this.getCouponfrequency();
        Long other$couponfrequency = other.getCouponfrequency();
        if (this$couponfrequency == null ? other$couponfrequency != null : !((Object)this$couponfrequency).equals(other$couponfrequency)) {
            return false;
        }
        Long this$isimportant = this.getIsimportant();
        Long other$isimportant = other.getIsimportant();
        if (this$isimportant == null ? other$isimportant != null : !((Object)this$isimportant).equals(other$isimportant)) {
            return false;
        }
        Long this$buyprice = this.getBuyprice();
        Long other$buyprice = other.getBuyprice();
        if (this$buyprice == null ? other$buyprice != null : !((Object)this$buyprice).equals(other$buyprice)) {
            return false;
        }
        Long this$projecttype = this.getProjecttype();
        Long other$projecttype = other.getProjecttype();
        if (this$projecttype == null ? other$projecttype != null : !((Object)this$projecttype).equals(other$projecttype)) {
            return false;
        }
        Long this$servenationalstrategyflag = this.getServenationalstrategyflag();
        Long other$servenationalstrategyflag = other.getServenationalstrategyflag();
        if (this$servenationalstrategyflag == null ? other$servenationalstrategyflag != null : !((Object)this$servenationalstrategyflag).equals(other$servenationalstrategyflag)) {
            return false;
        }
        Long this$groupbuyscale = this.getGroupbuyscale();
        Long other$groupbuyscale = other.getGroupbuyscale();
        if (this$groupbuyscale == null ? other$groupbuyscale != null : !((Object)this$groupbuyscale).equals(other$groupbuyscale)) {
            return false;
        }
        Long this$investtype = this.getInvesttype();
        Long other$investtype = other.getInvesttype();
        if (this$investtype == null ? other$investtype != null : !((Object)this$investtype).equals(other$investtype)) {
            return false;
        }
        Long this$ratetype = this.getRatetype();
        Long other$ratetype = other.getRatetype();
        if (this$ratetype == null ? other$ratetype != null : !((Object)this$ratetype).equals(other$ratetype)) {
            return false;
        }
        Long this$isexpire = this.getIsexpire();
        Long other$isexpire = other.getIsexpire();
        if (this$isexpire == null ? other$isexpire != null : !((Object)this$isexpire).equals(other$isexpire)) {
            return false;
        }
        Long this$servenationalstrategytype = this.getServenationalstrategytype();
        Long other$servenationalstrategytype = other.getServenationalstrategytype();
        if (this$servenationalstrategytype == null ? other$servenationalstrategytype != null : !((Object)this$servenationalstrategytype).equals(other$servenationalstrategytype)) {
            return false;
        }
        Long this$versionnum = this.getVersionnum();
        Long other$versionnum = other.getVersionnum();
        if (this$versionnum == null ? other$versionnum != null : !((Object)this$versionnum).equals(other$versionnum)) {
            return false;
        }
        Long this$buylevel = this.getBuylevel();
        Long other$buylevel = other.getBuylevel();
        if (this$buylevel == null ? other$buylevel != null : !((Object)this$buylevel).equals(other$buylevel)) {
            return false;
        }
        Long this$realestateprojectflag = this.getRealestateprojectflag();
        Long other$realestateprojectflag = other.getRealestateprojectflag();
        if (this$realestateprojectflag == null ? other$realestateprojectflag != null : !((Object)this$realestateprojectflag).equals(other$realestateprojectflag)) {
            return false;
        }
        Long this$productid = this.getProductid();
        Long other$productid = other.getProductid();
        if (this$productid == null ? other$productid != null : !((Object)this$productid).equals(other$productid)) {
            return false;
        }
        Long this$buyscale = this.getBuyscale();
        Long other$buyscale = other.getBuyscale();
        if (this$buyscale == null ? other$buyscale != null : !((Object)this$buyscale).equals(other$buyscale)) {
            return false;
        }
        Long this$valuation = this.getValuation();
        Long other$valuation = other.getValuation();
        if (this$valuation == null ? other$valuation != null : !((Object)this$valuation).equals(other$valuation)) {
            return false;
        }
        Long this$syncinvestsystemflag = this.getSyncinvestsystemflag();
        Long other$syncinvestsystemflag = other.getSyncinvestsystemflag();
        if (this$syncinvestsystemflag == null ? other$syncinvestsystemflag != null : !((Object)this$syncinvestsystemflag).equals(other$syncinvestsystemflag)) {
            return false;
        }
        Long this$id = this.getId();
        Long other$id = other.getId();
        if (this$id == null ? other$id != null : !((Object)this$id).equals(other$id)) {
            return false;
        }
        String this$glf = this.getGlf();
        String other$glf = other.getGlf();
        if (this$glf == null ? other$glf != null : !this$glf.equals(other$glf)) {
            return false;
        }
        String this$currency = this.getCurrency();
        String other$currency = other.getCurrency();
        if (this$currency == null ? other$currency != null : !this$currency.equals(other$currency)) {
            return false;
        }
        String this$enddate = this.getEnddate();
        String other$enddate = other.getEnddate();
        if (this$enddate == null ? other$enddate != null : !this$enddate.equals(other$enddate)) {
            return false;
        }
        String this$country = this.getCountry();
        String other$country = other.getCountry();
        if (this$country == null ? other$country != null : !this$country.equals(other$country)) {
            return false;
        }
        String this$region = this.getRegion();
        String other$region = other.getRegion();
        if (this$region == null ? other$region != null : !this$region.equals(other$region)) {
            return false;
        }
        String this$registration = this.getRegistration();
        String other$registration = other.getRegistration();
        if (this$registration == null ? other$registration != null : !this$registration.equals(other$registration)) {
            return false;
        }
        String this$investmentdeadline = this.getInvestmentdeadline();
        String other$investmentdeadline = other.getInvestmentdeadline();
        if (this$investmentdeadline == null ? other$investmentdeadline != null : !this$investmentdeadline.equals(other$investmentdeadline)) {
            return false;
        }
        String this$ratetypename = this.getRatetypename();
        String other$ratetypename = other.getRatetypename();
        if (this$ratetypename == null ? other$ratetypename != null : !this$ratetypename.equals(other$ratetypename)) {
            return false;
        }
        String this$priorityrate = this.getPriorityrate();
        String other$priorityrate = other.getPriorityrate();
        if (this$priorityrate == null ? other$priorityrate != null : !this$priorityrate.equals(other$priorityrate)) {
            return false;
        }
        String this$gicsindustry = this.getGicsindustry();
        String other$gicsindustry = other.getGicsindustry();
        if (this$gicsindustry == null ? other$gicsindustry != null : !this$gicsindustry.equals(other$gicsindustry)) {
            return false;
        }
        String this$capitalcap = this.getCapitalcap();
        String other$capitalcap = other.getCapitalcap();
        if (this$capitalcap == null ? other$capitalcap != null : !this$capitalcap.equals(other$capitalcap)) {
            return false;
        }
        String this$startdate = this.getStartdate();
        String other$startdate = other.getStartdate();
        if (this$startdate == null ? other$startdate != null : !this$startdate.equals(other$startdate)) {
            return false;
        }
        String this$productcode = this.getProductcode();
        String other$productcode = other.getProductcode();
        if (this$productcode == null ? other$productcode != null : !this$productcode.equals(other$productcode)) {
            return false;
        }
        String this$productname = this.getProductname();
        String other$productname = other.getProductname();
        if (this$productname == null ? other$productname != null : !this$productname.equals(other$productname)) {
            return false;
        }
        BigDecimal this$buyrate = this.getBuyrate();
        BigDecimal other$buyrate = other.getBuyrate();
        if (this$buyrate == null ? other$buyrate != null : !((Object)this$buyrate).equals(other$buyrate)) {
            return false;
        }
        String this$otherprojecttypename = this.getOtherprojecttypename();
        String other$otherprojecttypename = other.getOtherprojecttypename();
        if (this$otherprojecttypename == null ? other$otherprojecttypename != null : !this$otherprojecttypename.equals(other$otherprojecttypename)) {
            return false;
        }
        String this$manager = this.getManager();
        String other$manager = other.getManager();
        if (this$manager == null ? other$manager != null : !this$manager.equals(other$manager)) {
            return false;
        }
        String this$keytime = this.getKeytime();
        String other$keytime = other.getKeytime();
        if (this$keytime == null ? other$keytime != null : !this$keytime.equals(other$keytime)) {
            return false;
        }
        String this$expectedexitway = this.getExpectedexitway();
        String other$expectedexitway = other.getExpectedexitway();
        if (this$expectedexitway == null ? other$expectedexitway != null : !this$expectedexitway.equals(other$expectedexitway)) {
            return false;
        }
        String this$servenationalstrategyfield = this.getServenationalstrategyfield();
        String other$servenationalstrategyfield = other.getServenationalstrategyfield();
        if (this$servenationalstrategyfield == null ? other$servenationalstrategyfield != null : !this$servenationalstrategyfield.equals(other$servenationalstrategyfield)) {
            return false;
        }
        String this$industry = this.getIndustry();
        String other$industry = other.getIndustry();
        if (this$industry == null ? other$industry != null : !this$industry.equals(other$industry)) {
            return false;
        }
        String this$cashdividend = this.getCashdividend();
        String other$cashdividend = other.getCashdividend();
        if (this$cashdividend == null ? other$cashdividend != null : !this$cashdividend.equals(other$cashdividend)) {
            return false;
        }
        String this$productshortname = this.getProductshortname();
        String other$productshortname = other.getProductshortname();
        return !(this$productshortname == null ? other$productshortname != null : !this$productshortname.equals(other$productshortname));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProductinfoEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $pmtype = this.getPmtype();
        result = result * 59 + ($pmtype == null ? 43 : ((Object)$pmtype).hashCode());
        Long $expectedrate = this.getExpectedrate();
        result = result * 59 + ($expectedrate == null ? 43 : ((Object)$expectedrate).hashCode());
        Long $capitalsource = this.getCapitalsource();
        result = result * 59 + ($capitalsource == null ? 43 : ((Object)$capitalsource).hashCode());
        Long $isend = this.getIsend();
        result = result * 59 + ($isend == null ? 43 : ((Object)$isend).hashCode());
        Long $periodtype = this.getPeriodtype();
        result = result * 59 + ($periodtype == null ? 43 : ((Object)$periodtype).hashCode());
        Long $issuancescale = this.getIssuancescale();
        result = result * 59 + ($issuancescale == null ? 43 : ((Object)$issuancescale).hashCode());
        Long $couponfrequency = this.getCouponfrequency();
        result = result * 59 + ($couponfrequency == null ? 43 : ((Object)$couponfrequency).hashCode());
        Long $isimportant = this.getIsimportant();
        result = result * 59 + ($isimportant == null ? 43 : ((Object)$isimportant).hashCode());
        Long $buyprice = this.getBuyprice();
        result = result * 59 + ($buyprice == null ? 43 : ((Object)$buyprice).hashCode());
        Long $projecttype = this.getProjecttype();
        result = result * 59 + ($projecttype == null ? 43 : ((Object)$projecttype).hashCode());
        Long $servenationalstrategyflag = this.getServenationalstrategyflag();
        result = result * 59 + ($servenationalstrategyflag == null ? 43 : ((Object)$servenationalstrategyflag).hashCode());
        Long $groupbuyscale = this.getGroupbuyscale();
        result = result * 59 + ($groupbuyscale == null ? 43 : ((Object)$groupbuyscale).hashCode());
        Long $investtype = this.getInvesttype();
        result = result * 59 + ($investtype == null ? 43 : ((Object)$investtype).hashCode());
        Long $ratetype = this.getRatetype();
        result = result * 59 + ($ratetype == null ? 43 : ((Object)$ratetype).hashCode());
        Long $isexpire = this.getIsexpire();
        result = result * 59 + ($isexpire == null ? 43 : ((Object)$isexpire).hashCode());
        Long $servenationalstrategytype = this.getServenationalstrategytype();
        result = result * 59 + ($servenationalstrategytype == null ? 43 : ((Object)$servenationalstrategytype).hashCode());
        Long $versionnum = this.getVersionnum();
        result = result * 59 + ($versionnum == null ? 43 : ((Object)$versionnum).hashCode());
        Long $buylevel = this.getBuylevel();
        result = result * 59 + ($buylevel == null ? 43 : ((Object)$buylevel).hashCode());
        Long $realestateprojectflag = this.getRealestateprojectflag();
        result = result * 59 + ($realestateprojectflag == null ? 43 : ((Object)$realestateprojectflag).hashCode());
        Long $productid = this.getProductid();
        result = result * 59 + ($productid == null ? 43 : ((Object)$productid).hashCode());
        Long $buyscale = this.getBuyscale();
        result = result * 59 + ($buyscale == null ? 43 : ((Object)$buyscale).hashCode());
        Long $valuation = this.getValuation();
        result = result * 59 + ($valuation == null ? 43 : ((Object)$valuation).hashCode());
        Long $syncinvestsystemflag = this.getSyncinvestsystemflag();
        result = result * 59 + ($syncinvestsystemflag == null ? 43 : ((Object)$syncinvestsystemflag).hashCode());
        Long $id = this.getId();
        result = result * 59 + ($id == null ? 43 : ((Object)$id).hashCode());
        String $glf = this.getGlf();
        result = result * 59 + ($glf == null ? 43 : $glf.hashCode());
        String $currency = this.getCurrency();
        result = result * 59 + ($currency == null ? 43 : $currency.hashCode());
        String $enddate = this.getEnddate();
        result = result * 59 + ($enddate == null ? 43 : $enddate.hashCode());
        String $country = this.getCountry();
        result = result * 59 + ($country == null ? 43 : $country.hashCode());
        String $region = this.getRegion();
        result = result * 59 + ($region == null ? 43 : $region.hashCode());
        String $registration = this.getRegistration();
        result = result * 59 + ($registration == null ? 43 : $registration.hashCode());
        String $investmentdeadline = this.getInvestmentdeadline();
        result = result * 59 + ($investmentdeadline == null ? 43 : $investmentdeadline.hashCode());
        String $ratetypename = this.getRatetypename();
        result = result * 59 + ($ratetypename == null ? 43 : $ratetypename.hashCode());
        String $priorityrate = this.getPriorityrate();
        result = result * 59 + ($priorityrate == null ? 43 : $priorityrate.hashCode());
        String $gicsindustry = this.getGicsindustry();
        result = result * 59 + ($gicsindustry == null ? 43 : $gicsindustry.hashCode());
        String $capitalcap = this.getCapitalcap();
        result = result * 59 + ($capitalcap == null ? 43 : $capitalcap.hashCode());
        String $startdate = this.getStartdate();
        result = result * 59 + ($startdate == null ? 43 : $startdate.hashCode());
        String $productcode = this.getProductcode();
        result = result * 59 + ($productcode == null ? 43 : $productcode.hashCode());
        String $productname = this.getProductname();
        result = result * 59 + ($productname == null ? 43 : $productname.hashCode());
        BigDecimal $buyrate = this.getBuyrate();
        result = result * 59 + ($buyrate == null ? 43 : ((Object)$buyrate).hashCode());
        String $otherprojecttypename = this.getOtherprojecttypename();
        result = result * 59 + ($otherprojecttypename == null ? 43 : $otherprojecttypename.hashCode());
        String $manager = this.getManager();
        result = result * 59 + ($manager == null ? 43 : $manager.hashCode());
        String $keytime = this.getKeytime();
        result = result * 59 + ($keytime == null ? 43 : $keytime.hashCode());
        String $expectedexitway = this.getExpectedexitway();
        result = result * 59 + ($expectedexitway == null ? 43 : $expectedexitway.hashCode());
        String $servenationalstrategyfield = this.getServenationalstrategyfield();
        result = result * 59 + ($servenationalstrategyfield == null ? 43 : $servenationalstrategyfield.hashCode());
        String $industry = this.getIndustry();
        result = result * 59 + ($industry == null ? 43 : $industry.hashCode());
        String $cashdividend = this.getCashdividend();
        result = result * 59 + ($cashdividend == null ? 43 : $cashdividend.hashCode());
        String $productshortname = this.getProductshortname();
        result = result * 59 + ($productshortname == null ? 43 : $productshortname.hashCode());
        return result;
    }

    public String toString() {
        return "ProductinfoEntity(glf=" + this.getGlf() + ", currency=" + this.getCurrency() + ", enddate=" + this.getEnddate() + ", country=" + this.getCountry() + ", region=" + this.getRegion() + ", registration=" + this.getRegistration() + ", pmtype=" + this.getPmtype() + ", expectedrate=" + this.getExpectedrate() + ", capitalsource=" + this.getCapitalsource() + ", isend=" + this.getIsend() + ", investmentdeadline=" + this.getInvestmentdeadline() + ", periodtype=" + this.getPeriodtype() + ", issuancescale=" + this.getIssuancescale() + ", couponfrequency=" + this.getCouponfrequency() + ", ratetypename=" + this.getRatetypename() + ", isimportant=" + this.getIsimportant() + ", priorityrate=" + this.getPriorityrate() + ", gicsindustry=" + this.getGicsindustry() + ", buyprice=" + this.getBuyprice() + ", projecttype=" + this.getProjecttype() + ", capitalcap=" + this.getCapitalcap() + ", startdate=" + this.getStartdate() + ", productcode=" + this.getProductcode() + ", productname=" + this.getProductname() + ", servenationalstrategyflag=" + this.getServenationalstrategyflag() + ", groupbuyscale=" + this.getGroupbuyscale() + ", investtype=" + this.getInvesttype() + ", ratetype=" + this.getRatetype() + ", isexpire=" + this.getIsexpire() + ", servenationalstrategytype=" + this.getServenationalstrategytype() + ", versionnum=" + this.getVersionnum() + ", buyrate=" + this.getBuyrate() + ", buylevel=" + this.getBuylevel() + ", realestateprojectflag=" + this.getRealestateprojectflag() + ", otherprojecttypename=" + this.getOtherprojecttypename() + ", productid=" + this.getProductid() + ", manager=" + this.getManager() + ", buyscale=" + this.getBuyscale() + ", keytime=" + this.getKeytime() + ", expectedexitway=" + this.getExpectedexitway() + ", valuation=" + this.getValuation() + ", syncinvestsystemflag=" + this.getSyncinvestsystemflag() + ", servenationalstrategyfield=" + this.getServenationalstrategyfield() + ", id=" + this.getId() + ", industry=" + this.getIndustry() + ", cashdividend=" + this.getCashdividend() + ", productshortname=" + this.getProductshortname() + ")";
    }
}

