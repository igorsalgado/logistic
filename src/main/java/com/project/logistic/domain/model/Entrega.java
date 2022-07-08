package com.project.logistic.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.logistic.domain.utils.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity // indica que a classe é uma entidade
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class) // indica que o atributo ClienteId deve ser validado pelo grupo ClienteId
    @ManyToOne // indica que o atributo é um relacionamento de muitos para um
    @NotNull // indica que o atributo é obrigatório
    private Cliente cliente;

    @Valid
    @NotNull
    @Embedded // indica que o atributo é um objeto embutido
    private Destinatario destinatario;

    @NotNull
    private BigDecimal taxa;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL) // indica que o atributo é um relacionamento de um para muitos
    private List<Ocorrencia> ocorrencia = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // indica que o atributo é um atributo somente leitura
    @Enumerated(EnumType.STRING) // indica que o atributo é um enum de string
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataEntrega;

    public Ocorrencia registrarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);

        this.getOcorrencia().add(ocorrencia);

        return ocorrencia;

    }
}
