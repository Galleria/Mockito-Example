package com.project.service;

import org.junit.Test;

public class Test_Exception_Expected {

	ExceptionService exceptionService;
	
	@Test (expected=Exception.class)
	public void shouldBeThrowException() throws Exception{
		exceptionService.exception();
	}
}
