package com.sh.carexx.common.web.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sh.carexx.common.CarexxConstant;
import com.sh.carexx.common.ErrorCode;
import com.sh.carexx.common.limit.exception.RequestLimitException;
import com.sh.carexx.common.web.BasicRetVal;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = { RequestLimitException.class })
	public BasicRetVal requestLimitException(RequestLimitException e) {
		return new BasicRetVal(CarexxConstant.RetCode.BAD_REQUEST, ErrorCode.ACCESS_FREQUENCY);
	}
}
