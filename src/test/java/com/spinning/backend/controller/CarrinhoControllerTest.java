package com.spinning.backend.controller;

import com.spinning.backend.dto.CarrinhoDTO;
import com.spinning.backend.dto.ProdutoDTO;
import com.spinning.backend.model.Carrinho;
import com.spinning.backend.service.ProdutoService;
import com.spinning.backend.dto.CarrinhoResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarrinhoControllerTest {

    private CarrinhoController carrinhoController;
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        produtoService = mock(ProdutoService.class);
        carrinhoController = new CarrinhoController(produtoService);
    }

    private ProdutoDTO criarProdutoDTO(Long id, String nome, double preco) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(id);
        dto.setNome(nome);
        dto.setPreco(preco);
        return dto;
    }

    @Test
    void deveRetornaInformacoesDoCarrinhoComProduto() {
        ProdutoDTO produtoDTO = criarProdutoDTO(1L, "Produto Teste", 10.0);
        when(produtoService.buscarPorId(1L)).thenReturn(Optional.of(produtoDTO));
        Carrinho carrinho = new Carrinho();

        CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
        carrinhoDTO.setProdutoId(1L);
        carrinhoDTO.setQuantidade(2);

        ResponseEntity<String> responseAdd = carrinhoController.adicionarAoCarrinho(carrinhoDTO, carrinho);
        assertEquals(200, responseAdd.getStatusCode().value());
        assertEquals(1, carrinho.getItens().size());
        assertEquals(20.0, carrinho.getTotal());

        ResponseEntity<CarrinhoResponseDTO> responseGet = carrinhoController.visualizarCarrinho(carrinho);
        assertEquals(200, responseGet.getStatusCode().value());
        assertEquals(1, responseGet.getBody().getItens().size());
        assertEquals(20.0, responseGet.getBody().getTotal());
    }

    @Test
    void deveAtualizarQuantidadeProdutoNoCarrinho() {
        ProdutoDTO produtoDTO = criarProdutoDTO(1L, "Produto Teste", 10.0);
        when(produtoService.buscarPorId(1L)).thenReturn(Optional.of(produtoDTO));
        Carrinho carrinho = new Carrinho();

        // Adiciona 2 unidades
        CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
        carrinhoDTO.setProdutoId(1L);
        carrinhoDTO.setQuantidade(2);
        carrinhoController.adicionarAoCarrinho(carrinhoDTO, carrinho);

        assertEquals(2, carrinho.getItens().get(0).getQuantidade());
        assertEquals(20.0, carrinho.getTotal());

        // Atualiza para 1 unidade
        CarrinhoDTO carrinhoDTOAtualiza = new CarrinhoDTO();
        carrinhoDTOAtualiza.setProdutoId(1L);
        carrinhoDTOAtualiza.setQuantidade(1);
        carrinhoController.atualizarItem(0, carrinhoDTOAtualiza, carrinho);

        assertEquals(1, carrinho.getItens().get(0).getQuantidade());
        assertEquals(10.0, carrinho.getTotal());

        ResponseEntity<CarrinhoResponseDTO> responseGet = carrinhoController.visualizarCarrinho(carrinho);
        assertEquals(1, responseGet.getBody().getItens().size());
        assertEquals(10.0, responseGet.getBody().getTotal());
    }

    @Test
    void deveExcluirUmProdutoDoCarrinho() {
        ProdutoDTO produto1 = criarProdutoDTO(1L, "Produto 1", 10.0);
        ProdutoDTO produto2 = criarProdutoDTO(2L, "Produto 2", 20.0);
        when(produtoService.buscarPorId(1L)).thenReturn(Optional.of(produto1));
        when(produtoService.buscarPorId(2L)).thenReturn(Optional.of(produto2));
        Carrinho carrinho = new Carrinho();

        // Adiciona dois produtos
        CarrinhoDTO dto1 = new CarrinhoDTO();
        dto1.setProdutoId(1L);
        dto1.setQuantidade(1);
        carrinhoController.adicionarAoCarrinho(dto1, carrinho);

        CarrinhoDTO dto2 = new CarrinhoDTO();
        dto2.setProdutoId(2L);
        dto2.setQuantidade(2);
        carrinhoController.adicionarAoCarrinho(dto2, carrinho);

        assertEquals(2, carrinho.getItens().size());
        assertEquals(50.0, carrinho.getTotal());

        // Remove o primeiro produto
        carrinhoController.removerItem(0, carrinho);

        assertEquals(1, carrinho.getItens().size());
        assertEquals("Produto 2", carrinho.getItens().get(0).getNomeProduto());
        assertEquals(40.0, carrinho.getTotal());

        ResponseEntity<CarrinhoResponseDTO> responseGet = carrinhoController.visualizarCarrinho(carrinho);
        assertEquals(1, responseGet.getBody().getItens().size());
        assertEquals(40.0, responseGet.getBody().getTotal());
    }

    @Test
    void deveLimparCarrinho() {
        ProdutoDTO produtoDTO = criarProdutoDTO(1L, "Produto Teste", 10.0);
        when(produtoService.buscarPorId(1L)).thenReturn(Optional.of(produtoDTO));
        Carrinho carrinho = new Carrinho();

        CarrinhoDTO carrinhoDTO = new CarrinhoDTO();
        carrinhoDTO.setProdutoId(1L);
        carrinhoDTO.setQuantidade(2);
        carrinhoController.adicionarAoCarrinho(carrinhoDTO, carrinho);

        assertEquals(1, carrinho.getItens().size());
        assertEquals(20.0, carrinho.getTotal());

        // Limpa o carrinho
        org.springframework.web.bind.support.SessionStatus sessionStatus = mock(org.springframework.web.bind.support.SessionStatus.class);
        carrinhoController.limparCarrinho(carrinho, sessionStatus);

        assertEquals(0, carrinho.getItens().size());
        assertEquals(0.0, carrinho.getTotal());

        ResponseEntity<CarrinhoResponseDTO> responseGet = carrinhoController.visualizarCarrinho(carrinho);
        assertEquals(0, responseGet.getBody().getItens().size());
        assertEquals(0.0, responseGet.getBody().getTotal());
    }
}