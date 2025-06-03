package com.spinning.backend.mapper;

import org.springframework.stereotype.Component;

import com.spinning.backend.dto.ProdutoDTO;
import com.spinning.backend.model.Produto;

@Component
public class ProdutoMapper {

    public ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setCategoria(produto.getCategoria());
        dto.setImagem(produto.getImagem());
        dto.setInformacoesExtras(produto.getInformacoesExtras());
        return dto;
    }

    public Produto toEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());
        produto.setImagem(dto.getImagem());
        produto.setInformacoesExtras(dto.getInformacoesExtras());
        return produto;
    }
}
