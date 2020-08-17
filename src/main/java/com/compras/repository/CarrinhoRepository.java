package com.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compras.model.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{

	Carrinho findByClienteId(Long id);


}
