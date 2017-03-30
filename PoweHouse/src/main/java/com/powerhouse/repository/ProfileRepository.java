package com.powerhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.powerhouse.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
