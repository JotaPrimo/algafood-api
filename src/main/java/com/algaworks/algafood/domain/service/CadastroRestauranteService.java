package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroRestauranteService {

    private final RestauranteRepository restauranteRepository;

    public CadastroRestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public List<Restaurante> getAll() {
        return restauranteRepository.listar();
    }

    public Restaurante buscarPorId(Long id) {
        return restauranteRepository.buscar(id);
    }
}
