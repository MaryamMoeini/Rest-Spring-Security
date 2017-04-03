package com.powerhouse.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.powerhouse.entity.Consumption;
import com.powerhouse.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	@Query("select p from Profile p where meterId = ?1")
	public Profile getByMeterId(String meterId);

	public Profile findByMeterId(String meterId);

	@Modifying
	@Transactional
	@Query("delete from Profile where id= ?1")
	public void deleteById(long id);

}
