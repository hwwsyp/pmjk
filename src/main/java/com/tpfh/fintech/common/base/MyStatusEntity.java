/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.common.base;

public class MyStatusEntity<T> {
    private Boolean isHavingStatus;
    private T entity;

    public Boolean getIsHavingStatus() {
        return this.isHavingStatus;
    }

    public void setIsHavingStatus(Boolean isHavingStatus) {
        this.isHavingStatus = isHavingStatus;
    }

    public T getEntity() {
        return this.entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}

