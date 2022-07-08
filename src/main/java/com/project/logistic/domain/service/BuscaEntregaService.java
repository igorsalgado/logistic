package com.project.logistic.domain.service;

import com.project.logistic.domain.exception.EntidadeNaoEncontradaException;
import com.project.logistic.domain.model.Entrega;
import com.project.logistic.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private EntregaRepository entregaRepository;

    public Entrega buscarEntrega(Long entregaId) {
        return entregaRepository.findById(entregaId) // busca a entrega pelo id
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada")); // lança exceção se não encontrar a entrega
    }

}
