package com.powerhouse.entity;

import java.io.Serializable;
import java.time.Month;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"meterId", "month"})
	    
	)

@Entity
public class MeterData implements Serializable {

	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue
	private long id;
	private String meterId;
	private String profileName;
	private String month;
	private double meterReading;
	private Date recordDate;
	
	
	
	public MeterData(String meterId, String profileName, double meterReading, Date recordDate) {
		super();
		this.meterId = meterId;
		this.profileName = profileName;
		this.meterReading = meterReading;
		this.recordDate = recordDate;
	}
	
	

	public MeterData(String meterId, String profileName, String month, double meterReading, Date recordDate) {
		super();
		this.meterId = meterId;
		this.profileName = profileName;
		this.month = month;
		this.meterReading = meterReading;
		this.recordDate = recordDate;
	}



	public long getId() {
		return id;
	}



	public String getMeterId() {
		return meterId;
	}

	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}


	public double getMeterReading() {
		return meterReading;
	}

	public void setMeterReading(double meterReading) {
		this.meterReading = meterReading;
	}
	
	public MeterData() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "MeterData [id=" + id + ", meterId=" + meterId + ", profileName=" + profileName + ", month=" + month
				+ ", meterReading=" + meterReading + ", recordDate=" + recordDate + "]";
	}
	
	

	
}
