package com.springboot.app.item.model;

import java.io.Serializable;
import java.time.LocalDate;


import lombok.Data;

@Data
public class Producto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8462366156271712191L;
	
	private Long id;
	private String nombre;
	private Double precio;
	private LocalDate createAt;
	
}