/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.shiro.authz.AuthorizationException
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.dao.DuplicateKeyException
 *  org.springframework.web.bind.annotation.ExceptionHandler
 *  org.springframework.web.bind.annotation.RestControllerAdvice
 *  org.springframework.web.servlet.NoHandlerFoundException
 */
package com.tpfh.fintech.common.exception;

import com.tpfh.fintech.common.exception.TpfhException;
import com.tpfh.fintech.common.utils.R;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class TpfhExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value={TpfhException.class})
    public R handleTpfhException(TpfhException e) {
        R r = new R();
        r.put("code", (Object)e.getCode());
        r.put("msg", (Object)e.getMessage());
        return r;
    }

    @ExceptionHandler(value={NoHandlerFoundException.class})
    public R handlerNoFoundException(Exception e) {
        this.logger.error(e.getMessage(), (Throwable)e);
        return R.error(404, "\u8def\u5f84\u4e0d\u5b58\u5728\uff0c\u8bf7\u68c0\u67e5\u8def\u5f84\u662f\u5426\u6b63\u786e");
    }

    @ExceptionHandler(value={DuplicateKeyException.class})
    public R handleDuplicateKeyException(DuplicateKeyException e) {
        this.logger.error(e.getMessage(), (Throwable)e);
        return R.error("\u6570\u636e\u5e93\u4e2d\u5df2\u5b58\u5728\u8be5\u8bb0\u5f55");
    }

    @ExceptionHandler(value={AuthorizationException.class})
    public R handleAuthorizationException(AuthorizationException e) {
        this.logger.error(e.getMessage(), (Throwable)e);
        return R.error("\u6ca1\u6709\u6743\u9650\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458\u6388\u6743");
    }

    @ExceptionHandler(value={Exception.class})
    public R handleException(Exception e) {
        this.logger.error(e.getMessage(), (Throwable)e);
        return R.error();
    }
}

