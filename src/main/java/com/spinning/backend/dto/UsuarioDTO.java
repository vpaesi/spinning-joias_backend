package com.spinning.backend.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import java.time.LocalDate;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String nome;

    @NotBlank
    @CPF
    @Size(min = 11, max = 14)
    private String cpf;

    @NotBlank
    @Email
    @Size(min = 5, max = 50)
    private String email;

    @NotBlank
    @Size(min = 10, max = 15)
    private String telefone;

    @NotBlank
    @Size(min = 6, max = 20)
    private String senha;

    private LocalDate dataDeNascimento;

    @NotBlank
    @Size(min = 8, max = 10)
    private String cep;

    @NotBlank
    @Size(min = 2, max = 2)
    private String estado;

    @NotBlank
    @Size(min = 2, max = 50)
    private String cidade;

    @NotBlank
    @Size(min = 2, max = 50)
    private String bairro;

    @NotBlank
    @Size(min = 2, max = 50)
    private String logradouro;

    @NotBlank
    @Size(min = 1, max = 10)
    private String numero;

    @Size(max = 50)
    private String complemento;
}
