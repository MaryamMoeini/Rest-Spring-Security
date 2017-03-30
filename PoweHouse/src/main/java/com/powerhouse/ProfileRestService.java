package com.powerhouse;

import java.net.URI;
import java.util.Collection;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.powerhouse.entity.Profile;
import com.powerhouse.repository.ProfileRepository;

@RestController
@RequestMapping("/profile")
public class ProfileRestService extends Exception {
	
	@Autowired
	ProfileRepository profileRepository; 
	
	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Profile input) throws Exception{
		
		//TODO: validate profile
		Profile profile = profileRepository.save(input);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(profile.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	
	
}
