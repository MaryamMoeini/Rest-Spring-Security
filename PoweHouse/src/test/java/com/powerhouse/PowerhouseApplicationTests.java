package com.powerhouse;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.powerhouse.entity.Consumption;
import com.powerhouse.entity.Profile;
import com.powerhouse.repository.ProfileRepository;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
@RunWith(SpringRunner.class)
@SpringBootTest
public class PowerhouseApplicationTests {

	@Autowired 
	ProfileRepository profileRepository;
	
	 private MockMvc moc;
	
	@Test
	public void contextLoads() throws Exception {
		
		Profile testProfile = new Profile();
		Consumption consump1 = new Consumption();
		List<Consumption> lst = new ArrayList<Consumption>();
		testProfile.setMeterId("xyz001");
		testProfile.setProfileName("A");
		// and so on for another 11 records 
		consump1.setFraction(0.1);
		consump1.setMonth("April");
		consump1.setProfile(testProfile);
		lst.add(consump1);
		testProfile.setConsumption(lst);
		
		
	//	when(profileRepository.save(testProfile)).thenReturn();
		
		moc.perform(get("/profile/create"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(1)));
		
	}

}
