package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import utils.HttpUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @Sql(scripts = "/sql/restaurantes/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
// @Sql(scripts = "/sql/restaurantes/truncate.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class RestauranteControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final String URL = "http://api.algafood.local:8080/restaurantes";

    @Test
    void testListarRestaurantes_deveRetornarSuccesso_status200() {
        // deve retornar uma listagem json com os recurso de restaurantes cadastrados
        // cenario
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = HttpUtils.getHttpHeaders();

        // ação
        String jsonResponse = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class).getBody();

        // assertion
        assertThat(jsonResponse).isNotNull();
        assertThat(jsonResponse).isNotEmpty();
    }

    @Test
    void testBuscarRestaurante_deveRetornarSuccesso_status200() throws JsonProcessingException {
        // cenario
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = HttpUtils.getHttpHeaders();

        // ação
        ResponseEntity<String> jsonResponse = restTemplate.exchange(URL + "/1", HttpMethod.GET, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restaurante = objectMapper.readValue(jsonResponse.getBody(), Restaurante.class);

        // assertion
        assertThat(restaurante);
        assertThat(restaurante).isNotNull();
        assertThat(jsonResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testBuscarRestauranteInexistente_deveRetornarNotFound_status404() {
        // cenário
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = HttpUtils.getHttpHeaders();

        // ação
        Throwable thrown = catchThrowable(() -> {
            restTemplate.exchange(URL + "/0", HttpMethod.GET, entity, String.class);
        });

        // assertions
        assertThat(thrown).isInstanceOf(HttpClientErrorException.NotFound.class);  // Verifica se a exceção é 404
        if (thrown instanceof HttpClientErrorException) {
            HttpClientErrorException ex = (HttpClientErrorException) thrown;
            assertThat(ex.getStatusCode().value()).isEqualTo(404);  // Verifica se o status code é 404
        }
    }

    @Test
    void testAdicionarRestauranteComSuccesso_deveRetornarStatus201() throws JsonProcessingException {
        // cenario
        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        cozinha.setNome("Chilena");

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Los Chilenos");
        restaurante.setTaxaFrete(new BigDecimal(50));
        restaurante.setCozinha(cozinha);

        HttpEntity<String> entity = HttpUtils.getHttpHeaders();

        // ação
        ResponseEntity<String> jsonResponse = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().string("User created successfully"));
        Restaurante restauranteMapped = objectMapper.readValue(jsonResponse.getBody(), Restaurante.class);
        JsonNode jsonNode = objectMapper.readTree(jsonResponse.getBody());

        assertThat(jsonResponse.getBody()).isNotEmpty();
        assertThat(jsonResponse.getBody()).isNotNull();
        assertThat(jsonNode.has("nome")).isEqualTo("Los Chilenos");
    }
}