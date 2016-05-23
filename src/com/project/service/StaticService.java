package com.project.service;

import java.util.logging.Logger;

import com.project.util.Util;

public class StaticService {
	protected static Logger logger;
	protected static Util util ;
	private static Logger privateLogger;
	private static Util privateUtil ;
	
	protected static String ProtectedStaticString = "ProtectedStaticString++";
	private static String PrivateStaticString = "PrivateStaticString++";
	
	public void callLogger_StaticVariable(){
		logger.info("print from logger");
	}
	
	public void callLogger_PrivateStaticVariable(){
		privateLogger.info("print from private logger");
	}
	
	public static void staticMethod(){
		System.out.println("Call me in static method.");
	}
	
	public static String staticMethodReturnString(){
		System.out.println("Call me in static method.");
		return "you called me";
	}
	
	public String callStaticUtil(){
		return util.returnUtilString();
	}
	
	public String callPrivateStaticUtil(){
		return privateUtil.returnUtilStringFromPrivateVariable();
	}
	
	private String staticPrivateMethod(){
		return "I'm static private method";
	}
	
	private void staticPrivateMethod_NonReturn(){
		logger.info( "I'm static private method and non return." );
		System.out.println( "I'm static private method and non return.");
	}
	
	private void staticPrivateMethod_PrintOut_NonReturn(){
		System.out.println( "I'm static private method and non return.");
	}
	
	public String callStaticPrivateMethod(){
		return staticPrivateMethod();
	}
	
}
