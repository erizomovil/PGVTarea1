package com.jorge.Prueba.producto.dao;

import org.springframework.data.repository.CrudRepository;


import com.jorge.Prueba.producto.models.Producto;

public interface IProductoDao extends CrudRepository <Producto, Long>{

	
}
