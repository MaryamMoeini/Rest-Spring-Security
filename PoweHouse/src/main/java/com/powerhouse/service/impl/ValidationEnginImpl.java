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
	public void validateMeterData(MeterData input) {
		String lastMonth;
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		lastMonth = months[input.getRecordDate().getMonth() - 1];

		// check if current meter reading is more than last month
		MeterData lastMonthRecord = meterRepository.findBymonthAndmeterId(lastMonth, input.getMeterId());
		if (lastMonthRecord != null) {
			if (lastMonthRecord.getMeterReading() > input.getMeterReading()) {
				throw new Error("Meter reading of this month can not be less than last month! Invalid data");
			}
		}
		
		// validate the existance of the profile
		Collection<Profile> seachProfile = profileRepository.getByMeterId(input.getMeterId());
		if(seachProfile.size() == 0){
			throw new Error("This profile does not exist! please check the Meter ID");
		}
		
		//validate the consumption ratio
		if (lastMonthRecord != null) {
		  double consumption = lastMonthRecord.getMeterReading() - input.getMeterReading();
		  //double tolerance = consumption %25; 
		 // double acceptedreading = 
		}
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
