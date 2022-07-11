package com.project.logistic.api.controller;


import com.project.logistic.api.dto.EntregaDTO;
import com.project.logistic.api.dto.request.EntregaRequest;
import com.project.logistic.api.mapper.EntregaMapper;
import com.project.logistic.domain.model.Entrega;
import com.project.logistic.domain.repository.EntregaRepository;
import com.project.logistic.domain.service.EntregaService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas") // indica que a classe é um controller de entregas
public class EntregaController {

    private EntregaService entregaService; // indica que a classe é um serviço de entregas
    private EntregaRepository entregaRepository; // indica que a classe é um repositório de entregas
    private EntregaMapper entregaMapper; // indica que a classe é um mapeador de entregas

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // indica que a resposta será criada, retornando o status 201
    public EntregaDTO solicitar(@Valid @RequestBody EntregaRequest entregaRequest) { // indica que o método recebe um objeto de entrada de entrega
        Entrega novaEntrega = entregaMapper.toEntity(entregaRequest); // mapeia o input para entrega
        Entrega entregaSolicitada = entregaService.solicitarEntrega(novaEntrega); // solicita a entrega
        return entregaMapper.toDTO(entregaSolicitada); // adiciona e salva entrega e retorna 201
    }

    @GetMapping
    public List<EntregaDTO> listar(){
        return entregaMapper.toDTO(entregaRepository.findAll()); // lista todas as entregas
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaDTO> buscar(@PathVariable Long entregaId){ // indica que o método recebe um id de entrega
        return entregaRepository.findById(entregaId) // busca a entrega pelo id
                .map(entrega -> ResponseEntity.ok(entregaMapper.toDTO(entrega))) // retorna a entrega encontrada
                .orElse(ResponseEntity.notFound().build()); // retorna 404 se não encontrar
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT) // indica que a resposta será nula, retornando o status 204
    public void finalizar(@PathVariable Long entregaId) throws LoginException {
        entregaService.finalizarEntrega(entregaId); // finaliza a entrega
    }
}
