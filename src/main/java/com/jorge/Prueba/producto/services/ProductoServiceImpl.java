package com.jorge.Prueba.producto.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorge.Prueba.producto.dao.IProductoDao;
import com.jorge.Prueba.producto.models.Producto;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoServiceImpl implements IproductoService{

	@Autowired
	IProductoDao productoDao;
	
	@Override
	public Producto get(Long id) {
		return productoDao.findById(id).get();
	}

	@Override
	public List<Producto> getAll() {
		return (List<Producto>) productoDao.findAll();
	}
	
	@Override
	public List<Producto> getAllOrdered(String sortBy, String order) {
        List<Producto> productos = getAll();

        if (sortBy != null && order != null) {
            Comparator<Producto> comparator;

            switch (sortBy) {
                case "nombre":
                    comparator = Comparator.comparing(Producto::getNombre);
                    break;
                case "precio":
                    comparator = Comparator.comparing(Producto::getPrecio);
                    break;
                case "fechaCreacion":
                    comparator = Comparator.comparing(Producto::getFechaCreacion);
                    break;
                default:
                    return productos;
            }
            if ("descending".equalsIgnoreCase(order)) {
                comparator = comparator.reversed();
            }
            return productos.stream().sorted(comparator).collect(Collectors.toList());
        }
        return productos;
    }
	

	@Override
	public Producto post(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	public void put(Producto producto, long id) {
	    Optional<Producto> productoExistenteOpt = productoDao.findById(id);
	    if (productoExistenteOpt.isPresent()) {
	        Producto productoExistente = productoExistenteOpt.get();
	        producto.setId(id);
	        productoDao.save(producto);
	    } else {
	        throw new EntityNotFoundException("Producto no encontrado con id: " + id);
	    }
	}

	@Override
	public void delete(long id) {
		productoDao.deleteById(id);
	}


}
