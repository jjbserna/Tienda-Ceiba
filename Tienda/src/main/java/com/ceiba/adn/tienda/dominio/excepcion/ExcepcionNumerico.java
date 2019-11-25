/**
 * 
 */
package com.ceiba.adn.tienda.dominio.excepcion;

/**
 * @author jeison.barbosa
 *
 */
public class ExcepcionNumerico extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionNumerico (String mensaje) {
		super(mensaje);
	}
}
