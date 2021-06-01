package com.springboot.app.productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.productos.entity.Producto;
import com.springboot.app.productos.models.service.ProductoService;

@RestController
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@GetMapping(path = "/listar")
	public List<Producto> findAll() {
		return productoService.findAll();
	}
	
	@GetMapping(path = "/obtener/{id}")
	Producto getDetail(@PathVariable Long id) {
		/*
		try {
		 
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		return productoService.findById(id);
		
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}
	
	@DeleteMapping("/borrar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void borrar(@PathVariable Long id) {
		productoService.deleteById(id);
	}
	
	@PutMapping("/editar/{id}")
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoDB =  productoService.findById(id);
		productoDB.setNombre(producto.getNombre());
		productoDB.setPrecio(producto.getPrecio());
		productoDB.setCreateAt(producto.getCreateAt());
		return productoService.save(productoDB);
	}
	
}
