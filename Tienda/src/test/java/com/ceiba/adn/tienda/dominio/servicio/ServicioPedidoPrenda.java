/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrenda;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrendaTestDataBuilder;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionVenta;
import com.ceiba.adn.tienda.dominio.modelo.Pedido;
import com.ceiba.adn.tienda.dominio.modelo.PedidoPrenda;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedido;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedidoPrenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;
import com.ceiba.adn.tienda.dominio.servicio.pedidoprenda.ServicioCrearPedidoPrenda;
import com.ceiba.adn.tienda.dominio.servicio.pedidoprenda.ServicioEliminarPedidoPrenda;
import com.ceiba.adn.tienda.dominio.testdatabuilder.PedidoTestDataBuilder;
import com.ceiba.adn.tienda.dominio.testdatabuilder.PrendaTestDataBuilder;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioPedidoPrenda {
	@Test
	public void crearErrorExistePedidoTest() {
		// arrange
		RepositorioPedidoPrenda repositorioPedidoPrenda = Mockito.mock(RepositorioPedidoPrenda.class);
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		RepositorioPrenda repositorioPrenda = Mockito.mock(RepositorioPrenda.class);
		ComandoPedidoPrenda comandoPedidoPrenda = new ComandoPedidoPrendaTestDataBuilder().build();
		Pedido pedido = new PedidoTestDataBuilder().build();
		Prenda prenda = new PrendaTestDataBuilder().build();
		PedidoPrenda pedidoPrenda = new PedidoPrenda(comandoPedidoPrenda.getIdPedidoPrenda(), pedido, prenda,
				comandoPedidoPrenda.getCantidad(), comandoPedidoPrenda.getValorTotal());
		when(repositorioPedidoPrenda.buscarPorId(comandoPedidoPrenda.getIdPedidoPrenda()))
				.thenReturn(comandoPedidoPrenda);
		ServicioCrearPedidoPrenda servicioCrearPedidoPrenda = new ServicioCrearPedidoPrenda(repositorioPedidoPrenda,
				repositorioPedido, repositorioPrenda);

		try {
			// act
			servicioCrearPedidoPrenda.crear(pedidoPrenda);
			fail();

		} catch (ExcepcionVenta e) {
			// assert
			assertEquals(ServicioCrearPedidoPrenda.EL_PEDIDO_PRENDA_YA_EXISTE, e.getMessage());
		}
	}

	@Test
	public void crearErrorExistePrendaTest() {
		// arrange
		RepositorioPedidoPrenda repositorioPedidoPrenda = Mockito.mock(RepositorioPedidoPrenda.class);
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		RepositorioPrenda repositorioPrenda = Mockito.mock(RepositorioPrenda.class);
		ComandoPedidoPrenda comandoPedidoPrenda = new ComandoPedidoPrendaTestDataBuilder().build();
		Pedido pedido = new PedidoTestDataBuilder().build();
		Prenda prenda = new PrendaTestDataBuilder().build();
		PedidoPrenda pedidoPrenda = new PedidoPrenda(comandoPedidoPrenda.getIdPedidoPrenda(), pedido, prenda,
				comandoPedidoPrenda.getCantidad(), comandoPedidoPrenda.getValorTotal());
		when(repositorioPedidoPrenda.buscarPorId(comandoPedidoPrenda.getIdPedidoPrenda())).thenReturn(null);
		when(repositorioPedido.buscar(pedido.getNumeroOrden())).thenReturn(null);
		ServicioCrearPedidoPrenda servicioCrearPedidoPrenda = new ServicioCrearPedidoPrenda(repositorioPedidoPrenda,
				repositorioPedido, repositorioPrenda);

		try {
			// act
			servicioCrearPedidoPrenda.crear(pedidoPrenda);
			fail();

		} catch (ExcepcionVenta e) {
			// assert
			assertEquals(ServicioCrearPedidoPrenda.EL_PEDIDO_O_PRENDA_NO_EXISTE, e.getMessage());
		}
	}

	@Test
	public void eliminarFalsoTest() {
		// arrange
		RepositorioPedidoPrenda repositorioPedidoPrenda = Mockito.mock(RepositorioPedidoPrenda.class);
		ComandoPedidoPrenda comandoPedidoPrenda = new ComandoPedidoPrendaTestDataBuilder().build();
		when(repositorioPedidoPrenda.buscarPorId(comandoPedidoPrenda.getIdPedidoPrenda())).thenReturn(null);
		ServicioEliminarPedidoPrenda servicioEliminarPedidoPrenda = new ServicioEliminarPedidoPrenda(
				repositorioPedidoPrenda);
		// act-assert
		assertFalse(servicioEliminarPedidoPrenda.eliminar(comandoPedidoPrenda.getIdPedidoPrenda()));
	}

	@Test
	public void eliminarTest() {
		// arrange
		RepositorioPedidoPrenda repositorioPedidoPrenda = Mockito.mock(RepositorioPedidoPrenda.class);
		ComandoPedidoPrenda comandoPedidoPrenda = new ComandoPedidoPrendaTestDataBuilder().build();
		when(repositorioPedidoPrenda.buscarPorId(comandoPedidoPrenda.getIdPedidoPrenda()))
				.thenReturn(comandoPedidoPrenda);
		ServicioEliminarPedidoPrenda servicioEliminarPedidoPrenda = new ServicioEliminarPedidoPrenda(
				repositorioPedidoPrenda);
		// act-assert
		assertTrue(servicioEliminarPedidoPrenda.eliminar(comandoPedidoPrenda.getIdPedidoPrenda()));
	}
	
}
