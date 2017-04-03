package com.powerhouse.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"month" , "reference" })
	)
@Entity
public class Consumption {
	
	@Id
	@GeneratedValue
	private long id;
	private double fraction;
	private String month;
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name ="reference")
	private Profile profile;
	
	public Consumption(double fraction, String month) {
		this.fraction = fraction;
		this.month = month;
	}
	
	public Consumption(){
		
	}

	public long getId() {
		return id;
	}

	public Consumption(double fraction, String month, Profile profile) {
		super();
		this.fraction = fraction;
		this.month = month;
		this.profile = profile;
	}

	@JsonIgnore
	public Profile getProfile() {
		return profile;
	}


	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public double getFraction() {
		return fraction;
	}
	public void setFraction(double fraction) {
		this.fraction = fraction;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}




	@Override
	public String toString() {
		return "Consumption [id=" + id + ", fraction=" + fraction + ", month=" + month + "]";
	}
	
	
}
