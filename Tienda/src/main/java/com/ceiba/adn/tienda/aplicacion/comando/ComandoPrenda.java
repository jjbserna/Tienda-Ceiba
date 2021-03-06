/**
 * 
 */
package com.ceiba.adn.tienda.aplicacion.comando;

/**
 * @author jeison.barbosa
 *
 */

public class ComandoPrenda {
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
	public ComandoPrenda(int idPrenda, int codigoPrenda, String descripcion, String estilo, boolean estado,
			double precio, int stock) {
		this.idPrenda = idPrenda;
		this.codigoPrenda = codigoPrenda;
		this.descripcion = descripcion;
		this.estilo = estilo;
		this.estado = estado;
		this.precio = precio;
		this.stock = stock;
	}


	public ComandoPrenda() {
	}


	/**
	 * @return the idPrenda
	 */
	public int getIdPrenda() {
		return idPrenda;
	}


	/**
	 * @param idPrenda the idPrenda to set
	 */
	public void setIdPrenda(int idPrenda) {
		this.idPrenda = idPrenda;
	}


	/**
	 * @return the codigoPrenda
	 */
	public int getCodigoPrenda() {
		return codigoPrenda;
	}


	/**
	 * @param codigoPrenda the codigoPrenda to set
	 */
	public void setCodigoPrenda(int codigoPrenda) {
		this.codigoPrenda = codigoPrenda;
	}


	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	/**
	 * @return the estilo
	 */
	public String getEstilo() {
		return estilo;
	}


	/**
	 * @param estilo the estilo to set
	 */
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}


	/**
	 * @return the estado
	 */
	public boolean isEstado() {
		return estado;
	}


	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}


	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}


	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}


	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

}
