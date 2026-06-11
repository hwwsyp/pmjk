package com.tpfh.fintech.common.validator;

import org.apache.commons.lang.StringUtils;

import com.tpfh.fintech.common.exception.TpfhException;

/**
 * 数据校验
 * @author tpfh
 * @email tpfh@tpfh.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new TpfhException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new TpfhException(message);
        }
    }
}
