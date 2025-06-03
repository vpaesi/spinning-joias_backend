package com.spinning.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false, length = 1000)
    private String descricao;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Double preco;

    @NotBlank
    @Column(nullable = false)
    private String categoria;

    @NotBlank
    @Column(nullable = false)
    private String imagem; // Pode ser uma URL ou nome do arquivo

    @Column(nullable = true, length = 2000)
    private String informacoesExtras;
}
