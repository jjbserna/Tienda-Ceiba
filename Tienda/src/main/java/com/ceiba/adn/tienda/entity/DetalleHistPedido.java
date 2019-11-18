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
@Entity (name="detalle_hist_pedido")
public class DetalleHistPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDetalleHist;
	
	@Column(name="codigo_prenda")
	private String codigoPrenda;
	
	@Column(name="descripcion_prenda")
	private String descripcionPrenda;
	
	@Column(name="cantidad")
	private int cantidad;
	
	@Column(name="total_pedido")
	private double totalPedido;

	/**
	 * @return the idDetalleHist
	 */
	public int getIdDetalleHist() {
		return idDetalleHist;
	}

	/**
	 * @param idDetalleHist the idDetalleHist to set
	 */
	public void setIdDetalleHist(int idDetalleHist) {
		this.idDetalleHist = idDetalleHist;
	}

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
	 * @return the descripcionPrenda
	 */
	public String getDescripcionPrenda() {
		return descripcionPrenda;
	}

	/**
	 * @param descripcionPrenda the descripcionPrenda to set
	 */
	public void setDescripcionPrenda(String descripcionPrenda) {
		this.descripcionPrenda = descripcionPrenda;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the totalPedido
	 */
	public double getTotalPedido() {
		return totalPedido;
	}

	/**
	 * @param totalPedido the totalPedido to set
	 */
	public void setTotalPedido(double totalPedido) {
		this.totalPedido = totalPedido;
	}
	
	
	
}
