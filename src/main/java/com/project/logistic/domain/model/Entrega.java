package com.project.logistic.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity // indica que a classe é uma entidade
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // indica que o atributo é um relacionamento de muitos para um
    private Cliente cliente;

    @Embedded // indica que o atributo é um objeto embutido
    private Destinatario destinatario;

    private BigDecimal taxa;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // indica que o atributo é um atributo somente leitura
    @Enumerated(EnumType.STRING) // indica que o atributo é um enum de string
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataEntrega;

}
