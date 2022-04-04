package com.isidro.tallerjunit.services;

import com.isidro.tallerjunit.model.Articulo;

public interface BaseDatosI {

	// Iniciar base de datos
	public void iniciar();
	
	// Insertar artículo
	public Integer insertarArticulo(Articulo articulo);
	
	// Buscar por ID
	public Articulo buscarArticulo(Integer identificador);
	
}
