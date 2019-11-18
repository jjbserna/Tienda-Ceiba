/**
 * 
 */
package com.ceiba.adn.tienda.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author jeison.barbosa
 *
 */
@Entity(name="master_hist_pedido")
public class MasterHistPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idHistMaster;
	
	@Column(name="numero_orden")
	private int numeroOrden;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Prenda clienteId;
	
	@Column(name="fecha_compra")
	@Temporal(TemporalType.DATE)
	private Date fechaCompra;

	/**
	 * @return the idHistMaster
	 */
	public int getIdHistMaster() {
		return idHistMaster;
	}

	/**
	 * @param idHistMaster the idHistMaster to set
	 */
	public void setIdHistMaster(int idHistMaster) {
		this.idHistMaster = idHistMaster;
	}

	/**
	 * @return the numeroOrden
	 */
	public int getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * @param numeroOrden the numeroOrden to set
	 */
	public void setNumeroOrden(int numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * @return the clienteId
	 */
	public Prenda getClienteId() {
		return clienteId;
	}

	/**
	 * @param clienteId the clienteId to set
	 */
	public void setClienteId(Prenda clienteId) {
		this.clienteId = clienteId;
	}

	/**
	 * @return the fechaCompra
	 */
	public Date getFechaCompra() {
		return fechaCompra;
	}

	/**
	 * @param fechaCompra the fechaCompra to set
	 */
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
	

}
