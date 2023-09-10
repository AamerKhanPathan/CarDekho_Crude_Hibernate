package com.jspiders.hibernate.car_dekho.CarDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ValueGenerationType;

import lombok.Data;

@Data
@Entity

public class CarDTO {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "car_id")
	private int id;
	@Column(name = "car_name")
	private String Name;
	
	private String componyName;
	private double price;
	private String color;
	
	
	

}
