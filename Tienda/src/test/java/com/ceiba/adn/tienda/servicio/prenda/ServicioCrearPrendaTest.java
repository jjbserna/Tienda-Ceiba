/**
 * 
 */
package com.ceiba.adn.tienda.servicio.prenda;

import org.junit.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionVenta;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;
import com.ceiba.adn.tienda.dominio.servicio.prenda.ServicioCrearPrenda;
import com.ceiba.adn.tienda.testdatabuilder.PrendaTestDataBuilder;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioCrearPrendaTest {
	private static final String LA_PRENDA_EXISTE="La prenda ya existe";
	
	@Test
	public void validarExistenciaTest() {
		// arrange
		Prenda prenda = new PrendaTestDataBuilder().build();
		RepositorioPrenda repositorioPrenda = Mockito.mock(RepositorioPrenda.class);
		ComandoPrenda comandoPrenda = new ComandoPrenda(prenda.getIdPrenda(), prenda.getCodigoPrenda(),
				prenda.getDescripcion(), prenda.getEstilo(), prenda.isEstado(), prenda.getPrecio(), prenda.getStock());
		when(repositorioPrenda.buscarPorCodigo(prenda.getCodigoPrenda())).thenReturn(comandoPrenda);
		ServicioCrearPrenda servicioCrearPrenda=new ServicioCrearPrenda(repositorioPrenda);
		
		try {
			// act
			servicioCrearPrenda.agregar(prenda);
			fail();
		}
		catch (ExcepcionVenta e) {
			// assert
			assertEquals(LA_PRENDA_EXISTE,e.getMessage());
		}

	}
}
