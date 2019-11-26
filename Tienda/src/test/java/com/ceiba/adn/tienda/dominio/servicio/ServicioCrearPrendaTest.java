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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrendaTestDataBuilder;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionVenta;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioActualizarPrenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioCrearPrenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioEliminarPrenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioListarPrenda;
import com.ceiba.adn.tienda.dominio.testdatabuilder.PrendaTestDataBuilder;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioCrearPrendaTest {
	private static final String LA_PRENDA_EXISTE = "La prenda ya existe";
	private static final String PRENDA_NUEVA = "Camisa Java";
	private static final int TAMANO_LISTA = 1;

	@Test
	public void validarExistenciaTest() {
		// arrange
		Prenda prenda = new PrendaTestDataBuilder().build();
		RepositorioPrenda repositorioPrenda = Mockito.mock(RepositorioPrenda.class);
		ComandoPrenda comandoPrenda = new ComandoPrenda(prenda.getIdPrenda(), prenda.getCodigoPrenda(),
				prenda.getDescripcion(), prenda.getEstilo(), prenda.isEstado(), prenda.getPrecio(), prenda.getStock());
		when(repositorioPrenda.buscarPorCodigo(prenda.getCodigoPrenda())).thenReturn(comandoPrenda);
		ServicioCrearPrenda servicioCrearPrenda = new ServicioCrearPrenda(repositorioPrenda);
		try {
			// act
			servicioCrearPrenda.agregar(prenda);
			fail();
		} catch (ExcepcionVenta e) {
			// assert
			assertEquals(LA_PRENDA_EXISTE, e.getMessage());
		}

	}

	@Test
	public void validarCreacionTest() {
		// arrange
		Prenda prenda = new PrendaTestDataBuilder().build();
		RepositorioPrenda repositorioPrenda = Mockito.mock(RepositorioPrenda.class);
		when(repositorioPrenda.buscarPorCodigo(prenda.getCodigoPrenda())).thenReturn(null);
		ComandoPrenda comandoPrenda = new ComandoPrenda(prenda.getIdPrenda(), prenda.getCodigoPrenda(),
				prenda.getDescripcion(), prenda.getEstilo(), prenda.isEstado(), prenda.getPrecio(), prenda.getStock());
		when(repositorioPrenda.agregar(prenda)).thenReturn(comandoPrenda);
		ServicioCrearPrenda servicioCrearPrenda = new ServicioCrearPrenda(repositorioPrenda);
		// act
		comandoPrenda = servicioCrearPrenda.agregar(prenda);
		// assert
		assertNotNull(comandoPrenda);
	}

	@Test
	public void validarEliminaciontest() {
		// arrange
		Prenda prenda = new PrendaTestDataBuilder().build();
		RepositorioPrenda repositorioPrenda = Mockito.mock(RepositorioPrenda.class);
		ComandoPrenda comandoPrenda = new ComandoPrenda(prenda.getIdPrenda(), prenda.getCodigoPrenda(),
				prenda.getDescripcion(), prenda.getEstilo(), prenda.isEstado(), prenda.getPrecio(), prenda.getStock());
		when(repositorioPrenda.agregar(prenda)).thenReturn(comandoPrenda);
		when(repositorioPrenda.buscarPorCodigo(prenda.getCodigoPrenda())).thenReturn(comandoPrenda);

		ServicioEliminarPrenda servicioEliminarPrenda = new ServicioEliminarPrenda(repositorioPrenda);
		// act
		boolean eliminado = servicioEliminarPrenda.eliminar(prenda.getCodigoPrenda());
		// assert
		assertTrue(eliminado);
	}

	@Test
	public void validarExistenciaEliminacionTest() {
		// arrange
		Prenda prenda = new PrendaTestDataBuilder().build();
		RepositorioPrenda repositorioPrenda = Mockito.mock(RepositorioPrenda.class);
		when(repositorioPrenda.buscarPorCodigo(prenda.getCodigoPrenda())).thenReturn(null);
		ServicioEliminarPrenda servicioEliminarPrenda = new ServicioEliminarPrenda(repositorioPrenda);
		// act
		boolean eliminado = servicioEliminarPrenda.eliminar(prenda.getCodigoPrenda());
		// assert
		assertFalse(eliminado);
	}

	@Test
	public void validarNoActualizacionTest() {
		// arrange
		Prenda prenda = new PrendaTestDataBuilder().build();
		RepositorioPrenda repositorioPrenda = Mockito.mock(RepositorioPrenda.class);
		when(repositorioPrenda.buscarPorCodigo(prenda.getCodigoPrenda())).thenReturn(null);
		ServicioActualizarPrenda servicioActualizarPrenda = new ServicioActualizarPrenda(repositorioPrenda);
		try {
			// act
			servicioActualizarPrenda.actualizar(prenda);
			fail();
		} catch (ExcepcionVenta e) {
			// assert
			assertEquals(ServicioActualizarPrenda.LA_PRENDA_NO_EXISTE, e.getMessage());
		}

	}

	@Test
	public void validarGenerarListaTest() {
		// arrange
		List<ComandoPrenda> listaComandoPrenda = new ArrayList<ComandoPrenda>();
		listaComandoPrenda.add(new ComandoPrendaTestDataBuilder().build());
		RepositorioPrenda repositorioPrenda = Mockito.mock(RepositorioPrenda.class);
		when(repositorioPrenda.listar()).thenReturn(listaComandoPrenda);
		ServicioListarPrenda servicioListarPrenda = new ServicioListarPrenda(repositorioPrenda);

		// act-assert
		assertEquals(TAMANO_LISTA, servicioListarPrenda.listar().size());

	}

}
