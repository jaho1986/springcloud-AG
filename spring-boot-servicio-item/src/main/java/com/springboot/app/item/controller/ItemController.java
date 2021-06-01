package com.springboot.app.item.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.app.item.model.Item;
import com.springboot.app.item.model.Producto;
import com.springboot.app.item.model.service.ItemService;

@RefreshScope
@RestController
public class ItemController {
	
	@Value("${configuracion.texto}")
	private String texto;
	
	@Value("${server.port}")
	private String port;
	
	@Autowired(required = true)
	@Qualifier(value = "feignService")
	private ItemService itemService;
	
	@GetMapping("/listar")
	public List<Item> listar() {
		return itemService.findAll();
	}
	
	@PostMapping("/Crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(Producto producto) {
		return itemService.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		return itemService.update(producto, id);
	}
	
	@DeleteMapping("/eliminar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		itemService.deleteById(id);
	}
	
	@HystrixCommand(fallbackMethod = "respaldarItem")
	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item getDetail(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfiguracion() {
		Map<String,String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("port", port);
		return new ResponseEntity<Map<String,String>>(json, HttpStatus.OK);
	}
	
	public Item respaldarItem(Long id, Integer cantidad) {
		Item item = new Item();
		Producto p = new Producto();
		p.setId(id);
		p.setNombre("Producto Vacío");
		p.setPrecio(0D);
		item.setCantidad(cantidad);
		return new Item(p, cantidad);
	}
}
