package com.compras.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compras.exception.NotFoundException;
import com.compras.model.Categoria;
import com.compras.model.Produto;
import com.compras.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	
	public Page<Produto> listarProdutos(Pageable pageable){
		return produtoRepository.findAll(pageable);
	}
	
	public Produto carregarProduto (Long id){
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new NotFoundException("Objeto não encontrado"));
	}
	
	public Produto salvarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto atualizarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void deletarProduto(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		produtoRepository.delete(produto.get());
	}
}
