/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.dominio.modelo.Cliente;
import com.ceiba.adn.tienda.dominio.modelo.Pedido;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPedido;
import com.ceiba.adn.tienda.dominio.testdatabuilder.ClienteTestDataBuilder;
import com.ceiba.adn.tienda.dominio.testdatabuilder.PedidoTestDataBuilder;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioPedidoTest {

	@Test
	public void ValidarExistenciaTest() {
		Pedido pedido = new PedidoTestDataBuilder().build();
		Cliente cliente = new ClienteTestDataBuilder().build();
		RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
		ComandoPedido comandoPedido = new ComandoPedido(pedido.getIdPedido(), pedido.getNumeroOrden(),
				pedido.getFechaPedido(), pedido.getFechaEntrega(), cliente);
		
		

	}
}
