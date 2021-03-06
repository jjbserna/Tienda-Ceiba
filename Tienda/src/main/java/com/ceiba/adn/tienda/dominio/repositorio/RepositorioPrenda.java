/**
 * 
 */
package com.ceiba.adn.tienda.dominio.repositorio;

import java.util.List;

import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.dominio.modelo.Prenda;

/**
 * @author jeison.barbosa
 *
 */
public interface RepositorioPrenda {
	
	ComandoPrenda agregar(Prenda prenda);
	
	List<ComandoPrenda> listar();
	
	ComandoPrenda buscarPorCodigo(int codigoPrenda);
	
	void eliminar(int codigoPrenda);
	
	ComandoPrenda actualizar(Prenda prenda);

}
