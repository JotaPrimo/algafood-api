package com.algaworks.algafood.sb;

import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configurator {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
