package com.project.logistic.domain.service;

import com.project.logistic.domain.model.Cliente;
import com.project.logistic.domain.model.Entrega;
import com.project.logistic.domain.model.StatusEntrega;
import com.project.logistic.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class EntregaService {
    private EntregaRepository entregaRepository;
    private ClienteService clienteService;

    @Transactional
    public Entrega solicitarEntrega(Entrega entrega) {
        Cliente cliente = clienteService.buscar(entrega.getCliente().getId()); // verifica se o cliente existe

        entrega.setCliente(cliente); // seta o cliente
        entrega.setStatus(StatusEntrega.AGUARDANDO); // seta o status da entrega como aguardando
        entrega.setDataPedido(OffsetDateTime.now()); // seta a data do pedido

        return entregaRepository.save(entrega);
    }


}
