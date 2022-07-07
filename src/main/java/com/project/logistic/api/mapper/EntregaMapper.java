package com.project.logistic.api.mapper;

import com.project.logistic.api.dto.EntregaDTO;
import com.project.logistic.api.dto.request.EntregaRequest;
import com.project.logistic.domain.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaMapper {

    private ModelMapper modelMapper; // indica que a classe é um modelo de mapeamento de entrega

    public EntregaDTO toDTO(Entrega entrega) {
        return modelMapper.map(entrega, EntregaDTO.class);
    } // mapeia entrega para dto

    public List<EntregaDTO> toDTO(List<Entrega> entregas) {
        return entregas.stream() // transforma a lista de entregas em uma lista de entregasDTO
                .map(this::toDTO) // chama o método toDTO para cada entrega
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaRequest entregaRequest) { // transforma o input em entrega
        return modelMapper.map(entregaRequest, Entrega.class); // mapeia o input para entrega
    }
}
