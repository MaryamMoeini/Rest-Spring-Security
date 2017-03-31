package com.powerhouse;

import java.net.URI;
import java.util.Collection;

import org.apache.coyote.Response;
import org.hibernate.engine.spi.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<Profile> create(@RequestBody Profile input) throws Exception{
		
		//TODO: validate profile
		Profile profile = profileRepository.save(input);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/profile")
				.buildAndExpand(profile.getId()).toUri();
		
		return ResponseEntity.created(location).body(profile);
	}

	@RequestMapping(value="/delete" , method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteFraction(@RequestBody Profile input) throws Exception{
		
		profileRepository.deleteBymeterIdAndMonth(input.getMeterId(),input.getMonth());
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/update" , method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProfile(@RequestBody Profile profile) throws Exception{
		profileRepository.updateFraction(profile.getMeterId() , profile.getFraction());
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/getdata/{meterid}" , method = RequestMethod.GET)
	public ResponseEntity<Collection<Profile>> getProfileData(@PathVariable ("meterid") String meterId){
		Collection<Profile> profileData = profileRepository.getById(meterId);
		return ResponseEntity.ok(profileData);
	}

}
