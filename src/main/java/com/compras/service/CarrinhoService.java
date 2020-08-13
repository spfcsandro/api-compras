package com.compras.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compras.model.Carrinho;
import com.compras.model.Produto;
import com.compras.model.Usuario;
import com.compras.repository.CarrinhoRepository;

@Service
public class CarrinhoService {
	
	@Autowired
	CarrinhoRepository carrinhoRepository;
	

	public Produto adicionarProduto(@Valid Produto produto) {
		
		
		return null;
	}

	public void cadastrarCarrinhoCliente(Usuario usuarioCadastrado) {
		Carrinho carrinho = new Carrinho();
		carrinho.setCliente(usuarioCadastrado);
		carrinhoRepository.save(carrinho);
	}

}
