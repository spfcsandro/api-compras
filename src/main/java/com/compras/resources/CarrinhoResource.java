package com.compras.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compras.model.Produto;
import com.compras.model.Usuario;
import com.compras.service.CarrinhoService;

@RestController
@RequestMapping("/v1/carrinho")
public class CarrinhoResource {
	
	@Autowired
	CarrinhoService carrinhoService;
	
	@PostMapping("/adicionar-produto")
	public ResponseEntity<Produto> adicionarProduto(@Valid @RequestBody Produto produto){
		Produto produtoAdicionado = carrinhoService.adicionarProduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoAdicionado);
	}
	
	@PostMapping("/adicionar-cupom")
	public ResponseEntity<Produto> adicionarCupom(String codigoCupom){
		//Produto produtoAdicionado = carrinhoService.adicionarCupom(codigoCupom);
		//return ResponseEntity.status(HttpStatus.CREATED).body(produtoAdicionado);
		return null;
	}
	
}
