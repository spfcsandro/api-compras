package com.compras.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compras.model.Cupom;
import com.compras.service.CupomService;

@RestController
@RequestMapping("/v1/cupons/")
public class CupomResource {

	@Autowired
	CupomService cupomService;
	
	@PostMapping
	public ResponseEntity<Cupom> salvarCupom(@RequestBody Cupom cupom){
		
		Cupom cupomCriado = cupomService.salvarCupom(cupom);
		return ResponseEntity.status(HttpStatus.CREATED).body(cupomCriado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cupom> atualizarCupom(@PathVariable(name = "id") Long id, 
			@RequestBody Cupom cupom){
		
		cupom.setId(id);
		Cupom cupomAtualizado = cupomService.atualizarCupom(cupom);
		return ResponseEntity.ok(cupomAtualizado);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cupom> carregarCupom(@PathVariable(name = "id") Long id){
		Cupom cupomCarregado = cupomService.carregarCupom(id);
		return ResponseEntity.ok(cupomCarregado);
	}
	
	@GetMapping
	public ResponseEntity<Page<Cupom>> listarCupons(Pageable pageable){
		Page<Cupom> cupoms = cupomService.listarCupons(pageable);
		return ResponseEntity.ok(cupoms);
	}
}
