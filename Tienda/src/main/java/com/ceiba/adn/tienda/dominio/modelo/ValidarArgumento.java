/**
 * 
 */
package com.ceiba.adn.tienda.dominio.modelo;

import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionLongitud;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionNumerico;
import com.ceiba.adn.tienda.dominio.excepcion.ExcepcionValorObligatorio;

/**
 * @author jeison.barbosa
 *
 */
public class ValidarArgumento {

	private ValidarArgumento() {
	}

	public static void validarObligatorio(Object valor, String mensaje) {
		if (valor == null || valor.equals(0)) {
			throw new ExcepcionValorObligatorio(mensaje);
		}
	}
	
    public static void validarLongitud(Object valor,int longitud,String mensaje){
    	String numero=String.valueOf(valor);
        if(numero.length() < longitud){
            throw new ExcepcionLongitud(mensaje);
        }
    }

	public static void validarNumerico(int valor, String mensaje) {
		String numero = String.valueOf(valor);
		for (int i = 0; i < numero.length(); i++) {
			if (numero.charAt(i) >= '0' && numero.charAt(i) <= '9') {
				continue;
			} else {
				throw new ExcepcionNumerico(mensaje);
			}
		}

	}

}
