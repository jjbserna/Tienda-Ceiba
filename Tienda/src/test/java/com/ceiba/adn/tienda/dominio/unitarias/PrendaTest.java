/**
 * 
 */
package com.ceiba.adn.tienda.dominio.unitarias;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.ceiba.adn.tienda.dominio.entidades.Prenda;
import com.ceiba.adn.tienda.testdatabuilder.PrendaTestDataBuilder;

/**
 * @author jeison.barbosa
 *
 */
public class PrendaTest {

	private static final String DESCRIPCION = "Camisa DevOps";
	private static final String ESTILO = "DevOps";
	private static final boolean ESTADO = true;
	private static final double PRECIO = 100000;
	private static final int STOCK = 100;

	@Test
	public void crearPrendaTest() {

		// arrange
		PrendaTestDataBuilder prendaTestDataBuilder = new PrendaTestDataBuilder().conDescripcion(DESCRIPCION)
				.conEstilo(ESTILO).conEstado(ESTADO).conPrecio(PRECIO).conStock(STOCK);

		// act
		Prenda prenda = prendaTestDataBuilder.build();

		// assert
		assertEquals(DESCRIPCION, prenda.getDescripcion());
	}
}
