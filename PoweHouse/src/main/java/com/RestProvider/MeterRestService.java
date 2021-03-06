package com.RestProvider;

import java.net.URI;
import java.text.DateFormatSymbols;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

import com.RestProvider.entity.MeterData;
import com.RestProvider.entity.MeterDataSearch;
import com.RestProvider.repository.MeterRepository;
import com.RestProvider.repository.ValidationEngin;

@RestController
@RequestMapping("/meter")
public class MeterRestService{

	@Autowired
	MeterRepository meterRepository;

	@Autowired
	ValidationEngin validationEngin;

	@RequestMapping(value = "/record", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<MeterData>> recordMeterData(@RequestBody Collection<MeterData> input)
			throws Exception {
		Collection<MeterData> recordedData = new ArrayList<>();
		for (MeterData data : input) {
			setMonthName(data);
			try {
				if(validationEngin.validateMeterData(data)){
					meterRepository.save(data);
					recordedData.add(data);
				}
				
			} catch (Exception e) {
				continue;
			}

		}

		return ResponseEntity.ok().body(recordedData);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> removeMeterData(@RequestBody Collection<MeterData> input)
			throws Exception {

		for (MeterData m : input) {
			meterRepository.removeByMeterIdAndMonth(m.getMeterId(), m.getMonth());
		}
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateData(@RequestBody MeterData input) throws Exception {

		meterRepository.updateByMeterIdAndMonth(input.getMeterId(), input.getMonth(), input.getMeterReading());

		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/getmeterdata/{meterid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<MeterData>> getDataByMeterId(@PathVariable("meterid") String id) throws Exception {
		Collection<MeterData> meterData = meterRepository.getBymeterId(id);

		return ResponseEntity.ok(meterData);
	}

	@RequestMapping(value = "/getrecords", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<MeterData>> getConsumption(@RequestBody MeterDataSearch input) throws Exception {

		Collection<MeterData> response = meterRepository.getRecords(input.getMeterId(), input.getStartDate(),
				input.getEndDate());

		return ResponseEntity.ok(response);
	}

	private MeterData setMonthName(MeterData data) {
		String month;
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();

		month = months[data.getRecordDate().getMonth()];
		data.setMonth(month);
		return data;
	}

}
