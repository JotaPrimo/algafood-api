package com.algaworks.algafood.listener;

import com.algaworks.algafood.di.service.ClienteAtivadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        // cada nova ação que precise preciso apenas criar um novo evento
        System.out.println("Emitindo nota fiscal para cliente " + event.getCliente().getNome());
    }
}
