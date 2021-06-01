package com.springboot.app.item.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.app.item.model.Producto;

@FeignClient(name="servicio-productos")
public interface ProductoClienteRest {
	
	@GetMapping(path = "listar")
	List<Producto> listar();
	
	@GetMapping(path = "/obtener/{id}")
	Producto getDetail(@PathVariable("id") Long id);
	
	@PostMapping("/crear")
	Producto crear(@RequestBody Producto producto);
	
	@PutMapping("/editar/{id}")
	Producto editar(@RequestBody Producto producto, @PathVariable("id") Long id);
	
	@DeleteMapping("/eliminar/{id}")
	void eliminar(@PathVariable("id") Long id);
}
