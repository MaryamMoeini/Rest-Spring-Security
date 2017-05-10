package com.RestProvider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.RestProvider.entity.Consumption;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {
	@Modifying
	@Transactional
	@Query("delete from Consumption where id=?1 ")
	void deleteById(long id);

}
