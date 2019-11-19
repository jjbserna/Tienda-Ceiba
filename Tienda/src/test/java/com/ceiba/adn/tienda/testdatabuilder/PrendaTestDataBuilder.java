/**
 * 
 */
package com.ceiba.adn.tienda.testdatabuilder;

import com.ceiba.adn.tienda.dominio.entidades.Prenda;


/**
 * @author jeison.barbosa
 *
 */
public class PrendaTestDataBuilder {

	private static final String DESCRIPCION = "Camisa DevOps";
	private static final String ESTILO = "DevOps";
	private static final boolean ESTADO = true;
	private static final double PRECIO = 100000;
	private static final int STOCK = 100;
	
	private String descripcion;
	private String estilo;
	private boolean estado;
	private double precio;
	private int stock;

	
	/**
	 * @param descripcion
	 * @param estilo
	 * @param estado
	 * @param precio
	 * @param stock
	 */
	public PrendaTestDataBuilder() {
		this.descripcion = DESCRIPCION;
		this.estilo = ESTILO;
		this.estado = ESTADO;
		this.precio = PRECIO;
		this.stock = STOCK;
	}

	public PrendaTestDataBuilder conDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	
	public PrendaTestDataBuilder conEstilo(String estilo) {
		this.estilo = estilo;
		return this;
	}
	
	public PrendaTestDataBuilder conEstado(boolean estado) {
		this.estado = estado;
		return this;
	}
	
	public PrendaTestDataBuilder conPrecio(double precio) {
		this.precio = precio;
		return this;
	}
	
	public PrendaTestDataBuilder conStock(int stock) {
		this.stock = stock;
		return this;
	}
	
	public Prenda build() {
		return new Prenda(this.descripcion, this.estilo, this.estado, this.precio, this.stock);
	}

}
