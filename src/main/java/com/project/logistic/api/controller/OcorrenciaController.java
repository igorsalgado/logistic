package com.project.logistic.api.controller;

import com.project.logistic.api.dto.OcorrenciaDTO;
import com.project.logistic.api.dto.request.OcorrenciaRequest;
import com.project.logistic.api.mapper.OcorrenciaMapper;
import com.project.logistic.domain.model.Entrega;
import com.project.logistic.domain.model.Ocorrencia;
import com.project.logistic.domain.service.EntregaService;
import com.project.logistic.domain.service.OcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private OcorrenciaService ocorrenciaService; // indica que a classe é um serviço de ocorrências
    private OcorrenciaMapper ocorrenciaMapper; // indica que a classe é um mapeador de ocorrências
    private EntregaService buscaEntregaService; // indica que a classe é um serviço de busca de entregas

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaDTO registrar(@PathVariable Long entregaId,
                                   @Valid @RequestBody OcorrenciaRequest ocorrenciaRequest) {
        Ocorrencia ocorrenciaRegistrada = ocorrenciaService.buscar(entregaId, ocorrenciaRequest.getDescricao());

        return ocorrenciaMapper.toDTO(ocorrenciaRegistrada); // adiciona e salva ocorrência e retorna 201

    }

    @GetMapping
    public List<OcorrenciaDTO> listar(@PathVariable Long entregaId) {
        Entrega entrega = buscaEntregaService.buscarEntrega(entregaId);
        return ocorrenciaMapper.toDTO(entrega.getOcorrencia());
    }
}
