package com.spinning.backend.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<ItemCarrinho> itens = new ArrayList<>();
    private Double frete = 0.0;

    public void adicionarItem(ItemCarrinho item) {
        itens.add(item);
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public Double getSubtotal() {
        return itens.stream()
                    .mapToDouble(ItemCarrinho::getSubtotal)
                    .sum();
    }

    public Double getFrete() {
        return frete;
    }

    public void setFrete(Double frete) {
        this.frete = frete;
    }

    public Double getTotal() {
        return getSubtotal() + (frete != null ? frete : 0.0);
    }

    public void removerItemPorIndice(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            itens.remove(indice);
        }
    }
}
