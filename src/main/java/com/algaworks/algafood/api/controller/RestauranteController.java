package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exeptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final CadastroRestauranteService service;
    private final CadastroCozinhaService cozinhaService;

    public RestauranteController(CadastroRestauranteService service, CadastroCozinhaService cozinhaService) {
        this.service = service;
        this.cozinhaService = cozinhaService;
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar() {
        List<Restaurante> restauranteList = service.getAll();
        return ResponseEntity.ok(restauranteList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        Restaurante restaurante = service.buscarPorId(id);
        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteSaved = service.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSaved);
        }catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
