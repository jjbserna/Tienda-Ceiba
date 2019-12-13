/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoClienteTestDataBuilder;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionVenta;
import com.ceiba.adn.tienda.dominio.modelo.Cliente;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioCliente;
import com.ceiba.adn.tienda.dominio.servicio.cliente.ServicioActualizarCliente;
import com.ceiba.adn.tienda.dominio.servicio.cliente.ServicioCrearCliente;
import com.ceiba.adn.tienda.dominio.servicio.cliente.ServicioEliminarCliente;
import com.ceiba.adn.tienda.dominio.testdatabuilder.ClienteTestDataBuilder;

/**
 * @author jeison.barbosa
 *
 */
public class ServicioClienteTest {

	@Test
	public void eliminarTest() {
		// arrange
		RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
		ComandoCliente comandoCliente = new ComandoClienteTestDataBuilder().build();
		when(repositorioCliente.buscarPorCedula(comandoCliente.getIdentificacion())).thenReturn(null);
		ServicioEliminarCliente servicioEliminarCliente = new ServicioEliminarCliente(repositorioCliente);
		// act-assert
		assertFalse(servicioEliminarCliente.eliminar(comandoCliente.getIdentificacion()));
	}
	
	@Test
	public void crearTest() {
		// arrange
		RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
		Cliente comandoCliente = new ClienteTestDataBuilder().build();
		Cliente cliente = new Cliente(comandoCliente.getIdCliente(), comandoCliente.getIdentificacion(),
				comandoCliente.getNombre(), comandoCliente.getApellido(), comandoCliente.getFechaNacimiento(),
				comandoCliente.getCorreo(), comandoCliente.getCelular(), comandoCliente.getCiudad(),
				comandoCliente.getDireccionEntrega(), comandoCliente.getUsuario(), comandoCliente.getPassword());
		when(repositorioCliente.buscarPorCedula(comandoCliente.getIdentificacion())).thenReturn(comandoCliente);
		ServicioCrearCliente servicioCrearCliente = new ServicioCrearCliente(repositorioCliente);
		try {
			// act
			servicioCrearCliente.agregar(cliente);
			fail();
		} catch (ExcepcionVenta e) {
			// assert
			assertEquals(ServicioCrearCliente.CLIENTE_EXISTE, e.getMessage());
		}
	}
	
	@Test
	public void actualizarTest() {
		// arrange
		RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
		ComandoCliente comandoCliente = new ComandoClienteTestDataBuilder().build();
		Cliente cliente = new Cliente(comandoCliente.getIdCliente(), comandoCliente.getIdentificacion(),
				comandoCliente.getNombre(), comandoCliente.getApellido(), comandoCliente.getFechaNacimiento(),
				comandoCliente.getCorreo(), comandoCliente.getCelular(), comandoCliente.getCiudad(),
				comandoCliente.getDireccionEntrega(), comandoCliente.getUsuario(), comandoCliente.getPassword());
		when(repositorioCliente.buscarPorCedula(comandoCliente.getIdentificacion())).thenReturn(null);
		ServicioActualizarCliente servicioActualizarCliente = new ServicioActualizarCliente(repositorioCliente);
		try {
			// act
			servicioActualizarCliente.actualizar(cliente);
			fail();
		} catch (ExcepcionVenta e) {
			// assert
			assertEquals(ServicioActualizarCliente.EL_CLIENTE_NO_EXISTE, e.getMessage());
		}
	}

}
