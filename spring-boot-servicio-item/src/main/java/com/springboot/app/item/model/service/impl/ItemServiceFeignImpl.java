package com.springboot.app.item.model.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.springboot.app.item.clients.ProductoClienteRest;
import com.springboot.app.item.model.Item;
import com.springboot.app.item.model.Producto;
import com.springboot.app.item.model.service.ItemService;

@Service(value = "feignService")
@Primary  
public class ItemServiceFeignImpl implements ItemService {

	@Autowired
	private ProductoClienteRest productoClienteRest;
	
	@Override
	public List<Item> findAll() {
		return productoClienteRest.listar().stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(productoClienteRest.getDetail(id), cantidad);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return productoClienteRest.editar(producto, id);
	}

	@Override
	public Producto save(Producto producto) {
		return productoClienteRest.crear(producto);
	}

	@Override
	public void deleteById(Long id) {
		productoClienteRest.eliminar(id);
	}
}
