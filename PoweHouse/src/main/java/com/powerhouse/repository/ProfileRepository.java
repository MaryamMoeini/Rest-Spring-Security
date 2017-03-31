package com.powerhouse.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.powerhouse.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	void deleteBymeterIdAndMonth(String meterId, String month);

	Profile updateFraction(String meterId, double fraction);

	Collection<Profile> getById(String meterId);

}
