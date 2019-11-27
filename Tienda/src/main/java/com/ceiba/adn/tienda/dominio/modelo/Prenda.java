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
public class Prenda {
	public static final String LA_PRENDA_ES_UN_DATO_OBLIGATORIO = "La prenda es un dato obligatorio.";
	public static final String EL_STOCK_ES_OBLIGATORIO = "El Stock es un dato obligatorio.";
	public static final String EL_CODIGO_DE_PRENDA_ES_OBLIGATORIO = "El codigo de la prenda es obligatorio.";
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
	public Prenda(int idPrenda, int codigoPrenda, String descripcion, String estilo, boolean estado, double precio,
			int stock) {
		ValidarArgumento.validarObligatorio(codigoPrenda, EL_CODIGO_DE_PRENDA_ES_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(descripcion, LA_PRENDA_ES_UN_DATO_OBLIGATORIO);
		ValidarArgumento.validarObligatorio(stock, EL_STOCK_ES_OBLIGATORIO);
		this.idPrenda = idPrenda;
		this.codigoPrenda = codigoPrenda;
		this.descripcion = descripcion;
		this.estilo = estilo;
		this.estado = estado;
		this.precio = precio;
		this.stock = stock;
	}



}
