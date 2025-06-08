package com.spinning.backend.dto;

import com.spinning.backend.model.ItemCarrinho;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarrinhoResponseDTO {
    private List<ItemCarrinho> itens;
    private Double subtotal;
    private Double frete;
    private Double total;
}
