package com.jorge.Prueba.producto.models;

import java.io.Serializable;
import java.time.LocalDateTime; // Importa LocalDateTime
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "precio")
	private float precio;

	@Column(name = "fecha_creacion")
	private LocalDateTime fechaCreacion;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public LocalDateTime getFechaCreacion() { // Getter para fechaCreacion
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) { // Setter para fechaCreacion
		this.fechaCreacion = fechaCreacion;
	}

	// Constructor que incluye fechaCreacion
	public Producto(long id, String nombre, float precio, LocalDateTime fechaCreacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.fechaCreacion = fechaCreacion; // Asigna la fecha de creación
	}
	
	public Producto() {
		// Asigna la fecha de creación por defecto al momento de crear una nueva instancia
		this.fechaCreacion = LocalDateTime.now(); // Establece la fecha de creación actual
	}
}