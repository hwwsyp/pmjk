/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotations.TableId
 *  com.baomidou.mybatisplus.annotations.TableName
 *  com.fasterxml.jackson.annotation.JsonFormat
 *  org.hibernate.validator.constraints.NotBlank
 */
package com.tpfh.fintech.modules.job.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.validator.constraints.NotBlank;

@TableName(value="schedule_job")
public class ScheduleJobEntity
implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
    @TableId
    private Long jobId;
    @NotBlank(message="bean\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="bean\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a") String beanName;
    @NotBlank(message="\u65b9\u6cd5\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u65b9\u6cd5\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a") String methodName;
    private String params;
    @NotBlank(message="cron\u8868\u8fbe\u5f0f\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="cron\u8868\u8fbe\u5f0f\u4e0d\u80fd\u4e3a\u7a7a") String cronExpression;
    private Integer status;
    private String remark;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getJobId() {
        return this.jobId;
    }

    public String getBeanName() {
        return this.beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return this.params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getCronExpression() {
        return this.cronExpression;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }
}

