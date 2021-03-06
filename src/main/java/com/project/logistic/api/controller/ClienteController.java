package com.project.logistic.api.controller;

import com.project.logistic.domain.service.ClienteService;
import com.project.logistic.domain.model.Cliente;
import com.project.logistic.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor // indica que a classe é um contrutor com todos os argumentos
@RestController // indica que a classe é um controlador REST
@RequestMapping("/clientes") // indica que a classe é um controlador REST
public class ClienteController {

    private ClienteRepository clienteRepository; // indica que a classe é um repositório de clientes
    private ClienteService clienteService; // indica que a classe é um serviço de clientes

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
    @ResponseStatus(HttpStatus.CREATED) // indica que o método retorna um status 201
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return clienteService.salvar(cliente); // adiciona e salva cliente e retorna 201
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build(); // retorna 404 se o cliente não existir
        }

        cliente.setId(clienteId); // atualiza o id do cliente
        cliente = clienteService.salvar(cliente); // salva o cliente
        return ResponseEntity.ok(cliente); // retorna o cliente atualizado

    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build(); // retorna 404 se o cliente não existir
        }

        clienteService.remover(clienteId); // remove o cliente
        return ResponseEntity.noContent().build(); // retorna 204
    }

}
