/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionVenta;
import com.ceiba.adn.tienda.dominio.modelo.Cliente;
import com.ceiba.adn.tienda.dominio.modelo.Pedido;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioCliente;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedido;
import com.ceiba.adn.tienda.dominio.servicio.pedido.ServicioCrearPedido;
import com.ceiba.adn.tienda.dominio.servicio.pedido.ServicioEliminarPedido;
import com.ceiba.adn.tienda.dominio.testdatabuilder.ClienteTestDataBuilder;
import com.ceiba.adn.tienda.dominio.testdatabuilder.PedidoTestDataBuilder;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioPedidoTest {

	@Test
	public void validarExistenciaTest() {
		// arrange
		Pedido pedido = new PedidoTestDataBuilder().build();
		Cliente cliente = new ClienteTestDataBuilder().build();
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
		ComandoPedido comandoPedido = new ComandoPedido(pedido.getIdPedido(), pedido.getNumeroOrden(),
				pedido.getFechaPedido(), pedido.getFechaEntrega(), cliente);
		when(repositorioPedido.buscar(pedido.getNumeroOrden())).thenReturn(comandoPedido);
		ServicioCrearPedido servicioCrearPedido = new ServicioCrearPedido(repositorioPedido, repositorioCliente);
		try {
			// act
			servicioCrearPedido.crear(pedido);
			fail();
		} catch (ExcepcionVenta e) {
			// assert
			assertEquals(ServicioCrearPedido.PEDIDO_EXISTE, e.getMessage());
		}

	}

	@Test
	public void validarClienteEnPedido() {
		// arrange
		Pedido pedido = new PedidoTestDataBuilder().build();
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
		when(repositorioPedido.buscar(pedido.getNumeroOrden())).thenReturn(null);
		when(repositorioCliente.buscarPorCedula(pedido.getClienteId().getIdCliente())).thenReturn(null);
		ServicioCrearPedido servicioCrearPedido = new ServicioCrearPedido(repositorioPedido, repositorioCliente);
		try {
			// act
			servicioCrearPedido.crear(pedido);
			fail();
		} catch (ExcepcionVenta e) {
			// assert
			assertEquals(ServicioCrearPedido.EL_CLIENTE_NO_EXISTE, e.getMessage());
		}
	}

	@Test
	public void validarCreacionTest() {
		// arrange
		Pedido pedido = new PedidoTestDataBuilder().build();
		Cliente cliente = new ClienteTestDataBuilder().build();
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
		when(repositorioPedido.buscar(pedido.getNumeroOrden())).thenReturn(null);
		ComandoPedido comandoPedido = new ComandoPedido(pedido.getIdPedido(), pedido.getNumeroOrden(),
				pedido.getFechaPedido(), pedido.getFechaEntrega(), cliente);
		ComandoCliente comandoCliete = new ComandoCliente();
		comandoCliete.setIdCliente(cliente.getIdCliente());
		comandoCliete.setIdentificacion(cliente.getIdentificacion());
		when(repositorioCliente.buscarPorCedula(comandoPedido.getClienteId().getIdCliente())).thenReturn(comandoCliete);
		when(repositorioPedido.crear(pedido)).thenReturn(comandoPedido);
		ServicioCrearPedido servicioCrearPedido = new ServicioCrearPedido(repositorioPedido, repositorioCliente);

		// act-assert
		assertNotNull(servicioCrearPedido.crear(pedido));
	}

	@Test
	public void validarEliminacionTest() {
		// arrange
		Pedido pedido = new PedidoTestDataBuilder().build();
		Cliente cliente = new ClienteTestDataBuilder().build();
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		ComandoPedido comandoPedido = new ComandoPedido(pedido.getIdPedido(), pedido.getNumeroOrden(),
				pedido.getFechaPedido(), pedido.getFechaEntrega(), cliente);
		when(repositorioPedido.buscar(pedido.getNumeroOrden())).thenReturn(comandoPedido);

		ServicioEliminarPedido servicioEliminarPedido = new ServicioEliminarPedido(repositorioPedido);
		// act
		boolean eliminado = servicioEliminarPedido.eliminar(pedido.getNumeroOrden());
		// assert
		assertTrue(eliminado);

	}

	@Test
	public void validarNoEliminacionTest() {
		// arrange
		Pedido pedido = new PedidoTestDataBuilder().build();
		Cliente cliente = new ClienteTestDataBuilder().build();
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		ComandoPedido comandoPedido = new ComandoPedido(pedido.getIdPedido(), pedido.getNumeroOrden(),
				pedido.getFechaPedido(), pedido.getFechaEntrega(), cliente);
		when(repositorioPedido.buscar(pedido.getNumeroOrden())).thenReturn(null);

		ServicioEliminarPedido servicioEliminarPedido = new ServicioEliminarPedido(repositorioPedido);
		// act
		boolean eliminado = servicioEliminarPedido.eliminar(pedido.getNumeroOrden());
		// assert
		assertFalse(eliminado);

	}

}
