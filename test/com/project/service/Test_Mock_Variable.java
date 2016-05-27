package com.project.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;


@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(VariableService.class)
public class Test_Mock_Variable {

	@InjectMocks VariableService variableService;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks( this );
	}
	
	@Test
	public void publicShouldBeChange(){
		String str = "public vairable has been changed";
		variableService.publicVariable = str;
		Assert.assertEquals( str , variableService.publicVariable);
	}
	
	@Test
	public void privateShouldBeChange() throws IllegalArgumentException, IllegalAccessException{
		String str = "private vairable has been changed";
		MemberModifier.field( VariableService.class , "privateVariable").set(variableService , str);
		Assert.assertEquals( str , variableService.privateVariable);
		System.out.println( MemberModifier.field( VariableService.class , "privateVariable").get(variableService) ); 
	}
}
