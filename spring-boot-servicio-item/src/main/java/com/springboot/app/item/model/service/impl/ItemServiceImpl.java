package com.springboot.app.item.model.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.app.item.model.Item;
import com.springboot.app.item.model.Producto;
import com.springboot.app.item.model.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays.asList(restTemplate.getForObject("http://servicio-productos/listar", Producto[].class));
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Producto producto = restTemplate.getForObject("http://servicio-productos/obtener/{id}", Producto.class, pathVariables);
		return new Item(producto, cantidad);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		HttpEntity<Producto> body = new HttpEntity<>(producto);
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		ResponseEntity<Producto> response = restTemplate.exchange(
				"http://servicio-productos/editar/{id}", 
				HttpMethod.PUT, 
				body, 
				Producto.class, 
				pathVariables);
		return response.getBody();
	}

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<>(producto);
		ResponseEntity<Producto> response = restTemplate.exchange("http://servicio-productos/crear", HttpMethod.POST, body, Producto.class);	
		return response.getBody();
	}

	@Override
	public void deleteById(Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		restTemplate.delete("http://servicio-productos/eliminar", pathVariables);
	}

}
