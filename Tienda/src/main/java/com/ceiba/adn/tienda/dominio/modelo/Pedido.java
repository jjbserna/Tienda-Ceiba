/**
 * 
 */
package com.ceiba.adn.tienda.dominio.modelo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author jeison.barbosa
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class Pedido {
	
	public static final String EL_NUMERO_DE_ORDEN_ES_OBLIGATORIO = "El numero de orden es obligatorio";
	private int idPedido;
	private int numeroOrden;
	private Date fechaPedido;
	private Date fechaEntrega;
	private Cliente clienteId;
	/**
	 * @param idPedido
	 * @param numeroOrden
	 * @param fechaPedido
	 * @param fechaEntrega
	 * @param clienteId
	 */
	public Pedido(int idPedido, int numeroOrden, Date fechaPedido, Date fechaEntrega, Cliente clienteId) {
		ValidarArgumento.validarObligatorio(numeroOrden, EL_NUMERO_DE_ORDEN_ES_OBLIGATORIO);
		this.idPedido = idPedido;
		this.numeroOrden = numeroOrden;
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
		this.clienteId = clienteId;
	}


	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", numeroOrden=" + numeroOrden + ", fechaPedido=" + fechaPedido
				+ ", fechaEntrega=" + fechaEntrega + ", clienteId=" + clienteId + "]";
	}
	
	
	
}
