package com.project.service;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.powermock.reflect.internal.WhiteboxImpl;

import com.project.service.PrivateService;
import com.project.service.StaticService;
import com.project.util.Util;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

@RunWith(PowerMockRunner.class)
@PrepareForTest({StaticService.class,Logger.class})
public class Test_Mock_Static {
	
	StaticService staticServiceSpy = new StaticService();
	@Spy Logger __logger = Logger.getLogger("test");
	@Spy Logger __privateLogger = Logger.getLogger("test");
	@Spy Util __util = new Util();
	@Spy Util __privateUtil = new Util();
	
	@Captor
    private ArgumentCaptor<String> captorLoggingEvent;
	
	@Before
	public void init() throws IllegalArgumentException, IllegalAccessException {
		MockitoAnnotations.initMocks(this);
    }
	
	@SuppressWarnings("static-access")
	@Test
	public void get_ProtectedStaticVariable() throws Exception{
		assertEquals( "ProtectedStaticString++" ,  staticServiceSpy.ProtectedStaticString );
		//or
		assertEquals( "ProtectedStaticString++" ,  StaticService.ProtectedStaticString );
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void changeValueAndGet_ProtectedStaticVariable() throws Exception{
		staticServiceSpy.ProtectedStaticString = "change protected value";
		assertEquals( "change protected value" ,  staticServiceSpy.ProtectedStaticString );
		//or
		assertEquals( "change protected value" ,  StaticService.ProtectedStaticString );
		
		StaticService.ProtectedStaticString = "ProtectedStaticString++";
	}
	
	@Test
	public void get_PrivateStaticVariable() throws Exception{
		String result = (String) MemberModifier.field( StaticService.class , "PrivateStaticString").get( anyString() );
		assertEquals( "PrivateStaticString++" , result );
	}
	
	@Test
	public void changeValueAndGet_PrivateStaticVariable() throws Exception{
		MemberModifier.field( StaticService.class , "PrivateStaticString").set( anyString() , "change private value" );
		String result = (String) MemberModifier.field( StaticService.class , "PrivateStaticString").get( anyString() );
		assertEquals( "change private value" , result );
		
		MemberModifier.field( StaticService.class , "PrivateStaticString").set( anyString() , "PrivateStaticString++" );
	}
	
	@Test
	public void spyAndCall_ProtectedStaticVariable() throws Exception{
		
		Whitebox.setInternalState(StaticService.class, "util", __util);

		staticServiceSpy.callStaticUtil();
		
		Mockito.verify( __util , times(1) ).returnUtilString( );
		assertEquals( "UtilString" , __util.returnUtilString() );
	}
	
	@Test
	public void mockAndCall_ProtectedStaticVariable_CallMethodInVariable() throws Exception{
		
		Whitebox.setInternalState(StaticService.class, "util", __util);
		Mockito.when(__util.returnUtilString()).thenReturn("mock vairiable's method");

		staticServiceSpy.callStaticUtil();
		
		Mockito.verify( __util , times(1) ).returnUtilString( );
		assertEquals( "mock vairiable's method" , __util.returnUtilString() );
	}
	
	@Test
	public void spyCallAndCaptor_ProtectedStaticVariable_NonReturn() throws Exception{
		
		Whitebox.setInternalState(StaticService.class, "logger", __logger);
		staticServiceSpy.callLogger_StaticVariable();

		Mockito.verify( __logger , times(1) ).info(captorLoggingEvent.capture() );

		System.out.println("Print value from capturing. -> "+ captorLoggingEvent.getValue() );
		assertEquals( "print from logger" , captorLoggingEvent.getValue() );
	}
	
	@Test
	public void mockCallAndCaptor_ProtectedStaticVariable_NonReturn() throws Exception{
		// couldn't do this case.
	}
	
	@Test
	public void spyAndCall_PrivateStaticVariable() throws IllegalArgumentException, IllegalAccessException{
		//MemberModifier.field( StaticService.class , "privateUtil").set( Util.class ,  __privateUtil );
		Whitebox.setInternalState(StaticService.class, "privateUtil", __privateUtil);
		
		String result = staticServiceSpy.callPrivateStaticUtil();

		Mockito.verify( __privateUtil , times(1) ).returnUtilStringFromPrivateVariable();
		assertEquals( "UtilStringFromPrivate" , result );
	}
	
	@Test
	public void mockAndCall_PrivateStaticVariable(){
		Whitebox.setInternalState(StaticService.class, "privateUtil", __privateUtil);
		Mockito.when( __privateUtil.returnUtilStringFromPrivateVariable() ).thenReturn( "mock private variable" );

		String result = staticServiceSpy.callPrivateStaticUtil();
		
		Mockito.verify( __privateUtil , times(1) ).returnUtilStringFromPrivateVariable();
		assertEquals( "mock private variable" , result );
	}
	
	@Test
	public void callAndCaptor_PrivateStaticVariable_NonReturn(){
		Whitebox.setInternalState(StaticService.class, "privateLogger", __privateLogger);
		staticServiceSpy.callLogger_PrivateStaticVariable();

		Mockito.verify( __privateLogger , times(1) ).info(captorLoggingEvent.capture() );

		System.out.println("Print value from capturing. -> "+ captorLoggingEvent.getValue() );
		assertEquals( "print from private logger" , captorLoggingEvent.getValue() );
	}
	
	@Test
	public void mockCallAndCaptor_PrivateStaticVariable_NonReturn() throws Exception{
		// couldn't do this case.
	}
	
	@Test
	public void spyAndCall_PrivateStaticMethod() throws Exception {
		StaticService ssSpy = PowerMockito.spy( staticServiceSpy );
		String msg= WhiteboxImpl.invokeMethod(ssSpy, "staticPrivateMethod");

		verifyPrivate( ssSpy ,times(1) ).invoke("staticPrivateMethod");
		assertEquals("I'm static private method", msg);
	}
	
	@Test
	public void mockAndCall_PrivateStaticMethod() throws Exception {
		StaticService ssSpy = PowerMockito.spy( staticServiceSpy );
		PowerMockito.when(ssSpy, "staticPrivateMethod").thenReturn("mock private method");

		String msg= WhiteboxImpl.invokeMethod(ssSpy, "staticPrivateMethod");

		verifyPrivate( ssSpy ,times(1) ).invoke("staticPrivateMethod");
		assertEquals("mock private method", msg);
	}
	
	@Test
	public void spyAndCallAndCaptor_PrivateStaticMethod() throws Exception {
		StaticService ssSpy = PowerMockito.spy( staticServiceSpy );
		
		Whitebox.setInternalState(StaticService.class, "logger", __logger );
		WhiteboxImpl.invokeMethod(ssSpy, "staticPrivateMethod_NonReturn");
		Mockito.verify( __logger , times(1) ).info( captorLoggingEvent.capture() );
		
		verifyPrivate( ssSpy ,times(1) ).invoke("staticPrivateMethod_NonReturn");
		
		assertEquals("I'm static private method and non return.", captorLoggingEvent.getValue() );
	}
	
	@Test
	public void spyAndCallAndCaptor_PrivateStaticMethod_PrintOut() throws Exception {
		StaticService ssSpy = PowerMockito.spy( staticServiceSpy );
		final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(myOut));
		
		WhiteboxImpl.invokeMethod(ssSpy, "staticPrivateMethod_PrintOut_NonReturn");
		
		verifyPrivate( ssSpy ,times(1) ).invoke("staticPrivateMethod_PrintOut_NonReturn");
		
		assertEquals("I'm static private method and non return.", myOut.toString().trim() );
	}
	
	@Test
	public void mockCallAndCaptor_PrivateStaticMethod() throws Exception {
		// couldn't do this case.
	}
	
	
	@Test
	public void spyAndCall_PublicStaticMethod() {
		assertEquals("you called me" , StaticService.staticMethodReturnString() );
	}
	
	@Test
	public void mockAndCall_PublicStaticMethod() {
		PowerMockito.mockStatic(StaticService.class);
		Mockito.when(StaticService.staticMethodReturnString()).thenReturn("mock method");
		assertEquals("mock method" , StaticService.staticMethodReturnString() );
	}
	
	@Test
	public void spyAndCall_PublicStaticVariable() {
		Whitebox.setInternalState(StaticService.class, "util", __util);
		assertEquals( "UtilString" , __util.returnUtilString() );
	}
	
	@Test
	public void mockAndCall_PublicStaticVariable() throws Exception{
		
		Whitebox.setInternalState(StaticService.class, "util", __util);
		Mockito.when( __util.returnUtilString() ).thenReturn("mock static value from calling static method of variable");
		
		assertEquals( "mock static value from calling static method of variable" , __util.returnUtilString() );
	}
	
	
}
