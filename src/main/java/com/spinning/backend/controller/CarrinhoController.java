package com.spinning.backend.controller;

import com.spinning.backend.dto.CarrinhoDTO;
import com.spinning.backend.dto.ProdutoDTO;
import com.spinning.backend.model.Carrinho;
import com.spinning.backend.model.ItemCarrinho;
import com.spinning.backend.service.ProdutoService;
import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@RestController
@RequestMapping("/api")
@SessionAttributes("carrinho") // salva carrinho na sessão
public class CarrinhoController {

    private final ProdutoService produtoService;

    public CarrinhoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @ModelAttribute("carrinho")
    public Carrinho carrinho() {
        return new Carrinho();
    }

    @PostMapping("/carrinho")
    public ResponseEntity<String> adicionarAoCarrinho(@RequestBody @Valid CarrinhoDTO carrinhoDTO,
            @ModelAttribute("carrinho") Carrinho carrinho) {
        return produtoService.buscarPorId(carrinhoDTO.getProdutoId())
                .map(produtoDTO -> {
                    ItemCarrinho item = new ItemCarrinho(produtoDTO, carrinhoDTO.getQuantidade());
                    carrinho.adicionarItem(item);
                    return ResponseEntity.ok("Produto adicionado ao carrinho.");
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/carrinho/{indice}")
    public ResponseEntity<?> atualizarItem(@PathVariable int indice,
            @RequestBody @Valid CarrinhoDTO carrinhoDTO,
            @ModelAttribute("carrinho") Carrinho carrinho) {
        if (indice < 0 || indice >= carrinho.getItens().size()) {
            return ResponseEntity.badRequest().body("Índice inválido.");
        }

        Optional<ProdutoDTO> produtoOpt = produtoService.buscarPorId(carrinhoDTO.getProdutoId());
        if (produtoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ItemCarrinho novoItem = new ItemCarrinho(produtoOpt.get(), carrinhoDTO.getQuantidade());
        carrinho.getItens().set(indice, novoItem); // Substitui o item na posição

        return ResponseEntity.ok("Item atualizado com sucesso.");
    }

    @GetMapping("/carrinho")
    public ResponseEntity<Carrinho> visualizarCarrinho(@ModelAttribute("carrinho") Carrinho carrinho) {
        return ResponseEntity.ok(carrinho);
    }

    @DeleteMapping("/carrinho/{indice}")
    public ResponseEntity<String> removerItem(@PathVariable int indice,
            @ModelAttribute("carrinho") Carrinho carrinho) {
        if (indice < 0 || indice >= carrinho.getItens().size()) {
            return ResponseEntity.badRequest().body("Índice inválido.");
        }

        carrinho.removerItemPorIndice(indice);
        return ResponseEntity.ok("Item removido com sucesso.");
    }

    @DeleteMapping("/carrinho/limpar")
    public ResponseEntity<Void> limparCarrinho(@ModelAttribute("carrinho") Carrinho carrinho, SessionStatus sessionStatus) {
        carrinho.getItens().clear(); // Limpa os itens do carrinho
        sessionStatus.setComplete(); // Finaliza a sessão do carrinho
        return ResponseEntity.ok().build();
    }

}
