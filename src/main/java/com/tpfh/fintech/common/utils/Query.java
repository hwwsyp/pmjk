/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.commons.lang.StringUtils
 */
package com.tpfh.fintech.common.utils;

import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.common.xss.SQLFilter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

public class Query<T>
extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    private Page<T> page;
    private int currPage = 1;
    private int limit = 10;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        if (params.get("page") != null) {
            this.currPage = Integer.parseInt((String)params.get("page"));
        }
        if (params.get("limit") != null) {
            this.limit = Integer.parseInt((String)params.get("limit"));
        }
        this.put("offset", (this.currPage - 1) * this.limit);
        this.put("page", this.currPage);
        this.put("limit", this.limit);
        String sidx = SQLFilter.sqlInject((String)params.get("sidx"));
        String order = SQLFilter.sqlInject((String)params.get("order"));
        this.put("sidx", sidx);
        this.put("order", order);
        this.page = new Page(this.currPage, this.limit);
        if (StringUtils.isNotBlank((String)sidx) && StringUtils.isNotBlank((String)order)) {
            this.page.setOrderByField(sidx);
            this.page.setAsc("ASC".equalsIgnoreCase(order));
        }
    }

    public Page<T> getPage() {
        return this.page;
    }

    public int getCurrPage() {
        return this.currPage;
    }

    public int getLimit() {
        return this.limit;
    }
}

