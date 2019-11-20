/**
 * 
 */
package com.ceiba.adn.tienda.dominio.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.dominio.excepcion.VentaException;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;
import com.ceiba.adn.tienda.dominio.repositorio.RepositorioPrenda;
import com.ceiba.adn.tienda.infraestructura.entidades.PrendaEntidad;

/**
 * @author jeison.barbosa
 *
 */
@Component
public class ServicioImpPrenda {
	private static final String PRENDA_EXISTE = "La prenda ya existe";
	private static final String NO_EXISTE_PRENDA = "La prenda no se pudo encontrar, no existe";

	@Autowired
	private RepositorioPrenda repositorioPrenda;

	/**
	 * @param repositorioPrenda
	 */
	public ServicioImpPrenda(RepositorioPrenda repositorioPrenda) {
		this.repositorioPrenda = repositorioPrenda;
	}

	public ComandoPrenda agregar(Prenda prenda) {
		ComandoPrenda comandoPrenda = buscarPorCodigo(prenda.getCodigoPrenda());
		if (comandoPrenda == null) {
			return repositorioPrenda.agregar(prenda);
		} else {
			throw new VentaException(PRENDA_EXISTE);
		}

	}

	public List<ComandoPrenda> listar() {
		return repositorioPrenda.listar();
	}

	public ComandoPrenda buscarPorCodigo(int codigoPrenda) {
		return repositorioPrenda.buscarPorCodigo(codigoPrenda);
	}

	public boolean eliminar(int codigoPrenda) {
		return repositorioPrenda.eliminar(codigoPrenda);
	}

}
