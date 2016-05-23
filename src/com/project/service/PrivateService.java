package com.project.service;

import java.util.stream.Collectors;

import com.project.model.Person;

public class PrivateService {

	private String pvStr;
	public String puStr;
	
	private String IMPrivate(){
		return this.pvStr;
	}
	
	private String toggleCaseString(String str){
		return str.chars().mapToObj
				(c -> {
			        if (Character.isUpperCase(c)) {
			            c = Character.toLowerCase(c);
			        } else if (Character.isLowerCase(c)) {
			            c = Character.toUpperCase(c);
			        }
			        return String.valueOf((char)c);
			    }).collect(Collectors.joining());
	}
	
	public String convertStringToNumber(String str){
		String result = "";
		result = str.chars().mapToObj( c-> {
			int ascii = (int) c;
			//result.concat( String.valueOf(ascii) );
			return String.valueOf(ascii);
		}).collect( Collectors.joining() );
		return result;
	}
	
	
	public String callPrivateMethod(){
		System.out.println("callPrivateMethod");
		return toggleCaseString("test");
	}
	
	public String convertPvStr(){
		return toggleCaseString(this.pvStr);
	}

	public String getPvStr() {
		return pvStr;
	}
	
	public String newStringInMethod(){
		System.out.println("newStringInMethod");
		Person person = new Person();
		return String.valueOf( person.getAge() );
	}
}
