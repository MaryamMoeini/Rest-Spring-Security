package com.powerhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.powerhouse.entity.MeterData;

public interface MeterRepository extends JpaRepository<MeterData, Long> {

}
