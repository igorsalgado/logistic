package com.project.logistic.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class ClienteIdRequest {

    @NotNull
    private Long id;
}
