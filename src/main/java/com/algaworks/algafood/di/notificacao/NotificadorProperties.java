package com.algaworks.algafood.di.notificacao;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
public class NotificadorProperties {
    private String hostServidor;
    private Integer portaServidor;

    @PostConstruct
    public void init() {
        System.out.println("hostServidor : " + getHostServidor());
        System.out.println("portaServidor : " + getPortaServidor());
    }

    public String getHostServidor() {
        return hostServidor;
    }

    public void setHostServidor(String hostServidor) {
        this.hostServidor = hostServidor;
    }

    public Integer getPortaServidor() {
        return portaServidor;
    }

    public void setPortaServidor(Integer portaServidor) {
        this.portaServidor = portaServidor;
    }
}
