package com.project.service;

import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberMatcher;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.internal.WhiteboxImpl;

import com.project.model.Person;
import com.project.service.PrivateService;
import com.project.service.UserService;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.times;


import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.*;

@SuppressWarnings("unused")
@RunWith(PowerMockRunner.class)
@PrepareForTest(PrivateService.class)
public class Test_Mock_Private {

	@InjectMocks PrivateService privateService;
	
	String helloPrivate = "helloPrivateVariable";
	String resultFromToggleCaseString = "HELLOpRIVATEvARIABLE";

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this); 
	}

	
	@Test
	public void mockPrivateVariable() throws Exception {
		MemberModifier.field( PrivateService.class , "pvStr").set( privateService , helloPrivate );
		Assert.assertEquals( helloPrivate , privateService.getPvStr() );
	}
	
	
	@Test
	public void verifyCallingPrivateMethodFromPublicMethod() throws Exception {
		PrivateService psSpy = PowerMockito.spy( privateService );
		psSpy.callPrivateMethod();
		PowerMockito.verifyPrivate(psSpy ,times(1) ).invoke("toggleCaseString" , anyString());
	}
	
	
	@Test
	public void resultFromPrivateMethod() throws Exception {
		String msg= WhiteboxImpl.invokeMethod(privateService, "toggleCaseString",helloPrivate);
	    Assert.assertEquals( resultFromToggleCaseString ,msg );
	}
	

	@Test
	public void verifyCallingPrivateMethod() throws Exception {	
		PrivateService psSpy = PowerMockito.spy( privateService );
		String msg= WhiteboxImpl.invokeMethod(psSpy, "toggleCaseString",helloPrivate);
		verifyPrivate( psSpy ,times(1) ).invoke("toggleCaseString", anyString());
	}
	
	
	@Test
	public void callPrivateMethodFromMock() throws Exception {
		PrivateService psSpy = PowerMockito.spy( privateService );
		
		String msg= WhiteboxImpl.invokeMethod(psSpy, "toggleCaseString",helloPrivate);
		
		Assert.assertEquals(resultFromToggleCaseString, msg );
		verifyPrivate( psSpy ,times(1) ).invoke("toggleCaseString", anyString());
	}
	
	@Test
	public void injectSpyToClass() throws Exception{
		PrivateService psSpy = PowerMockito.spy( new PrivateService() );
		
		Person person = new Person("Supachai","Male",23);
		
		PowerMockito.whenNew(Person.class).withNoArguments().thenReturn( person );

		Assert.assertEquals( "23" , psSpy.newStringInMethod() );
	}
	
}
