/**
 * 
 */
package com.ceiba.adn.tienda.dominio.modelo;

import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionValorObligatorio;

/**
 * @author jeison.barbosa
 *
 */
public class ValidarArgumento {

	private ValidarArgumento() {}
	
    public static void validarObligatorio(Object valor, String mensaje) {
        if (valor == null) {
            throw new ExcepcionValorObligatorio(mensaje);
        }
    }
}
