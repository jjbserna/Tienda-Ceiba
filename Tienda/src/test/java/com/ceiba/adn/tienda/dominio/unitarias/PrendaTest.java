/**
 * 
 */
package com.ceiba.adn.tienda.dominio.unitarias;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.ceiba.adn.tienda.dominio.modelo.Prenda;
import com.ceiba.adn.tienda.testdatabuilder.PrendaTestDataBuilder;

/**
 * @author jeison.barbosa
 *
 */
public class PrendaTest {
	
	private static final int CODIGO_PRENDA = 1234;
	private static final String DESCRIPCION = "Camisa DevOps";
	private static final String ESTILO = "DevOps";
	private static final boolean ESTADO = true;
	private static final double PRECIO = 100000;
	private static final int STOCK = 100;

	@Test
	public void crearPrendaTest() {

		// arrange
		PrendaTestDataBuilder prendaTestDataBuilder = new PrendaTestDataBuilder().conCodigoPrenda(CODIGO_PRENDA).conDescripcion(DESCRIPCION)
				.conEstilo(ESTILO).conEstado(ESTADO).conPrecio(PRECIO).conStock(STOCK);

		// act
		Prenda prenda = prendaTestDataBuilder.build();

		// assert
		assertEquals(CODIGO_PRENDA, prenda.getCodigoPrenda());
		assertEquals(DESCRIPCION, prenda.getDescripcion());
		assertEquals(ESTILO, prenda.getEstilo());
		assertEquals(ESTADO, prenda.isEstado());
		assertEquals(PRECIO, prenda.getPrecio(),0);
		assertEquals(STOCK, prenda.getStock());
	}
	
	
}
