package com.springau.sellerportal.exceptions;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleExceptions {
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void handle(HttpServletResponse response) throws IOException {
		System.out.println("handling");
		response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Send prorper data");
		
	}
}
