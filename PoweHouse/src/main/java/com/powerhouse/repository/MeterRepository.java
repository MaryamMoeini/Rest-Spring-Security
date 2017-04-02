package com.powerhouse.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.powerhouse.entity.MeterData;

public interface MeterRepository extends JpaRepository<MeterData, Long> {

	@Modifying
	@Transactional
	@Query("update MeterData m set m.meterReading = ?3 where m.meterId = ?1 and m.month = ?2")
	void updateByMeterIdAndMonth(String meterId, String month, double meterReading);

	@Modifying
	@Transactional
	@Query("delete from MeterData m where m.meterId = ?1 and m.month = ?2")
	public void removeByMeterIdAndMonth(String meterId, String month);

	Collection<MeterData> getBymeterId(String id);
	
	@Query("select m from MeterData m where m.meterId= ?1 and m.recordDate between ?2 and ?3")
	Collection<MeterData> getRecords(String meterId, Date from, Date to);

	@Query("select m from MeterData m where m.month = ?1 and m.meterId = ?2")
	MeterData findBymonthAndmeterId(String lastMonth, String meterId);

}
