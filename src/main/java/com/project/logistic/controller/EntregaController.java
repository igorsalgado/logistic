package com.project.logistic.controller;


import com.project.logistic.domain.model.Entrega;
import com.project.logistic.domain.service.EntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas") // indica que a classe é um controller de entregas
public class EntregaController {

    private EntregaService entregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // indica que a resposta será criada, retornando o status 201
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return entregaService.solicitarEntrega(entrega); // solicita a entrega
    }
}
