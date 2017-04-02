package com.powerhouse;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.powerhouse.entity.Consumption;
import com.powerhouse.entity.Profile;
import com.powerhouse.repository.ConsumptionRepository;
import com.powerhouse.repository.ProfileRepository;
import com.powerhouse.repository.ValidationEngin;

@RestController
@RequestMapping("/profile")
public class ProfileRestService extends ExceptionHandler {
	
	@Autowired
	ProfileRepository profileRepository; 
	
	@Autowired
	ValidationEngin validationEngin;
	
	@Autowired
	ConsumptionRepository consumptionRepository;
	
	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	public ResponseEntity<Profile> create(@RequestBody Profile input) throws Exception{
		
		validationEngin.validateProfile(input);
		Profile profile = profileRepository.save(input);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/profile")
				.buildAndExpand(profile.getId()).toUri();
		
		return ResponseEntity.ok(profile);
	}

	@RequestMapping(value="/delete" , method = RequestMethod.DELETE)
	public ResponseEntity<Collection<Profile>> deleteFraction(@RequestBody Profile input) throws Exception{
		String meterId= input.getMeterId();
		Profile search = profileRepository.findByMeterId(meterId);
	
		for(Consumption c : input.getConsumption()){
			for(Consumption existing : search.getConsumption())
				if(c.getMonth().equals(existing.getMonth())){
					consumptionRepository.deleteById(existing.getId());
				}else{
					System.out.println("this record dont exist" + c.toString());
				}
		}
			
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/update" , method = RequestMethod.PUT)
	public ResponseEntity<Profile> updateProfile(@RequestBody Profile profile) throws Exception{
		//assuming that in every update we get 12 ratios for 12 month
		Profile existingProfile = profileRepository.findByMeterId(profile.getMeterId());
		
		for(Consumption remove: existingProfile.getConsumption()){
			consumptionRepository.deleteById(remove.getId());
		}
		profileRepository.deleteById(existingProfile.getId());
		profileRepository.save(profile);

		Profile updatedProfile = profileRepository.findByMeterId(profile.getMeterId());
		return ResponseEntity.ok().body(updatedProfile);
	}
	
	@RequestMapping(value = "/getdata/{meterid}" , method = RequestMethod.GET)
	public ResponseEntity<Collection<Profile>> getProfileData(@PathVariable ("meterid") String meterId){
		Collection<Profile> profileData = profileRepository.getByMeterId(meterId);
		return ResponseEntity.ok(profileData);
		
	}

}
