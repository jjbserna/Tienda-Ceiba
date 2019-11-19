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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author jeison.barbosa
 *
 */
@Entity(name="pedido_x_prenda")
public class Pedido_x_Prenda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPedidoPrenda;
	
    @ManyToOne
    @JoinColumn(name="pedido_id")
	private Pedido pedidoId;
    
    @ManyToOne
    @JoinColumn(name="prenda_id")
	private Prenda prendaId;
    
	@Column(name="cantidad")
	private double cantidad;
    
	@Column(name="valor_total")
	private double valorTotal;

	/**
	 * @return the idPedidoPrenda
	 */
	public int getIdPedidoPrenda() {
		return idPedidoPrenda;
	}

	/**
	 * @param idPedidoPrenda the idPedidoPrenda to set
	 */
	public void setIdPedidoPrenda(int idPedidoPrenda) {
		this.idPedidoPrenda = idPedidoPrenda;
	}

	/**
	 * @return the pedidoId
	 */
	public Pedido getPedidoId() {
		return pedidoId;
	}

	/**
	 * @param pedidoId the pedidoId to set
	 */
	public void setPedidoId(Pedido pedidoId) {
		this.pedidoId = pedidoId;
	}

	/**
	 * @return the prendaId
	 */
	public Prenda getPrendaId() {
		return prendaId;
	}

	/**
	 * @param prendaId the prendaId to set
	 */
	public void setPrendaId(Prenda prendaId) {
		this.prendaId = prendaId;
	}

	/**
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the valorTotal
	 */
	public double getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	

}
