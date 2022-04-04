package com.isidro.tallerjunit.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.isidro.tallerjunit.model.Articulo;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class CarritoCompraServiceImplTest {

	@InjectMocks
	private CarritoCompraServiceImpl carritoService = new CarritoCompraServiceImpl();

	@Mock
	private BaseDatosI baseDatos;

	@Test
	public void testLimpiarCesta() {

		// Añadir un artículo a la cesta
		carritoService.addArticulo(new Articulo("Camiseta", 15.99));
		// Comprobar que no está vacía
		assertFalse(carritoService.getArticulos().isEmpty());

		// Vaciar cesta
		carritoService.limpiarCesta();
		// Comprobar que está vacía
		assertTrue(carritoService.getArticulos().isEmpty());
	}

	@Test
	public void testAddArticulo() {

		// Añadir un artículo a la cesta
		carritoService.addArticulo(new Articulo("Camiseta", 15.99));

		// Comprobar que no está vacía
		assertFalse(carritoService.getArticulos().isEmpty());

		// Comprobar que tiene un artículo
		assertEquals((Integer) 1, carritoService.getNumArticulos());

		// También se podría comprobar que el nombre del artículo añadido coincida con
		// el que especifiquemos.

	}

	@Test
	public void testGetNumArticulos() {

		// Añadir un artículo a la cesta
		carritoService.addArticulo(new Articulo("Art1", 5.00));
		// Añadir otro artículo a la cesta
		carritoService.addArticulo(new Articulo("Art2", 10.00));

		// Comprobar que tiene dos artículos
		// assertEquals((Integer) 2, carritoService.getNumArticulos());
		// assertEquals(2, carritoService.getNumArticulos(), 0);
		assertEquals(Integer.valueOf(2), carritoService.getNumArticulos());

	}

	@Test
	public void testGetArticulos() {

		// Añadir a la cesta
		carritoService.addArticulo(new Articulo("Art1", 10.00));
		carritoService.addArticulo(new Articulo("Art2", 20.00));

		// Comparar nombre del primer artículo
		assertEquals("Art1", carritoService.getArticulos().get(0).getNombre());

		// Comprobar que hay dos artículos
		assertEquals(Integer.valueOf(2), carritoService.getNumArticulos());

		// Comprobar que el precio es el correcto (10+20)
		assertEquals(Double.valueOf(30D), carritoService.totalPrice());

	}

	@Test
	public void testTotalPrice() {

		// Añadir dos artículos a la cesta
		carritoService.addArticulo(new Articulo("Art1", 5.00));
		carritoService.addArticulo(new Articulo("Art2", 10.00));

		// Comprobar resultado
		// assertEquals((Double) 15.00, carritoService.totalPrice());
		assertEquals(Double.valueOf(15D), carritoService.totalPrice());

	}

	@Test
	public void testCalculadorDescuento() {

		// Comprobar resultado (100-25% = 75)
		assertEquals(Double.valueOf(75D), carritoService.calculadorDescuento(100D, 25D));

	}

	@Test
	public void testAplicarDescuento() {

		// Crear artículo
		Articulo articulo = new Articulo("Camiseta", 20.00);

		// Simular llamada (Cuando busque un artículo, meter en el resultado el artículo
		// que hemos creado arriba)
		when(baseDatos.buscarArticulo(any(Integer.class))).thenReturn(articulo);

		// Aplicar el descuento sobre ese artículo
		Double res = carritoService.aplicarDescuento(29, 10D);

		// Comprobar que el valor es el esperado (20-1%=18)
		assertEquals(Double.valueOf(18D), res);

		// Verificar simulación
		// verify(baseDatos).buscarArticulo(any(Integer.class));

		// Verificar que se ha llamado al menos 1 vez
		// verify(baseDatos, atLeast(1)).buscarArticulo(any(Integer.class));

		// Verificar que se ha llamado dos veces (en aplicarDescuento() de
		// CarritoCompraServiceImpl)
		verify(baseDatos, times(2)).buscarArticulo(any(Integer.class));

	}

	@Test
	public void testInsertar() {

		// Crear artículo
		Articulo articulo = new Articulo("Art1", 5.99);

		// Cuando inserte el artículo me retornará 1
		when(baseDatos.insertarArticulo(articulo)).thenReturn(1);

		// Variable con el ID del artículo
		Integer idEnCarrito = carritoService.insertar(articulo);

		// Comprobar que el ID en el carrito es 1
		assertEquals(Integer.valueOf(1), idEnCarrito);

		// Comprobar que el nombre y precio del artículo coincide con el del test
		assertEquals("Art1", carritoService.getArticulos().get(0).getNombre());
		assertEquals(Double.valueOf(5.99), carritoService.getArticulos().get(0).getPrecio());

		// Verificar que se llama al insertar en base de datos al menos una vez
		verify(baseDatos, atLeast(1)).insertarArticulo(any(Articulo.class));
	}
}
