package com.project.logistic.domain.service;

import com.project.logistic.domain.model.Entrega;
import com.project.logistic.domain.model.Ocorrencia;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@AllArgsConstructor
@Service
public class OcorrenciaService {

    private EntregaService buscaEntregaService;

    @Transactional // indica que o método é transacionado
    public Ocorrencia buscar(Long entregaId, String descricao) {

        Entrega entrega = buscaEntregaService.buscarEntrega(entregaId);

        return entrega.registrarOcorrencia(descricao);
    }

}
