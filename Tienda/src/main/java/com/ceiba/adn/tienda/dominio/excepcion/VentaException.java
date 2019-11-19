package com.ceiba.adn.tienda.dominio.excepcion;

public class VentaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public VentaException(String message) {
		super(message);
	}
	
}
