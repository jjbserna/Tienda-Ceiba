/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ceiba.adn.tienda.TiendaApplication;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoClienteTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jeison.barbosa
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TiendaApplication.class)
@AutoConfigureMockMvc
@Transactional
public class ControladorCliente {
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	@Test
	public void crearTest() throws Exception {
		ComandoCliente comandoCliente= new ComandoClienteTestDataBuilder().build();
		mockMvc.perform(post("/cliente/crear").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoCliente))).andExpect(status().isOk());
	}
	
	@Test
	public void eliminarTest() throws Exception {
		crearTest();
		ComandoCliente comandoCliente= new ComandoClienteTestDataBuilder().build();
		mockMvc.perform(delete("/cliente/eliminar/123456")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(comandoCliente)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void actualizarTest() throws Exception {
		crearTest();
		ComandoCliente comandoCliente= new ComandoClienteTestDataBuilder().build();
		mockMvc.perform(put("/cliente")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(comandoCliente)))
				.andExpect(status().isOk());
	}
	
}
