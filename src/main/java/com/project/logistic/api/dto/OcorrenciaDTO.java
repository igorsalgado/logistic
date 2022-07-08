package com.project.logistic.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OcorrenciaDTO {

    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;
}
