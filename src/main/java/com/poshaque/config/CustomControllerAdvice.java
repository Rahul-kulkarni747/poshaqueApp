package com.poshaque.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.poshaque.model.Payload;

@ControllerAdvice
public class CustomControllerAdvice implements ResponseBodyAdvice<Object>  {

	@ExceptionHandler(value = Exception.class)
    @ResponseBody
	public Payload handleException(Exception e) {
		Payload payload = new Payload();
		payload.setErrorMessage((e.getMessage() == null) ? "Resource not found." : e.getMessage());
		payload.setHasError(true);
		return payload;
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return !returnType.toString().contains("handleException");
	}

	@Override
	public Payload beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		Payload payload = new Payload();
		payload.setBody(body);
		return payload;
	}
}
