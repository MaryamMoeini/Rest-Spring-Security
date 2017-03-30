package com.powerhouse.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"profileName", "month"})
	)

@Entity
public class Profile implements Serializable {
	
	@Id
	@GeneratedValue
	long id;
	
	String profileName;
	String month;
	double fraction;
	
}
