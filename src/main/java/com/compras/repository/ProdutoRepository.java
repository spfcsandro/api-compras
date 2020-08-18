package com.compras.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compras.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Optional<Produto> findByCodigo(String codigo);

}
