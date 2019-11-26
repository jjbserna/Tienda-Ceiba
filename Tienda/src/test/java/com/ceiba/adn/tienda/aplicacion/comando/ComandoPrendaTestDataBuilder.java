/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.comando;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;


/**
 * @author jeison.barbosa
 *
 */
public class ComandoPrendaTestDataBuilder {

	private static final int ID_PRENDA = 1111;
	private static final int CODIGO_PRENDA = 1234433;
	private static final String DESCRIPCION = "Camisa DevOps";
	private static final String ESTILO = "DevOps";
	private static final boolean ESTADO = true;
	private static final double PRECIO = 100000;
	private static final int STOCK = 100;
	private int idPrenda;
	private int codigoPrenda;
	private String descripcion;
	private String estilo;
	private boolean estado;
	private double precio;
	private int stock;

	/**
	 * @param idPrenda
	 * @param codigoPrenda
	 * @param descripcion
	 * @param estilo
	 * @param estado
	 * @param precio
	 * @param stock
	 */
	public ComandoPrendaTestDataBuilder() {
		this.idPrenda = ID_PRENDA;
		this.codigoPrenda = CODIGO_PRENDA;
		this.descripcion = DESCRIPCION;
		this.estilo = ESTILO;
		this.estado = ESTADO;
		this.precio = PRECIO;
		this.stock = STOCK;
	}

	public ComandoPrendaTestDataBuilder conIdPrenda(int idPrenda) {
		this.idPrenda = idPrenda;
		return this;
	}

	public ComandoPrendaTestDataBuilder conCodigoPrenda(int codigoPrenda) {
		this.codigoPrenda = codigoPrenda;
		return this;
	}

	public ComandoPrendaTestDataBuilder conDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}

	public ComandoPrendaTestDataBuilder conEstilo(String estilo) {
		this.estilo = estilo;
		return this;
	}

	public ComandoPrendaTestDataBuilder conEstado(boolean estado) {
		this.estado = estado;
		return this;
	}

	public ComandoPrendaTestDataBuilder conPrecio(double precio) {
		this.precio = precio;
		return this;
	}

	public ComandoPrendaTestDataBuilder conStock(int stock) {
		this.stock = stock;
		return this;
	}

	public ComandoPrenda build() {
		return new ComandoPrenda(this.idPrenda, this.codigoPrenda, this.descripcion, this.estilo, this.estado,
				this.precio, this.stock);
	}

}
