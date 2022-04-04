package com.isidro.tallerjunit.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.isidro.tallerjunit.model.Articulo;

public class CarritoCompraServiceImpl implements CarritoCompraServiceI {

	@Autowired
	private BaseDatosI baseDatos;

	private List<Articulo> cesta = new ArrayList<>();

	@Override
	public void limpiarCesta() {

		cesta.clear();

	}

	@Override
	public void addArticulo(Articulo articulo) {

		cesta.add(articulo);

	}

	@Override
	public Integer getNumArticulos() {

		return cesta.size();
	}

	@Override
	public List<Articulo> getArticulos() {

		return cesta;

	}

	@Override
	public Double totalPrice() {

		// Inicializar variable
		Double total = 0D;

		// Recorrer cesta
		for (Articulo articulo : this.cesta) {
			total += articulo.getPrecio();
		}

		// Retornar suma
		return total;

	}

	@Override
	public Double calculadorDescuento(Double precio, Double porcentaje) {

		return precio - precio * porcentaje / 100;
	}

	@Override
	public Double aplicarDescuento(Integer idArticulo, Double porcentaje) {

		// Inicializar base de datos
		// baseDatos.iniciar();

		// Obtener artículo de la BD
		Articulo articulo = baseDatos.buscarArticulo(idArticulo);
		Articulo articulo2 = baseDatos.buscarArticulo(idArticulo);

		// if(articulo != null) {
		if (Optional.ofNullable(articulo).isPresent()) {
			// Retornar descuento
			return this.calculadorDescuento(articulo.getPrecio(), porcentaje);

		} else {
			System.out.println("No se ha encontrado el artículo con ID " + idArticulo);
		}

		// Si no existe el articulo devuelve 0
		return 0D;

	}

	// Método para el reto final
	public Integer insertar(Articulo articulo) {

		// Inicializar base de datos
		baseDatos.iniciar();

		// Añadir artículo a la base de datos
		Integer idArticulo = this.baseDatos.insertarArticulo(articulo);

		// Añadir artículo a la cesta
		this.addArticulo(articulo);
		

		// Retornar ID
		return idArticulo;

	}

}
