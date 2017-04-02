package com.powerhouse.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"profileName" , "meterId"})
	)

@Entity
public class Profile {

	@Id
	@GeneratedValue
	private long id;
	private String meterId;
	private String profileName;
	
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true , mappedBy="profile")
	private List<Consumption> consumption;


	public Profile(long id, String meterId, String profileName) {
		super();
		this.id = id;
		this.meterId = meterId;
		this.profileName = profileName;
	}

	public String getMeterId() {
		return meterId;
	}

	public void setMeterId(String meterId) {
		this.meterId = meterId;
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

	
	public List<Consumption> getConsumption() {
		return consumption;
	}

	public void setConsumption(List<Consumption> consumption) {
		this.consumption = consumption;
		for (Consumption con : consumption) {
			if (con.getProfile() == null) {
				con.setProfile(this);
			}
		}
	}
	

	@Override
	public String toString() {
		return "Profile [id=" + id + ", meterId=" + meterId + ", profileName=" + profileName + ", consumption="
				+ consumption + "]";
	}

}
