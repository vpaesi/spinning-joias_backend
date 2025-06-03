package com.spinning.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank
    @Size(min = 10, max = 1000, message = "A descrição deve ter entre 10 e 1000 caracteres")
    private String descricao;

    @NotNull
    @Positive(message = "O preço deve ser um valor positivo")
    private Double preco;

    @NotBlank
    @Size(min = 2, max = 50, message = "A categoria deve ter entre 2 e 50 caracteres")
    private String categoria;

    @NotBlank
    @Size(min = 5, max = 255, message = "A imagem deve conter o caminho ou URL")
    private String imagem;

    @Size(max = 2000, message = "As informações extras devem ter no máximo 2000 caracteres")
    private String informacoesExtras;
}
