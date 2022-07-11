package com.project.logistic.domain.service;

import com.project.logistic.domain.exception.EntidadeNaoEncontradaException;
import com.project.logistic.domain.model.Cliente;
import com.project.logistic.domain.model.Entrega;
import com.project.logistic.domain.model.StatusEntrega;
import com.project.logistic.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginException;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class EntregaService {
    private EntregaRepository entregaRepository;
    private ClienteService clienteService;

    @Transactional
    public Entrega solicitarEntrega(Entrega entrega) { // solicita uma entrega
        Cliente cliente = clienteService.buscar(entrega.getCliente().getId()); // verifica se o cliente existe

        entrega.setCliente(cliente); // seta o cliente
        entrega.setStatus(StatusEntrega.PENDENTE); // seta o status da entrega como aguardando
        entrega.setDataPedido(OffsetDateTime.now()); // seta a data do pedido

        return entregaRepository.save(entrega);
    }

    @Transactional
    public Entrega buscarEntrega(Long entregaId) {
        return entregaRepository.findById(entregaId) // busca a entrega pelo id
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada")); // lança exceção se não encontrar a entrega
    }

    @Transactional
    public Entrega finalizarEntrega(Long entregaId) throws LoginException {

        Entrega entrega = buscarEntrega(entregaId); // busca a entrega pelo id
        entrega.finalizar(); // finaliza a entrega
        entrega.setStatus(StatusEntrega.FINALIZADA); // seta o status da entrega como entregue
        entrega.setDataEntrega(OffsetDateTime.now()); // seta a data de entrega
        return entregaRepository.save(entrega); // salva a entrega

    }
}


