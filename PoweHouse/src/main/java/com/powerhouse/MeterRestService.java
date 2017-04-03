package com.powerhouse;

import java.net.URI;
import java.text.DateFormatSymbols;
import java.time.Month;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.powerhouse.entity.MeterData;
import com.powerhouse.entity.MeterDataSearch;
import com.powerhouse.repository.MeterRepository;
import com.powerhouse.repository.ValidationEngin;

@RestController
@RequestMapping("/meter")
public class MeterRestService extends ExceptionHandlerController {

	@Autowired
	MeterRepository meterRepository;
	
	@Autowired
	ValidationEngin validationEngin;
	
	@RequestMapping(value="/record" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MeterData> recordMeterData(@RequestBody MeterData input)throws Exception{
		
		setMonthName(input);
		validationEngin.validateMeterData(input);
		MeterData inputData = meterRepository.save(input);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/meter")
				.buildAndExpand(inputData.getId()).toUri();
		return ResponseEntity.created(location).body(inputData);
	}
	
	@RequestMapping(value="/remove" , method = RequestMethod.DELETE)
	public ResponseEntity<Collection<MeterData>> removeMeterData(@RequestBody Collection<MeterData> input) throws Exception{
		
		for(MeterData m: input){
		meterRepository.removeByMeterIdAndMonth(m.getMeterId(), m.getMonth());
		}
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/update" , method = RequestMethod.PUT)
	public ResponseEntity<Void> updateData(@RequestBody MeterData input) throws Exception{
		
		meterRepository.updateByMeterIdAndMonth(input.getMeterId() , input.getMonth() , input.getMeterReading());
		
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value="/getmeterdata/{meterid}" , method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<MeterData>> getDataByMeterId(@PathVariable("meterid") String id) throws Exception{
		Collection<MeterData> meterData = meterRepository.getBymeterId(id);
		
		return ResponseEntity.ok(meterData);
	}
	
	@RequestMapping(value = "/getrecords" , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<MeterData>> getConsumption(@RequestBody MeterDataSearch input) throws Exception{
		
		Collection<MeterData> response = meterRepository.getRecords(input.getMeterId() , input.getStartDate() , input.getEndDate());
		
		return ResponseEntity.ok(response);
	}
	
	private MeterData setMonthName(MeterData data){
		String month;
		DateFormatSymbols dfs = new DateFormatSymbols();
		String [] months = dfs.getMonths();
		
		month =  months[data.getRecordDate().getMonth()];
		data.setMonth(month);
		return data;
	}
	
}
