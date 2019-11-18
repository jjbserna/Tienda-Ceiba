/**
 * 
 */
package com.ceiba.adn.tienda.entity;

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
	
	@Column(name="descripcion")
	private String descripcion;

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

}
