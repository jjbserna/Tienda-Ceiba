package com.ceiba.adn.tienda.dominio.excepcion;

public class ExcepcionVenta extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ExcepcionVenta(String message) {
		super(message);
	}
	
}
