/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author jeison.barbosa
 *
 */
@Entity(name="prenda")
public class Prenda implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPrenda;
	
	@Column(name="codigo_prenda", nullable = false)
	private String codigoPrenda;
	
	@Column(name="descripcion")
	private String descripcion;

	@Column(name="estilo")
	private String estilo;
	
	@Column(name="estado")
	private boolean estado;
	
	@Column(name="precio")
	private double precio;
	
	@Column(name="stock")
	private int stock;
	
	
	/**
	 * @return the codigoPrenda
	 */
	public String getCodigoPrenda() {
		return codigoPrenda;
	}

	/**
	 * @param codigoPrenda the codigoPrenda to set
	 */
	public void setCodigoPrenda(String codigoPrenda) {
		this.codigoPrenda = codigoPrenda;
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
