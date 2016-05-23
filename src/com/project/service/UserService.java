package com.project.service;

import com.project.repositories.HistoryPersistence;
import com.project.repositories.UserPersistence;

public class UserService {
	private UserPersistence up ;//= new UserPersistence();
	private HistoryPersistence hs;// = new HistoryPersistence();
	private ProfileService pp;
	
	private String DetailStr;
	
	public UserService(){
		up = new UserPersistence();
		hs = new HistoryPersistence();
		pp = new ProfileService();
	}
	
	public UserService(UserPersistence up, HistoryPersistence hs, ProfileService pp) {
		super();
		this.up = up;
		this.hs = hs;
		this.pp = pp;
	}

	public boolean insertUser(){
		up.insert( new Object() );
		return hs.insert();
	}
	
	public boolean createUser(){

		up.insert( new Object() );
		hs.insert();
		
		return pp.createNewProfileForUser();
	}
	
	public void callPrivateMethod(){
		imPrivate();
		imPrivate();
	}
	
	private void imPrivate(){
		System.out.println("I'm Private");
	}
	
	public String callPrivateVariable(){
		System.out.println( DetailStr );
		return DetailStr;
	}
}
