/**
 * 
 */
package com.ceiba.adn.tienda.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.ceiba.adn.tienda.TiendaApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jeison.barbosa
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TiendaApplication.class)
@AutoConfigureMockMvc
@Transactional
public class ControllerPrendaTest {

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
	public void crear() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/prenda").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
