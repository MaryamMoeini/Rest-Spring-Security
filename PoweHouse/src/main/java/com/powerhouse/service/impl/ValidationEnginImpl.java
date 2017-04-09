package com.powerhouse.service.impl;

import java.text.DateFormatSymbols;
import java.util.Collection;

import org.apache.log4j.Logger;
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

	final static Logger logger = Logger.getLogger(ValidationEnginImpl.class);

	
	@Override
	public boolean validateMeterData(MeterData input) {
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		
		//String lastTwoMonth=months[input.getRecordDate().getMonth() - 2];
		
		// validate the existence of the profile
		Profile seachProfile = profileRepository.getByMeterId(input.getMeterId());
		if(seachProfile == null){
			logger.info("This profile does not exist! please check the Meter ID");
			return false;
		}

		
		// check if current meter reading is more than last month, If it's the record of January, It will skip this validation
		if(input.getRecordDate().getMonth() != 0){
			String lastMonth = months[input.getRecordDate().getMonth() - 1];
			MeterData lastMonthRecord = meterRepository.findBymonthAndmeterId(lastMonth, input.getMeterId());
			if (lastMonthRecord != null) {
				if (lastMonthRecord.getMeterReading() > input.getMeterReading()) {
					logger.info("Meter reading of this month can not be less than last month! Invalid data");
					return false;
				}
			}
		}
		
		
		//MeterData lasttwoMonthRecord = meterRepository.findBymonthAndmeterId(lastTwoMonth, input.getMeterId());
		
		
		
		
		//validate the consumption ratio 
//		if (lastMonthRecord != null && lasttwoMonthRecord!= null) {
//		  double lastMonthConsumption = lastMonthRecord.getMeterReading() - lasttwoMonthRecord.getMeterReading();
//		  double consumption = input.getMeterReading() -  lastMonthRecord.getMeterReading();
//		  double tolerance = lastMonthConsumption * 0.25; 
//		  double maxAcceptedreading =  lastMonthConsumption  + tolerance;
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
