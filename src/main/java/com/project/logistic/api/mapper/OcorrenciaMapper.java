package com.project.logistic.api.mapper;

import com.project.logistic.api.dto.OcorrenciaDTO;
import com.project.logistic.domain.model.Ocorrencia;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {
    private ModelMapper modelMapper;

    public OcorrenciaDTO toDTO(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
    }

    public List<OcorrenciaDTO> toDTO(List<Ocorrencia> ocorrenciaList) {
        return ocorrenciaList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

