package com.compras.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compras.dto.CarrinhoResumoDTO;
import com.compras.model.ItemCarrinho;
import com.compras.service.CarrinhoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Carrinho", description = "Endpoint de Carrinho", tags = {"Carrinho"})
@RestController
@RequestMapping("/v1/carrinho")
public class CarrinhoResource {
	
	@Autowired
	CarrinhoService carrinhoService;
	
	@ApiOperation(value = "Adicionar Produtos no Carrinho")
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_CLIENTE"})
	@PostMapping("/adicionar-item-carrinho")
	public ResponseEntity<ItemCarrinho> adicionarItemCarrinho(@Valid @RequestBody ItemCarrinho itemCarrinho){
		ItemCarrinho itemCarrinhoAdicionado = carrinhoService.adicionarItemCarrinho(itemCarrinho);
		return ResponseEntity.status(HttpStatus.CREATED).body(itemCarrinhoAdicionado);
	}
	
	@ApiOperation(value = "Listar Produtos do Carrinho")
	@Secured({"ROLE_ADMINISTRADOR", "ROLE_CLIENTE"})
	@GetMapping
	public ResponseEntity<CarrinhoResumoDTO> listarItemCarrinho(){
		CarrinhoResumoDTO carrinhoResumoDTO = carrinhoService.listarItemCarrinho();
		return ResponseEntity.ok(carrinhoResumoDTO);
	}
	
}
