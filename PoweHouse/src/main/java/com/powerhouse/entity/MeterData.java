package com.powerhouse.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MeterData implements Serializable {

	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue
	private long id;
	private long meterId;
	private String profileName;
	private String month;
	private double meterReading;
	@Temporal(TemporalType.DATE)
	private Date recordDate;
	
	
	public MeterData(long meterId, String profileName, double meterReading, Date recordDate) {
		super();
		this.meterId = meterId;
		this.profileName = profileName;
		this.meterReading = meterReading;
		this.recordDate = recordDate;
	}
	
	

	public MeterData(long meterId, String profileName, String month, double meterReading, Date recordDate) {
		super();
		this.meterId = meterId;
		this.profileName = profileName;
		this.month = month;
		this.meterReading = meterReading;
		this.recordDate = recordDate;
	}



	public long getMeterId() {
		return meterId;
	}

	public void setMeterId(long meterId) {
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
