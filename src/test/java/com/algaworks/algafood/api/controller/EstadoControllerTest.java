package com.algaworks.algafood.api.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.algaworks.algafood.domain.model.Estado;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EstadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testarUsuarioControllerListarUsuariosComSucesso() throws Exception {
        // cenario

        // ação
        String jsonResponse = restTemplate.getForObject("http://api.algafood.local:8080/cozinhas", String.class);
        Estado[] usuarios = objectMapper.readValue(jsonResponse, Estado[].class);


        // assertions
        assertThat(usuarios).isNotEmpty();
        assertThat(jsonResponse).isNotNull();
    }

}