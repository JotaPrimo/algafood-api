package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    private final CozinhaRepository cozinhaRepository;

    private final CadastroCozinhaService cadastroCozinha;

    @Autowired
    public CozinhaController(CozinhaRepository cozinhaRepository, CadastroCozinhaService cadastroCozinha) {
        this.cozinhaRepository = cozinhaRepository;
        this.cadastroCozinha = cadastroCozinha;
    }

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {

        Cozinha cozinha = cozinhaRepository.buscar(id);

        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        Cozinha cozinhaSave = cadastroCozinha.salvar(cozinha);

        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {

        Cozinha cozinhaAtual = cozinhaRepository.buscar(id);
        // agora passar os valores do update para o registro encontrado
        // isto seta todas as propriedades de um para outro

        if (cozinhaAtual != null) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            cozinhaRepository.salvar(cozinhaAtual);
            return ResponseEntity.ok(cozinha);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Cozinha> cozinhaOptional = Optional.ofNullable(cozinhaRepository.buscar(id));

        if (cozinhaOptional.isPresent()) {
            cozinhaRepository.remover(cozinhaOptional.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
