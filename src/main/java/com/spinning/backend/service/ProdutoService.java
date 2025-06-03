package com.spinning.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spinning.backend.dto.ProdutoDTO;
import com.spinning.backend.mapper.ProdutoMapper;
import com.spinning.backend.model.Produto;
import com.spinning.backend.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    public ProdutoDTO criar(ProdutoDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);
        produto = produtoRepository.save(produto);
        return produtoMapper.toDTO(produto);
    }

    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(produtoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> buscarPorId(Long id) {
        return produtoRepository.findById(id).map(produtoMapper::toDTO);
    }

    public boolean deletar(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<ProdutoDTO> atualizar(Long id, ProdutoDTO dto) {
        return produtoRepository.findById(id).map(produtoExistente -> {
            Produto produtoAtualizado = produtoMapper.toEntity(dto);
            produtoAtualizado.setId(id);
            produtoRepository.save(produtoAtualizado);
            return produtoMapper.toDTO(produtoAtualizado);
        });
    }
}
