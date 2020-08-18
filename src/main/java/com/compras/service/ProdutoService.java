package com.compras.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compras.exception.NegocioException;
import com.compras.exception.NotFoundException;
import com.compras.model.Produto;
import com.compras.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final MessageSource messageSource;
	
	public Page<Produto> listarProdutos(Pageable pageable){
		return produtoRepository.findAll(pageable);
	}
	
	public Produto carregarProduto (Long id){
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new NotFoundException("Objeto não encontrado"));
	}
	
	public Produto salvarProduto(Produto produto) {
		validarProdutoComMesmoCodigo(produto);
		return produtoRepository.save(produto);
	}
	
	private void validarProdutoComMesmoCodigo(Produto produto) {
		Optional<Produto> produtoBanco = produtoRepository.findByCodigo(produto.getCodigo());
		
		if(produtoBanco.isPresent()){
			throw new NegocioException(messageSource.getMessage("negocio.validacao.codigo.existente", new Object[] {"'"+ produto.getCodigo() +"'"}, null));
		}
	}

	public Produto atualizarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void deletarProduto(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		produtoRepository.delete(produto.get());
	}
}
