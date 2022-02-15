package com.producer.controller;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class CarDTO {

	private String id;
	private String model;
	private String color;
	
}
