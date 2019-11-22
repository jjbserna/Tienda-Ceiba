/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.dominio.modelo.Cliente;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class FabricaCliente {

	public Cliente crearCliente(ComandoCliente comandoCliente) {
		return new Cliente(comandoCliente.getIdCliente(),comandoCliente.getIdentificacion(), comandoCliente.getNombre(), comandoCliente.getApellido(),
				comandoCliente.getFechaNacimiento(), comandoCliente.getCorreo(), comandoCliente.getCelular(),
				comandoCliente.getCiudad(), comandoCliente.getDireccionEntrega(), comandoCliente.getUsuario(),
				comandoCliente.getPassword());
	}
}
