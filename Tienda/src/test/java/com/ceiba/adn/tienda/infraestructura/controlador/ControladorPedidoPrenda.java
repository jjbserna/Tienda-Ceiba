/**
 * 
 */
package com.ceiba.adn.tienda.infraestructura.controlador;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.ceiba.adn.tienda.aplicacion.comando.ComandoCliente;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoClienteTestDataBuilder;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedido;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrenda;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoPrendaTestDataBuilder;
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoTestDataBuilder;
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
public class ControladorPedidoPrenda {
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
		
		ComandoCliente comandoCliente=new ComandoClienteTestDataBuilder().build();
		mockMvc.perform(post("/cliente/crear").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoCliente))).andExpect(status().isOk());
		
		ComandoPedido comandoPedido=new ComandoPedidoTestDataBuilder().build();
		mockMvc.perform(post("/pedido/crear").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoPedido))).andExpect(status().isOk());
		
		ComandoPrenda comandoPrenda= new ComandoPrendaTestDataBuilder().build();
		mockMvc.perform(post("/prenda/crear").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoPrenda))).andExpect(status().isOk());
		
		ComandoPedidoPrenda comandoPedidoPrenda = new ComandoPedidoPrendaTestDataBuilder().build();
		mockMvc.perform(post("/pedidoprenda/crear").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoPedidoPrenda))).andExpect(status().isOk());
	}
	
	@Test
	public void listarTest() throws Exception {
		crearTest();
		mockMvc.perform(get("/pedidoprenda/1001")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)));
	}
	
	@Test
	public void eliminarTest() throws Exception {
		crearTest();
		ComandoPedidoPrenda comandoPedidoPrenda = new ComandoPedidoPrendaTestDataBuilder().build();
		mockMvc.perform(delete("/pedido/eliminar/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(comandoPedidoPrenda)))
				.andExpect(status().isOk());
	}
}
