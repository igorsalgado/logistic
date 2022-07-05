package com.project.logistic.domain.service;

import com.project.logistic.domain.exception.LogisticExceptions;
import com.project.logistic.domain.model.Cliente;
import com.project.logistic.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ClienteService { // indica que a classe é um serviço de cadastro de clientes

    private ClienteRepository clienteRepository;

    // verifica se o cliente existe
    public Cliente buscar(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new LogisticExceptions("Cliente não encontrado"));
    }

    @Transactional // indica que o método é transacionado
    public Cliente salvar(Cliente cliente) {

        //Verifica se o Email está em uso, caso esteja, retorna um erro
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()// retorna um fluxo de clientes
                .anyMatch(emailExistente -> !emailExistente.equals(cliente)); // verifica se o email já está em uso
        if (emailEmUso) {
            throw new LogisticExceptions("Email já está em uso"); // lança exceção se o email já estiver em uso
        }


        return clienteRepository.save(cliente);
    } // salva o cliente e retorna o cliente salvo

    @Transactional
    public void remover(Long ClienteId) {
        clienteRepository.deleteById(ClienteId);
    } // remove o cliente
}
