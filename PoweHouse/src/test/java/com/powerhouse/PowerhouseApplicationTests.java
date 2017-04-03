package com.powerhouse;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	WebApplicationContext context;

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	ObjectMapper mapper;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	private static final String URL_PREFIX = "http://localhost:8080";
	private MockMvc moc;

	@Before
	public void setup() {
		this.moc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Ignore
	@Test
	public void createProfileRestServiceTest() throws Exception {

		Profile testProfile = new Profile();
		Consumption consump1 = new Consumption();
		List<Consumption> list = new ArrayList<Consumption>();
		testProfile.setMeterId("xyz001");
		testProfile.setProfileName("A");

		consump1.setFraction(0.1);
		consump1.setMonth("April");
		list.add(consump1);
		testProfile.setConsumption(list);

		System.out.println(mapper.writeValueAsString(testProfile));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URL_PREFIX + "/profile/create", mapper.writeValueAsString(testProfile))
				.param(mapper.writeValueAsString(testProfile));

		moc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(content().json(testProfile.toString(), true)).andReturn();
	}

	@Ignore
	@Test
	public void deleteProfileRestServiceTest() throws Exception {
		Profile testProfile = new Profile();
		Consumption consump1 = new Consumption();
		List<Consumption> list = new ArrayList<Consumption>();
		testProfile.setMeterId("xyz001");
		testProfile.setProfileName("A");

		consump1.setFraction(0.1);
		consump1.setMonth("April");
		list.add(consump1);
		testProfile.setConsumption(list);
		mapper.writeValueAsString(testProfile);

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete(URL_PREFIX + "/profile/create", mapper.writeValueAsString(testProfile))
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testProfile)); 
		moc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
	}

}
