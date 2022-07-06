package com.project.logistic.domain.model;

import com.project.logistic.domain.utils.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity // indica que a classe é uma entidade
public class Cliente {

    @NotNull(groups = ValidationGroups.ClienteId.class)
    @EqualsAndHashCode.Include // indica que o atributo deve ser incluído no cálculo de hashCode
    @Id // indica que o atributo é o id da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) //estratégia de geração de chave primária
    private Long id;

    @NotBlank // indica que o atributo é obrigatório
    @Size(max = 60) // indica que o atributo deve ter um tamanho máximo de 60 caracteres
    private String nome;

    @NotBlank // indica que o atributo é obrigatório
    @Email // indica que o atributo deve ser um email válido
    @Size(max = 255) // indica que o atributo deve ter um tamanho máximo de 255 caracteres
    private String email;

    @NotBlank // indica que o atributo é obrigatório
    @Size(max = 20) // indica que o atributo deve ter um tamanho máximo de 20 caracteres
    private String telefone;

}
