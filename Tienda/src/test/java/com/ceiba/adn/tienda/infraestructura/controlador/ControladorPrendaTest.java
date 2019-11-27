/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.controlador;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrenda;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPrendaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jeison.barbosa
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TiendaApplication.class)
@AutoConfigureMockMvc
@Transactional
public class ControladorPrendaTest {

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

		ComandoPrenda comandoPrenda = new ComandoPrendaTestDataBuilder().build();

		mockMvc.perform(post("/prenda/crear").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoPrenda))).andExpect(status().isOk());
	}

	@Test
	public void actualizarTest() throws Exception {
		crearTest();
		ComandoPrenda comandoPrenda = new ComandoPrendaTestDataBuilder().build();
		mockMvc.perform(put("/prenda/actualizar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(comandoPrenda)))
				.andExpect(status().isOk());
	}

	@Test
	public void listarTest() throws Exception {
		crearTest();
		mockMvc.perform(get("/prenda")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)));
	}
	
	@Test
	public void eliminarTest() throws Exception {
		crearTest();
		ComandoPrenda comandoPrenda = new ComandoPrendaTestDataBuilder().build();
		mockMvc.perform(delete("/prenda/eliminar/1234433")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(comandoPrenda)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void errorEliminarTest() throws Exception {
		ComandoPrenda comandoPrenda = new ComandoPrendaTestDataBuilder().build();
		mockMvc.perform(delete("/prenda/eliminar/1234433")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(comandoPrenda)))
				.andExpect(status().isOk());
	}
}
