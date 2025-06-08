package com.spinning.backend.model;

import com.spinning.backend.dto.ProdutoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCarrinho {

    private String nomeProduto;
    private String imagemProduto;
    private Double valorPorProduto;
    private int quantidade;

    public ItemCarrinho(ProdutoDTO produtoDTO, int quantidade) {
        this.nomeProduto = produtoDTO.getNome();
        this.imagemProduto = produtoDTO.getImagem();
        this.valorPorProduto = produtoDTO.getPreco();
        this.quantidade = quantidade;
    }

    public Double getSubtotal() {
        return valorPorProduto * quantidade;
    }

}
