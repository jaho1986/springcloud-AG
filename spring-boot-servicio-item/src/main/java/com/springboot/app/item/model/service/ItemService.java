package com.springboot.app.item.model.service;

import java.util.List;

import com.springboot.app.item.model.Item;
import com.springboot.app.item.model.Producto;

public interface ItemService {
	
	List<Item> findAll();
	Item findById(Long id, Integer cantidad);
	
	Producto update(Producto producto, Long id);
		
	Producto save (Producto producto);
	
	void deleteById(Long id);
}
