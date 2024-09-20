package com.algaworks.algafood.di.notificacao;

import com.algaworks.algafood.di.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

// essa anotação faz com que o spring gerencia estado e injeta esse componente
// instancia e configura

@Profile("prod")
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {

    private final NotificadorProperties notificadorProperties;

    @Autowired
    public NotificadorEmail(NotificadorProperties notificadorProperties) {
        this.notificadorProperties = notificadorProperties;
        System.out.println("PROD : NotificadorEmail");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do e-mail %s : %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}