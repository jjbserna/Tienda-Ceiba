/**
 * 
 */
package com.ceiba.adn.tienda.dominio.repositorio;

import java.util.List;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.infraestructura.entidades.Prenda;

/**
 * @author jeison.barbosa
 *
 */
public interface RepositorioPrenda {
	
	Prenda agregar(Prenda prenda);
	
	List<ComandoPrenda> listar();
	
	ComandoPrenda buscarPorCodigo(int codigoPrenda);
	
	boolean eliminar(int codigoPrenda);

}
