package com.test.mockito;

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

import com.project.repositories.HistoryPersistence;
import com.project.repositories.ProfilePersistence;
import com.project.repositories.UserPersistence;
import com.project.service.ProfileService;
import com.project.service.UserService;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;

@SuppressWarnings("unused")
//@RunWith(MockitoJUnitRunner.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class Mockito_Annotation {
	 
	@InjectMocks UserService us;
	@InjectMocks ProfileService ps;
	
	@Mock UserPersistence up;
	@Mock HistoryPersistence hp;
	
	@Mock ProfilePersistence pp;
	
	@Before 
	public void init(){
		ps = new ProfileService( pp );
		us = new UserService( up , hp , ps );
		MockitoAnnotations.initMocks(this); 
	}

	
	@Test
	public void mockProfilePersistence_ReturnTrue(){
		doReturn( true ).when(pp).insertProfile();
		Assert.assertTrue( ps.createNewProfileForUser() );
	}
	
	@Test
	public void mockProfilePersistence_ReturnFalse(){
		doReturn( false ).when(pp).insertProfile();
		Assert.assertFalse( ps.createNewProfileForUser() );
	}
	
	@Test
	public void mockInsertUser_ReturnTrue(){
		doReturn( new Object() ).when(up).findById();
		doReturn( true ).when(hp).insert();
		
		Assert.assertTrue( us.insertUser() );
	}
	
	@Test
	public void mockInsertUser_ReturnFalse(){
		doReturn( new Object() ).when(up).findById();
		doReturn( false ).when(hp).insert();

		Assert.assertFalse( us.insertUser() );
	}
	
	@Test
	public void CallPersistenceFromOtherService_ShouldBeTrue(){
		
		doReturn( new Object() ).when(up).findById();
		doReturn( false ).when(hp).insert();
	
		doReturn( true ).when(pp).insertProfile();

		Assert.assertTrue( us.createUser() );
	}
	
	@Test
	public void CallPersistenceFromOtherService_ShouldBeFalse(){
		
		doReturn( new Object() ).when(up).findById();
		doReturn( false ).when(hp).insert();
		
		doReturn( false ).when(pp).insertProfile();

		Assert.assertFalse( us.createUser() );
	}
	
	@Test
	public void CallPrivateMethodByPowerMock() throws Exception{
		UserService classUnderTest = PowerMockito.spy(us);
		classUnderTest.callPrivateMethod();
		PowerMockito.verifyPrivate( classUnderTest , times(1)).invoke("imPrivate");
	}
}
