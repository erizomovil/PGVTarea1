package com.jorge.Prueba.producto.services;

import java.util.List;
import java.util.Optional;

import com.jorge.Prueba.producto.models.Producto;

public interface IproductoService {

	public Producto get(Long id);
	public List<Producto> getAll();
	public List<Producto> getAllOrdered(String sortBy, String order);
	public Producto post(Producto producto);
	public void put(Producto producto, long id);
	public void delete(long id);
	
}
