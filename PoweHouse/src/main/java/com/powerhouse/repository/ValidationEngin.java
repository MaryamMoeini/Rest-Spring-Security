package com.powerhouse.repository;

import org.springframework.stereotype.Component;

import com.powerhouse.entity.MeterData;
import com.powerhouse.entity.Profile;

@Component
public interface ValidationEngin {

	void validateMeterData(MeterData input);

	void validateProfile(Profile input);

}
