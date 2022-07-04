package com.project.logistic.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity // indica que a classe é uma entidade
public class Cliente {

    @EqualsAndHashCode.Include // indica que o atributo deve ser incluído no cálculo de hashCode
    @Id // indica que o atributo é o id da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) //estratégia de geração de chave primária
    private Long id;

    private String nome;
    private String email;
    private String telefone;

}
