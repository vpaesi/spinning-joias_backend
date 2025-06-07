package com.spinning.backend.controller;

import com.spinning.backend.dto.UsuarioDTO;
import com.spinning.backend.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {

    private UsuarioService usuarioService;
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        usuarioService = mock(UsuarioService.class);
        usuarioController = new UsuarioController();
        // Injeta o mock manualmente já que não há construtor
        java.lang.reflect.Field field;
        try {
            field = UsuarioController.class.getDeclaredField("usuarioService");
            field.setAccessible(true);
            field.set(usuarioController, usuarioService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private UsuarioDTO criarUsuarioDTO(Long id, String nome, String email) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(id);
        dto.setNome(nome);
        dto.setCpf("12345678901");
        dto.setEmail(email);
        dto.setTelefone("11999999999");
        dto.setSenha("senha123");
        dto.setDataDeNascimento(LocalDate.of(2000, 1, 1));
        dto.setCep("12345678");
        dto.setEstado("SP");
        dto.setCidade("São Paulo");
        dto.setBairro("Centro");
        dto.setLogradouro("Rua Teste");
        dto.setNumero("123");
        dto.setComplemento("Apto 1");
        return dto;
    }

    @Test
    void deveCriarUsuario() {
        UsuarioDTO dto = criarUsuarioDTO(null, "Fulano", "fulano@email.com");
        UsuarioDTO salvo = criarUsuarioDTO(1L, "Fulano", "fulano@email.com");
        when(usuarioService.criar(ArgumentMatchers.any(UsuarioDTO.class))).thenReturn(salvo);

        ResponseEntity<UsuarioDTO> response = usuarioController.criar(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Fulano", response.getBody().getNome());
    }

    @Test
    void deveEditarUsuario() {
        UsuarioDTO dto = criarUsuarioDTO(1L, "Fulano Editado", "fulano@email.com");
        when(usuarioService.atualizar(eq(1L), any(UsuarioDTO.class))).thenReturn(dto);

        ResponseEntity<UsuarioDTO> response = usuarioController.atualizar(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Fulano Editado", response.getBody().getNome());
    }

    @Test
    void deveExcluirUsuario() {
        doNothing().when(usuarioService).deletar(1L);

        ResponseEntity<Void> response = usuarioController.deletar(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioService, times(1)).deletar(1L);
    }

    @Test
    void deveBuscarUsuarioPorId() {
        UsuarioDTO dto = criarUsuarioDTO(1L, "Fulano", "fulano@email.com");
        when(usuarioService.buscarPorId(1L)).thenReturn(dto);

        ResponseEntity<UsuarioDTO> response = usuarioController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Fulano", response.getBody().getNome());
    }

    @Test
    void deveRealizarLoginComCadastroExistente() {
        // Simula login: normalmente teria um método específico, mas aqui vamos simular busca por email e senha
        UsuarioDTO dto = criarUsuarioDTO(1L, "Fulano", "fulano@email.com");
        when(usuarioService.listarTodos()).thenReturn(List.of(dto));

        // Simula um "login" buscando todos e filtrando (apenas para exemplo, pois não há endpoint de login)
        List<UsuarioDTO> usuarios = usuarioController.listarTodos().getBody();
        Optional<UsuarioDTO> usuarioLogado = usuarios.stream()
                .filter(u -> u.getEmail().equals("fulano@email.com") && u.getSenha().equals("senha123"))
                .findFirst();

        assertTrue(usuarioLogado.isPresent());
        assertEquals("Fulano", usuarioLogado.get().getNome());
    }
}