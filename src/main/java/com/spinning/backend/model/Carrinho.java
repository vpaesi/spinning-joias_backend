package com.spinning.backend.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<ItemCarrinho> itens = new ArrayList<>();

    public void adicionarItem(ItemCarrinho item) {
        itens.add(item);
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public Double getTotal() {
        return itens.stream()
                    .mapToDouble(ItemCarrinho::getSubtotal)
                    .sum();
    }

    public void removerItemPorIndice(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            itens.remove(indice);
        }
    }
}
