package com.tpfh.fintech.common.validator;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.tpfh.fintech.common.exception.TpfhException;

/**
 * 密码强度校验
 */
public class PasswordValidator {

	private static final Pattern PASSWORD_PATTERN = Pattern
			.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$");

	private static final String MESSAGE = "密码至少8位，且须包含大写字母、小写字母、数字和特殊字符";

	private PasswordValidator() {
	}

	public static boolean isValid(String password) {
		return StringUtils.isNotBlank(password) && PASSWORD_PATTERN.matcher(password).matches();
	}

	public static void validate(String password) {
		if (!isValid(password)) {
			throw new TpfhException(MESSAGE);
		}
	}
}
