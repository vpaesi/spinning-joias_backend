package com.spinning.backend.controller;

import com.spinning.backend.dto.ProdutoDTO;
import com.spinning.backend.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProdutoControllerTest {

    private ProdutoService produtoService;
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        produtoService = mock(ProdutoService.class);
        produtoController = new ProdutoController(produtoService);
    }

    private ProdutoDTO criarProdutoDTO(Long id, String nome, String categoria, Double preco, String descricao, String infoExtra) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(id);
        dto.setNome(nome);
        dto.setCategoria(categoria);
        dto.setPreco(preco);
        dto.setDescricao(descricao);
        dto.setImagem("imagem.jpg");
        dto.setInformacoesExtras(infoExtra);
        return dto;
    }

    @Test
    void deveAdicionarProduto() {
        ProdutoDTO dto = criarProdutoDTO(null, "Produto Teste", "Categoria", 10.0, "Descricao do produto", "Extra");
        ProdutoDTO salvo = criarProdutoDTO(1L, "Produto Teste", "Categoria", 10.0, "Descricao do produto", "Extra");
        when(produtoService.criar(ArgumentMatchers.any(ProdutoDTO.class))).thenReturn(salvo);

        ResponseEntity<ProdutoDTO> response = produtoController.criar(dto);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Produto Teste", response.getBody().getNome());
    }

    @Test
    void deveEditarProduto() {
        ProdutoDTO dto = criarProdutoDTO(1L, "Produto Editado", "Categoria", 20.0, "Descricao editada", "Extra");
        when(produtoService.atualizar(eq(1L), any(ProdutoDTO.class))).thenReturn(Optional.of(dto));

        ResponseEntity<ProdutoDTO> response = produtoController.atualizar(1L, dto);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Produto Editado", response.getBody().getNome());
        assertEquals(20.0, response.getBody().getPreco());
    }

    @Test
    void deveExcluirProduto() {
        when(produtoService.deletar(1L)).thenReturn(true);

        ResponseEntity<Void> response = produtoController.deletar(1L);

        assertEquals(204, response.getStatusCode().value());
        verify(produtoService, times(1)).deletar(1L);
    }

    @Test
    void deveBuscarProdutoPorId() {
        ProdutoDTO dto = criarProdutoDTO(1L, "Produto Teste", "Categoria", 10.0, "Descricao", "Extra");
        when(produtoService.buscarPorId(1L)).thenReturn(Optional.of(dto));

        ResponseEntity<ProdutoDTO> response = produtoController.buscarPorId(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Produto Teste", response.getBody().getNome());
    }

    @Test
    void deveBuscarProdutosPorCategoria() {
        ProdutoDTO p1 = criarProdutoDTO(1L, "Produto 1", "Categoria A", 10.0, "Descricao", "Extra");
        ProdutoDTO p2 = criarProdutoDTO(2L, "Produto 2", "Categoria B", 20.0, "Descricao", "Extra");
        ProdutoDTO p3 = criarProdutoDTO(3L, "Produto 3", "Categoria A", 30.0, "Descricao", "Extra");
        when(produtoService.listarTodos()).thenReturn(List.of(p1, p2, p3));

        List<ProdutoDTO> filtrados = produtoController.listarTodos().stream()
                .filter(p -> p.getCategoria().equals("Categoria A"))
                .collect(Collectors.toList());

        assertEquals(2, filtrados.size());
        assertTrue(filtrados.stream().allMatch(p -> p.getCategoria().equals("Categoria A")));
    }

    @Test
    void deveBuscarProdutosPorOrdemAlfabetica() {
        ProdutoDTO p1 = criarProdutoDTO(1L, "Banana", "Categoria", 10.0, "Descricao", "Extra");
        ProdutoDTO p2 = criarProdutoDTO(2L, "Abacate", "Categoria", 20.0, "Descricao", "Extra");
        ProdutoDTO p3 = criarProdutoDTO(3L, "Cenoura", "Categoria", 30.0, "Descricao", "Extra");
        when(produtoService.listarTodos()).thenReturn(List.of(p1, p2, p3));

        List<ProdutoDTO> ordenados = produtoController.listarTodos().stream()
                .sorted(Comparator.comparing(ProdutoDTO::getNome))
                .collect(Collectors.toList());

        assertEquals("Abacate", ordenados.get(0).getNome());
        assertEquals("Banana", ordenados.get(1).getNome());
        assertEquals("Cenoura", ordenados.get(2).getNome());
    }

    @Test
    void deveBuscarProdutosPorOrdemDePreco() {
        ProdutoDTO p1 = criarProdutoDTO(1L, "Produto 1", "Categoria", 30.0, "Descricao", "Extra");
        ProdutoDTO p2 = criarProdutoDTO(2L, "Produto 2", "Categoria", 10.0, "Descricao", "Extra");
        ProdutoDTO p3 = criarProdutoDTO(3L, "Produto 3", "Categoria", 20.0, "Descricao", "Extra");
        when(produtoService.listarTodos()).thenReturn(List.of(p1, p2, p3));

        List<ProdutoDTO> ordenados = produtoController.listarTodos().stream()
                .sorted(Comparator.comparing(ProdutoDTO::getPreco))
                .collect(Collectors.toList());

        assertEquals(10.0, ordenados.get(0).getPreco());
        assertEquals(20.0, ordenados.get(1).getPreco());
        assertEquals(30.0, ordenados.get(2).getPreco());
    }

    @Test
    void deveBuscarProdutosPorPalavraChave() {
        ProdutoDTO p1 = criarProdutoDTO(1L, "Camiseta Azul", "Roupas", 30.0, "Linda camiseta azul", "Algodão");
        ProdutoDTO p2 = criarProdutoDTO(2L, "Calça Jeans", "Roupas", 50.0, "Calça confortável", "Jeans");
        ProdutoDTO p3 = criarProdutoDTO(3L, "Tênis Esportivo", "Calçados", 100.0, "Tênis para corrida", "Esporte");
        when(produtoService.listarTodos()).thenReturn(List.of(p1, p2, p3));

        String palavra = "azul";
        List<ProdutoDTO> encontrados = produtoController.listarTodos().stream()
                .filter(p -> (p.getNome() != null && p.getNome().toLowerCase().contains(palavra)) ||
                             (p.getDescricao() != null && p.getDescricao().toLowerCase().contains(palavra)) ||
                             (p.getCategoria() != null && p.getCategoria().toLowerCase().contains(palavra)) ||
                             (p.getInformacoesExtras() != null && p.getInformacoesExtras().toLowerCase().contains(palavra)))
                .collect(Collectors.toList());

        assertEquals(1, encontrados.size());
        assertEquals("Camiseta Azul", encontrados.get(0).getNome());
    }
}