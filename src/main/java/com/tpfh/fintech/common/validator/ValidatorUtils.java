/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.validation.ConstraintViolation
 *  javax.validation.Validation
 *  javax.validation.Validator
 */
package com.tpfh.fintech.common.validator;

import com.tpfh.fintech.common.exception.TpfhException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorUtils {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static void validateEntity(Object object, Class<?> ... groups) throws TpfhException {
        Set constraintViolations = validator.validate(object, (Class[])groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append("</br>");
            }
            throw new TpfhException(msg.toString());
        }
    }
}

