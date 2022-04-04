package com.isidro.tallerjunit.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.isidro.tallerjunit.model.Articulo;

@Service
public class BaseDatosImpl implements BaseDatosI {

	// Mapa (base de datos simulada)
	private Map<Integer, Articulo> baseDatos;

	@Override
	public void iniciar() {
		// Inicializar base de datos
		baseDatos = new HashMap<>();

		// Añadir artículos
		baseDatos.put(1, new Articulo("Art1", 10.00));
		baseDatos.put(2, new Articulo("Art2", 15.00));
		baseDatos.put(3, new Articulo("Art3", 20.00));
		baseDatos.put(4, new Articulo("Art4", 25.00));
		baseDatos.put(5, new Articulo("Art5", 30.00));
	}

	@Override
	public Integer insertarArticulo(Articulo articulo) {

		// ID del nuevo artículo (Tamaño del mapa +1)
		Integer clave = baseDatos.size() + 1;

		// Insertar
		baseDatos.put(clave, articulo);

		// Mostrar ID por consola
		System.out.println("El ID del artículo es " + clave);

		// Retornar nuevo ID
		return clave;
	}

	@Override
	public Articulo buscarArticulo(Integer identificador) {

		return baseDatos.get(identificador);

	}

}
