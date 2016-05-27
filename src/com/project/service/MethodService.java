package com.project.service;

public class MethodService {

	public String publicMethod(){
		return "Public :: No argument";
	}
	
	public String publicMethod(String str){
		return "Public :: One argument is "+str;
	}
	
	private String privateMethod(){
		return "Private :: No argument";
	}
	
	private String privateMethod(String str){
		return "Private :: One argument is "+str;
	}
}
