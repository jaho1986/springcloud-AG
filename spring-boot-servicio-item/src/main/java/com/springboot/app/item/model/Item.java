package com.springboot.app.item.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	private Producto producto;
	private int cantidad;
	
	public Double getTotal() {
		return this.producto.getPrecio()*cantidad;
	}
}
