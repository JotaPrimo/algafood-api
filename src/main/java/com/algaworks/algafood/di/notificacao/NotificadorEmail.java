package com.algaworks.algafood.di.notificacao;

import com.algaworks.algafood.di.modelo.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

// essa anotação faz com que o spring gerencia estado e injeta esse componente
// instancia e configura

@Profile("prod")
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {

    public NotificadorEmail() {
        System.out.println("PROD : NotificadorEmail");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do e-mail %s : %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}