package com.spinning.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarrinhoDTO {

    @NotNull
    private Long produtoId;

    @Min(1)
    private int quantidade;
}
