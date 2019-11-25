/**
 * 
 */
package com.ceiba.adn.tienda.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jeison.barbosa
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest()

public class ControllerPrendaTest {
	
	@Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;
}
