/**
 * 
 */
package com.ceiba.adn.tienda.dominio.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.adn.tienda.dominio.testdatabuilder.PedidoTestDataBuilder;

/**
 * @author jeison.barbosa
 *
 */
public class PedidoTest {

	@Test
	public void testNumeroOrdenObligatorio() {
		// Arrange
		PedidoTestDataBuilder  pedidoTestDataBuilder=new PedidoTestDataBuilder();
		pedidoTestDataBuilder.conNumeroOrden(0);

		try {
			// Act
			pedidoTestDataBuilder.build();
			fail();

		} catch (ExcepcionValorObligatorio e) {
			// assert
			assertEquals(Pedido.EL_NUMERO_DE_ORDEN_ES_OBLIGATORIO, e.getMessage());
		}
	}
}
