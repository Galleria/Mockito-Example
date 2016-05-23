package com.project.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.project.model.Person;
import com.project.service.PrivateService;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.*;

@SuppressWarnings("unused")
@RunWith(PowerMockRunner.class)
@PrepareForTest(PrivateService.class)
public class Test_Mock_LocalObject_WithAnnotation {
	
	@InjectMocks PrivateService privateService;
	
	@Mock Person person;
	
	@Before
	public void init() throws Exception{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void injectSpyToClass() throws Exception{	
		Person person = new Person("Supachai","Male",23);
		PowerMockito.whenNew(Person.class).withNoArguments().thenReturn(person);
		Assert.assertEquals("23", privateService.newStringInMethod() );
	}
	
	
}
