package com.springboot.app.productos.models.service;

import java.util.List;

import com.springboot.app.productos.entity.Producto;

public interface ProductoService {

	List<Producto> findAll();
	
	Producto findById(Long id);
	
	Producto save (Producto producto);
	
	void deleteById(Long id);
}
