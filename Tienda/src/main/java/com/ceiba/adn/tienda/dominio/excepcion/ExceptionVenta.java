package com.ceiba.adn.tienda.dominio.excepcion;

public class ExceptionVenta extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ExceptionVenta(String message) {
		super(message);
	}
	
}
