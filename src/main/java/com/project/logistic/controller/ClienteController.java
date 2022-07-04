package com.project.logistic.controller;

import com.project.logistic.domain.model.Cliente;
import com.project.logistic.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor // indica que a classe é um contrutor com todos os argumentos
@RestController // indica que a classe é um controlador REST
@RequestMapping("/clientes") // indica que a classe é um controlador REST
public class ClienteController {

    private ClienteRepository clienteRepository; // indica que a classe é um repositório de clientes

    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll(); // retorna todos os clientes
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // retorna o cliente se existir, senão retorna 404
    }

    @PostMapping
    public Cliente adicionar(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
