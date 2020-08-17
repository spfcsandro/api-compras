package com.compras.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compras.dto.CarrinhoResumoDTO;
import com.compras.model.ItemCarrinho;
import com.compras.model.Produto;
import com.compras.service.ItemCarrinhoService;

@RestController
@RequestMapping("/v1/carrinho")
public class ItemCarrinhoResource {
	
	@Autowired
	ItemCarrinhoService itemCarrinhoService;
	
	@PostMapping("/adicionar-item-carrinho")
	public ResponseEntity<ItemCarrinho> adicionarItemCarrinho(@Valid @RequestBody ItemCarrinho itemCarrinho){
		ItemCarrinho itemCarrinhoAdicionado = itemCarrinhoService.adicionarItemCarrinho(itemCarrinho);
		return ResponseEntity.status(HttpStatus.CREATED).body(itemCarrinhoAdicionado);
	}
	
	@GetMapping
	public ResponseEntity<CarrinhoResumoDTO> listarItemCarrinho(){
		//List<ItemCarrinho> itens = itemCarrinhoService.listarItemCarrinho(id);
		CarrinhoResumoDTO carrinhoResumoDTO = itemCarrinhoService.listarItemCarrinho();
		return ResponseEntity.ok(carrinhoResumoDTO);
	}
	
	@PostMapping("/adicionar-cupom")
	public ResponseEntity<Produto> adicionarCupom(String codigoCupom){
		//Produto produtoAdicionado = carrinhoService.adicionarCupom(codigoCupom);
		//return ResponseEntity.status(HttpStatus.CREATED).body(produtoAdicionado);
		return null;
	}
	
}
