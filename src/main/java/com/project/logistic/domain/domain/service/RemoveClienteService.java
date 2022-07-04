package com.project.logistic.domain.domain.service;

import com.project.logistic.domain.model.Cliente;
import com.project.logistic.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RemoveClienteService { // indica que a classe é um serviço de cadastro de clientes

    private ClienteRepository clienteRepository;

    @Transactional
    public void remover(Long ClienteId) {
        clienteRepository.deleteById(ClienteId);
    } // remove o cliente
}
