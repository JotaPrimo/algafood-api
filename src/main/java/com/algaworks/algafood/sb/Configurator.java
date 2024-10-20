package com.algaworks.algafood.sb;

import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.infrastructure.repository.CozinhaRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configurator {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CozinhaRepository cozinhaRepository() {
        return new CozinhaRepositoryImpl();
    }

    @Bean
    public CadastroCozinhaService cadastroCozinhaService() {
        return new CadastroCozinhaService(cozinhaRepository());
    }

}
