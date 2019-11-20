package com.ceiba.adn.tienda.dominio.excepcion;

public class ExcepcionValorObligatorio extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionValorObligatorio(String message) {
        super(message);
    }
}
