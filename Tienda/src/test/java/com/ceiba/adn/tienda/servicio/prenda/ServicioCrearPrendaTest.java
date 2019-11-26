/**
 * 
 */
package com.ceiba.adn.tienda.servicio.prenda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionVenta;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioActualizarPrenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioCrearPrenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioEliminarPrenda;
import com.ceiba.adn.tienda.testdatabuilder.PrendaTestDataBuilder;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioCrearPrendaTest {
	private static final String LA_PRENDA_EXISTE = "La prenda ya existe";
	private static final String PRENDA_NUEVA = "Camisa Java";

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
		ComandoPrenda comandoPrenda = new ComandoPrenda(prenda.getIdPrenda(), prenda.getCodigoPrenda(),
				prenda.getDescripcion(), prenda.getEstilo(), prenda.isEstado(), prenda.getPrecio(), prenda.getStock());
		when(repositorioPrenda.buscarPorCodigo(prenda.getCodigoPrenda())).thenReturn(null);
		ServicioEliminarPrenda servicioEliminarPrenda = new ServicioEliminarPrenda(repositorioPrenda);
		// act
		boolean eliminado = servicioEliminarPrenda.eliminar(prenda.getCodigoPrenda());
		// assert
		assertFalse(eliminado);
	}

	@Test
	public void validaractualizacionTest() {
		// arrange
		Prenda prenda = new PrendaTestDataBuilder().build();
		RepositorioPrenda repositorioPrenda = Mockito.mock(RepositorioPrenda.class);
		ComandoPrenda comandoPrenda = new ComandoPrenda(prenda.getIdPrenda(), prenda.getCodigoPrenda(),
				prenda.getDescripcion(), prenda.getEstilo(), prenda.isEstado(), prenda.getPrecio(), prenda.getStock());
		when(repositorioPrenda.buscarPorCodigo(prenda.getCodigoPrenda())).thenReturn(comandoPrenda);
		when(repositorioPrenda.agregar(prenda)).thenReturn(comandoPrenda);
		ServicioActualizarPrenda servicioActualizarPrenda = new ServicioActualizarPrenda(repositorioPrenda);
		prenda.setDescripcion(PRENDA_NUEVA);
		// act
		comandoPrenda=servicioActualizarPrenda.actualizar(prenda);
		// assert
		assertEquals(PRENDA_NUEVA, comandoPrenda.getDescripcion());
	}
}
