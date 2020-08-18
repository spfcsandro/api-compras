package com.compras.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compras.model.Produto;
import com.compras.service.ProdutoService;

import io.swagger.annotations.Api;

@Api(value = "Produto", description = "Endpoint de Produto", tags = {"Produto"})
@RestController
@RequestMapping("/v1/produtos")
public class ProdutoResource {

	@Autowired
	ProdutoService produtoService;
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@PostMapping
	public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto){
		Produto produtoCriado = produtoService.salvarProduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
	}
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable(name = "id") Long id, 
			@RequestBody Produto produto){
		
		produto.setId(id);
		Produto produtoAtualizado = produtoService.atualizarProduto(produto);
		return ResponseEntity.ok(produtoAtualizado);
	}
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@GetMapping("/{id}")
	public ResponseEntity<Produto> carregarProduto(@PathVariable(name = "id") Long id){
		Produto produtoCarregado = produtoService.carregarProduto(id);
		return ResponseEntity.ok(produtoCarregado);
	}
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@GetMapping
	public ResponseEntity<Page<Produto>> listarProdutos(Pageable pageable){
		Page<Produto> produtos = produtoService.listarProdutos(pageable);
		return ResponseEntity.ok(produtos);
	}
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@DeleteMapping("/{id}")
	public void deletarProduto(@PathVariable(name = "id") Long id){
		produtoService.deletarProduto(id);
	}
}
