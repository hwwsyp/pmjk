/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableName
 */
package com.tpfh.fintech.modules.pmjk.productinfo.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;
import java.util.Date;

@TableName(value="pmjk_productinfo")
public class ProductinfoEntity  {
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
    
    @JsonSerialize(using=ToStringSerializer.class)
    private BigDecimal groupbuyscale;
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
    
    @JsonSerialize(using=ToStringSerializer.class)
    private BigDecimal buyscale;
    
    private String keytime;
    private String expectedexitway;
    private Long valuation;
    private Long syncinvestsystemflag;
    private String servenationalstrategyfield;
    private Long id;
    private String industry;
    private String cashdividend;
    private String productshortname;
    
    private Date createtimestamp;
    private Date updatetimestamp;
    
    

    public Date getCreatetimestamp() {
		return createtimestamp;
	}

	public void setCreatetimestamp(Date createtimestamp) {
		this.createtimestamp = createtimestamp;
	}

	public Date getUpdatetimestamp() {
		return updatetimestamp;
	}

	public void setUpdatetimestamp(Date updatetimestamp) {
		this.updatetimestamp = updatetimestamp;
	}

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


    public BigDecimal getGroupbuyscale() {
		return groupbuyscale;
	}

	public void setGroupbuyscale(BigDecimal groupbuyscale) {
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

    public BigDecimal getBuyscale() {
		return buyscale;
	}

	public void setBuyscale(BigDecimal buyscale) {
		this.buyscale = buyscale;
	}
}

