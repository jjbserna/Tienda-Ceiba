/**
 * 
 */
package com.ceiba.adn.tienda.dominio.excepcion;

/**
 * @author jeison.barbosa
 *
 */
public class ExcepcionLongitud extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcepcionLongitud (String mensaje) {
		super(mensaje);
	}

}
