package com.project.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberMatcher;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.internal.WhiteboxImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MethodService.class)
public class Test_Mock_Method {

	@Spy MethodService methodService = new MethodService();
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks( this );
	}
	
	@Test
	public void PublicNoArgument(){
		String defaultStr = "Public :: No argument";
		String str = "mock public method no argument";
		
		Assert.assertEquals( defaultStr , methodService.publicMethod() );
		Mockito.doReturn( str ).when( methodService ).publicMethod();
		Assert.assertEquals( str , methodService.publicMethod() );
	}
	
	@Test
	public void PublicWithArgument(){
		String defaultStr = "Public :: One argument is ";
		String str = "mock public method one argument";
		
		Assert.assertEquals( defaultStr , methodService.publicMethod( Mockito.anyString() ) );
		Mockito.doReturn( str ).when( methodService ).publicMethod( Mockito.anyString() );
		Assert.assertEquals( str , methodService.publicMethod( Mockito.anyString() ) );
	}
	
	@Test
	public void PrivateNoArgument() throws Exception{
		String defaultStr = "Private :: No argument";
		String str = "mock private method no argument";
		MethodService methodServicePowerMock = PowerMockito.spy( methodService );
		
		String msg = WhiteboxImpl.invokeMethod(methodServicePowerMock, "privateMethod");
		Assert.assertEquals( defaultStr , msg );

		PowerMockito.doReturn(str).when( methodServicePowerMock , "privateMethod");
		msg = WhiteboxImpl.invokeMethod(methodServicePowerMock, "privateMethod");
		Assert.assertEquals( str , msg );
	}
	
	@Test
	public void PrivateNoArgument2() throws Exception{
		String defaultStr = "Private :: No argument";
		String str = "mock private method no argument";
		MethodService methodServicePowerMock = PowerMockito.spy( methodService );
		
		String msg = WhiteboxImpl.invokeMethod(methodServicePowerMock, "privateMethod");
		Assert.assertEquals( defaultStr , msg );

		MemberModifier.stub(MemberMatcher.method(MethodService.class,"privateMethod")).toReturn( str );
		msg = WhiteboxImpl.invokeMethod(methodServicePowerMock, "privateMethod");
		Assert.assertEquals( str , msg );
	}
	
	@Test
	public void PrivateWithArgument() throws Exception{
		String defaultStr = "Private :: One argument is ";
		String str = "mock private method with argument";
		MethodService methodServicePowerMock = PowerMockito.spy( methodService );
		
		String msg = WhiteboxImpl.invokeMethod(methodServicePowerMock, "privateMethod" , Mockito.anyString() );
		Assert.assertEquals( defaultStr , msg );

		PowerMockito.doReturn(str).when( methodServicePowerMock , "privateMethod" , Mockito.anyString());
		msg = WhiteboxImpl.invokeMethod(methodServicePowerMock, "privateMethod" , Mockito.anyString());
		Assert.assertEquals( str , msg );
	}
	
	@Test
	public void PrivateWithArgument2() throws Exception{
		String defaultStr = "Private :: One argument is ";
		String str = "mock private method with argument";
		MethodService methodServicePowerMock = PowerMockito.spy( methodService );
		
		String msg = WhiteboxImpl.invokeMethod(methodServicePowerMock, "privateMethod" , Mockito.anyString() );
		Assert.assertEquals( defaultStr , msg );

		MemberModifier.stub(MemberMatcher.method(MethodService.class,"privateMethod",String.class)).toReturn( str );
		msg = WhiteboxImpl.invokeMethod(methodServicePowerMock, "privateMethod" , Mockito.anyString());
		Assert.assertEquals( str , msg );
	}
}
