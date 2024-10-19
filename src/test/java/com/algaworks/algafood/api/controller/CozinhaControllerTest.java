package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Estado;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import utils.HttpUtils;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CozinhaControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final String URL = "http://api.algafood.local:8080/cozinhas";

    @Test
    void testListarTodasCozinhas_ComStatusSucesso() throws JsonProcessingException {
        // cenario

        // ação
        String jsonResponse = restTemplate.getForObject("http://api.algafood.local:8080/cozinhas", String.class);
        Cozinha[] cozinhas = objectMapper.readValue(jsonResponse, Cozinha[].class);
        List<Cozinha> cozinhasList = Arrays.asList(cozinhas);

        // asserção
        Assertions.assertNotNull(jsonResponse, "A resposta JSON não deveria ser nula.");
        Assertions.assertNotNull(cozinhas);
        assertThat(jsonResponse).isNotEmpty();
        assertThat(cozinhasList.get(0).getNome()).isEqualTo("Tailandesa");
    }

    @Test
    void testFindCozinha_ComStatusSucesso() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> entity = HttpUtils.getHttpHeaders();

        // Fazer a requisição com os cabeçalhos personalizados
        String jsonResponse = restTemplate.exchange(URL + "/2", HttpMethod.GET, entity, String.class).getBody();

        // Converter para o objeto desejado
        Cozinha cozinha = objectMapper.readValue(jsonResponse, Cozinha.class);

        // assertions
        assertThat(jsonResponse).isNotEmpty();
        assertNotNull(jsonResponse, "Não pode ser null a resposta");
        assertThat(cozinha.getNome()).isEqualTo("Indiana");
        assertThat(cozinha.getId()).isEqualTo(2);
    }
}