package com.RestProvider.repository;

import org.springframework.stereotype.Component;

import com.RestProvider.entity.MeterData;
import com.RestProvider.entity.Profile;

@Component
public interface ValidationEngin {

	boolean validateMeterData(MeterData input);

	void validateProfile(Profile input);

}
