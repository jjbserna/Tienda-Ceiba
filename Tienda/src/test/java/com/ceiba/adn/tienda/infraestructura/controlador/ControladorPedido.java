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
import com.ceiba.adn.tienda.aplicacion.comando.ComandoPedidoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TiendaApplication.class)
@AutoConfigureMockMvc
@Transactional
public class ControladorPedido {
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
	}
	@Test
	public void eliminarTest() throws Exception {
		crearTest();
		ComandoPedido comandoPedido = new ComandoPedidoTestDataBuilder().build();
		mockMvc.perform(delete("/pedido/eliminar/1001")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(comandoPedido)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void listarTest() throws Exception {
		crearTest();
		mockMvc.perform(get("/pedido")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)));
	}
}
