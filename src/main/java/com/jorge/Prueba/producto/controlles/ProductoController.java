package com.jorge.Prueba.producto.controlles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jorge.Prueba.producto.models.Producto;
import com.jorge.Prueba.producto.services.IproductoService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin(origins= "*")
public class ProductoController {
	
	@Autowired
	IproductoService productoService;
	/*
	@GetMapping("/productos")
	public List<Producto> getAllProducts(){
		return productoService.getAll();
	}*/
	
	@GetMapping("/productos")
    public List<Producto> getAllProductsOrdered(@RequestParam(required = false) String sortBy,
                                                @RequestParam(required = false) String order) {
        return productoService.getAllOrdered(sortBy, order);
    }
	
	@GetMapping("/productos/{id}")
	public Producto getOne(@PathVariable(value = "id") long id){
		return productoService.get(id);
	}
	
	@PostMapping("/productos")
	public ResponseEntity<Producto> post(@RequestBody Producto producto) {
	    Producto nuevoProducto = productoService.post(producto);
	    return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
	}
	
	@PutMapping("/productos/{id}")
	public ResponseEntity<Producto> put(@PathVariable Long id, @RequestBody Producto producto) {
	    try {
	        productoService.put(producto, id);
	        return new ResponseEntity<>(producto, HttpStatus.OK);
	    } catch (EntityNotFoundException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@DeleteMapping("/productos/{id}")
	public void delete(@PathVariable(value = "id") long id){
		productoService.delete(id);
	}
}
