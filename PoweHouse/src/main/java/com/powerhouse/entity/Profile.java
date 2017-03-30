package com.powerhouse.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"profileName", "month"}),
	    name = "profile"
	)

@Entity
public class Profile implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	private String profileName;
	private String month;
	private double fraction;
	
	
	public Profile(String profileName, String month, double fraction) {
		super();
		this.profileName = profileName;
		this.month = month;
		this.fraction = fraction;
	}

	
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}
	
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public double getFraction() {
		return fraction;
	}
	public void setFraction(double fraction) {
		this.fraction = fraction;
	}


	@Override
	public String toString() {
		return "Profile [id=" + id + ", profileName=" + profileName + ", month=" + month + ", fraction=" + fraction
				+ "]";
	}
	
	
	
}
