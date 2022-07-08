package com.project.logistic.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity // indica que a classe é uma entidade
public class Ocorrencia {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //estratégia de geração de chave primária
    private Long id;

    @ManyToOne
    private Entrega entrega;
    private String descricao;
    private OffsetDateTime dataRegistro ;
}
