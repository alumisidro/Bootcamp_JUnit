package com.isidro.tallerjunit.services;

import java.util.List;

import com.isidro.tallerjunit.model.Articulo;

public interface CarritoCompraServiceI {

	public void limpiarCesta();
	public void addArticulo(Articulo articulo);
	public Integer getNumArticulos();
	public List<Articulo> getArticulos();
	public Double totalPrice();
	public Double calculadorDescuento(Double precio, Double porcentaje);
	public Double aplicarDescuento(Integer idArticulo, Double porcentaje);
	public Integer insertar(Articulo articulo);
}
