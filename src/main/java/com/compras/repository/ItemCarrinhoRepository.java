package com.compras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compras.model.ItemCarrinho;

@Repository
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long>{

	List<ItemCarrinho> findByCarrinhoId(Long id);

//	List<ItemCarrinho> findByCarrinhoIdAndProdutoCategoriaCuponsCumulativoIsFalse(Long id);

}
