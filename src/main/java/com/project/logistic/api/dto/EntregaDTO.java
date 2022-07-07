package com.project.logistic.api.dto;

import com.project.logistic.domain.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Getter
@Setter
public class EntregaDTO {

    private Long id;
    private ClienteDTO cliente;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataEntrega;
    private DestinatarioDTO destinatario;
}
