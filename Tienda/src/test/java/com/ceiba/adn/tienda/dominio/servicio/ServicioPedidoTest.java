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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
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
	private final static String FECHA_SOLICITUD="2019-11-27";
	private final static String FECHA_ESPERADA="2019-12-02";
	private final static String FECHA_PEDIDO_SABADO="2019-11-30";
	private final static String FECHA_PEDIDO_DOMINGO="2019-12-01";

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
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		when(repositorioPedido.buscar(pedido.getNumeroOrden())).thenReturn(null);

		ServicioEliminarPedido servicioEliminarPedido = new ServicioEliminarPedido(repositorioPedido);
		// act
		boolean eliminado = servicioEliminarPedido.eliminar(pedido.getNumeroOrden());
		// assert
		assertFalse(eliminado);

	}
	

	@Test
	public void validarFechaEntrega() {
		// arrange
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
		ServicioCrearPedido servicioCrearPedido = new ServicioCrearPedido(repositorioPedido, repositorioCliente);
		Date fechaSolicitud;
		Date fechaEsperada;
		Date fechaEntrega;
		try {
			fechaSolicitud = new SimpleDateFormat("yyyy-MM-dd").parse(FECHA_SOLICITUD);
			fechaEsperada = new SimpleDateFormat("yyyy-MM-dd").parse(FECHA_ESPERADA);
			// act
			fechaEntrega=servicioCrearPedido.generarFechaEntrega(ServicioCrearPedido.DIAS_ENTREGA, fechaSolicitud);
			// assert
			Assert.assertEquals(fechaEsperada, fechaEntrega);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void validarFechasRestriccion() {
		// arrange
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
		ServicioCrearPedido servicioCrearPedido = new ServicioCrearPedido(repositorioPedido, repositorioCliente);
		
		try {
			Date fechaPedidoSabado = new SimpleDateFormat("yyyy-MM-dd").parse(FECHA_PEDIDO_SABADO);
			Date fechaPedidoDomingo = new SimpleDateFormat("yyyy-MM-dd").parse(FECHA_PEDIDO_DOMINGO);
			// act
			boolean pedido=servicioCrearPedido.validarFecha(fechaPedidoSabado);
			boolean pedido2=servicioCrearPedido.validarFecha(fechaPedidoDomingo);
			// assert
			assertTrue(pedido);
			assertTrue(pedido2);
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}
	

}
