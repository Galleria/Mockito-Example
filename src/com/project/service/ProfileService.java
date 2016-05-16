package com.project.service;

import com.project.repositories.ProfilePersistence;

public class ProfileService {
	
	private ProfilePersistence pp;
	
	public ProfileService() {
		super();
		this.pp = new ProfilePersistence();
	}



	public ProfileService(ProfilePersistence pp) {
		super();
		this.pp = pp;
	}



	public boolean createNewProfileForUser(){
		return pp.insertProfile();
	}
}
