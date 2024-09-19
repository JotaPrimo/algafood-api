package com.algaworks.algafood.controllers;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.service.AtivacaoClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// a anotação controller tambem torna um bean
@Controller
public class MeuPrimeiroController {

    private final AtivacaoClienteService ativacaoClienteService;

    public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;
        System.out.println("MeuPrimeiroController " + ativacaoClienteService);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        Cliente cliente = new Cliente("Jailson Santos", "jotasantos@gmail.com", "(67)984731865", false);

        ativacaoClienteService.ativar(cliente);
        return "Hello";
    }
}
