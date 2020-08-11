package com.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compras.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
