package com.spinning.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String cpf;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String telefone;

    @NotBlank
    @Column(nullable = false)
    private String senha;

    @Column
    private LocalDate dataDeNascimento;

    @NotBlank
    @Column(nullable = false)
    private String cep;

    @NotBlank
    @Column(nullable = false)
    private String estado;

    @NotBlank
    @Column(nullable = false)
    private String cidade;

    @NotBlank
    @Column(nullable = false)
    private String bairro;

    @NotBlank
    @Column(nullable = false)
    private String logradouro;

    @NotBlank
    @Column(nullable = false)
    private String numero;

    @Column
    private String complemento;
}
