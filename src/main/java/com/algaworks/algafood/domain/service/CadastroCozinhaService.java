package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exeptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exeptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Service
public class CadastroCozinhaService {

    private final CozinhaRepository cozinhaRepository;

    @Autowired
    public CadastroCozinhaService(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }

    public Cozinha salvar(Cozinha cozinha) {
        return this.cozinhaRepository.salvar(cozinha);
    }

    public void excluir(Long idCozinha) {
        try {
            this.cozinhaRepository.remover(idCozinha);
        }catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de id %d não pode ser removida por estar em uso", idCozinha)
            );
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Cozinha de id %d não encontrada", idCozinha)
            );
        }
    }

    public Cozinha buscar(Long id) {
        return cozinhaRepository.buscar(id);
    }
}
