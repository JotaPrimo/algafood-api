package com.algaworks.algafood.di.config;

import com.algaworks.algafood.di.notificacao.NotificadorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgaFoodConfigurator {

    @Bean
    public NotificadorProperties notificadorProperties() {
        System.out.println("NotificadorProperties AlgaFoodConfigurator");
        return new NotificadorProperties();
    }
}
