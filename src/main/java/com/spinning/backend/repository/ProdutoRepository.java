package com.spinning.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spinning.backend.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
