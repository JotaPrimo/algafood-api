package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exeptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroRestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;

    public CadastroRestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository cozinhaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
    }

    public List<Restaurante> getAll() {
        return restauranteRepository.listar();
    }

    public Restaurante buscarPorId(Long id) {
        return restauranteRepository.buscar(id);
    }

    public Restaurante buscarOuFalhar(Long id) {
        Restaurante restaurante = restauranteRepository.buscar(id);

        if (restaurante == null) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante de id %d não encontrado", id));
        }

        return restaurante;
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de id %d não existe", cozinhaId));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.salvar(restaurante);
    }

    public Restaurante atualizar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha == null) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de id %d não existe", cozinhaId));
        }

        restaurante.setCozinha(cozinha);

        return restauranteRepository.salvar(restaurante);
    }
}





























