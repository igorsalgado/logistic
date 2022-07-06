package com.project.logistic.controller;


import com.project.logistic.domain.model.Entrega;
import com.project.logistic.domain.repository.EntregaRepository;
import com.project.logistic.domain.service.EntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas") // indica que a classe é um controller de entregas
public class EntregaController {

    private EntregaService entregaService;
    private EntregaRepository entregaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // indica que a resposta será criada, retornando o status 201
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return entregaService.solicitarEntrega(entrega); // solicita a entrega
    }

    @GetMapping
    public List<Entrega> listar(){
        return entregaRepository.findAll(); // lista todas as entregas
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<Entrega> buscar(@PathVariable Long entregaId){
        return entregaRepository.findById(entregaId) // busca a entrega pelo id
                .map(ResponseEntity::ok) // caso a entrega exista, retorna o status 200
                .orElse(ResponseEntity.notFound().build()); // retorna 404 se não encontrar o id
    }
}
