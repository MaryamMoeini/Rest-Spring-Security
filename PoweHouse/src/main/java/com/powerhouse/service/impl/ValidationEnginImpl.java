package com.powerhouse.service.impl;

import java.text.DateFormatSymbols;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.powerhouse.entity.Consumption;
import com.powerhouse.entity.MeterData;
import com.powerhouse.entity.Profile;
import com.powerhouse.repository.MeterRepository;
import com.powerhouse.repository.ProfileRepository;
import com.powerhouse.repository.ValidationEngin;

@Service
public class ValidationEnginImpl implements ValidationEngin {

	@Autowired
	MeterRepository meterRepository;
	
	@Autowired
	ProfileRepository profileRepository;

	@Override
	public boolean validateMeterData(MeterData input) {
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		String lastMonth = months[input.getRecordDate().getMonth() - 1];
		//String lastTwoMonth=months[input.getRecordDate().getMonth() - 2];
		
		
		// check if current meter reading is more than last month
		MeterData lastMonthRecord = meterRepository.findBymonthAndmeterId(lastMonth, input.getMeterId());
		//MeterData lasttwoMonthRecord = meterRepository.findBymonthAndmeterId(lastTwoMonth, input.getMeterId());
		if (lastMonthRecord != null) {
			if (lastMonthRecord.getMeterReading() > input.getMeterReading()) {
				throw new Error("Meter reading of this month can not be less than last month! Invalid data");
				
			}
		}
		
		// validate the existance of the profile
		Profile seachProfile = profileRepository.getByMeterId(input.getMeterId());
		if(seachProfile == null){
			throw new Error("This profile does not exist! please check the Meter ID");
		}
		
		//validate the consumption ratio
//		if (lastMonthRecord != null && lasttwoMonthRecord!= null) {
//		  double lastMonthConsumption = lastMonthRecord.getMeterReading() - lasttwoMonthRecord.getMeterReading();
//		  double consumption = input.getMeterReading() -  lastMonthRecord.getMeterReading();
//		  double tolerance = lastMonthConsumption * 0.25; 
//		  double maxAcceptedreading =  lastMonthConsumption  + tolerance;
//		  System.err.println("tolerance" +tolerance);
//		  System.err.println("maxAcceptedreading" +maxAcceptedreading);
//		  System.err.println("consumption" +consumption);
//		  if(!(consumption <= maxAcceptedreading)){
//			  throw new Error("This consumption is exceeding the %25 tolerance");
//		  }
//		}
	
	return true;
	}

	@Override
	public void validateProfile(Profile input) {
		// check if the total fractions is equal to one
		double totalFraction = 0.0;
		for (Consumption c : input.getConsumption()) {
			totalFraction += c.getFraction();
		}

		if (totalFraction > 1) {
			throw new Error("sum of fractions MUST be less than or equal to one");
		}

	}

}
