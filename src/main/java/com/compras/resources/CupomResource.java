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

import com.compras.model.Cupom;
import com.compras.service.CupomService;

import io.swagger.annotations.Api;

@Api(value = "Cupom", description = "Endpoint de Cupom", tags = {"Cupom"})
@RestController
@RequestMapping("/v1/cupons")
public class CupomResource {

	@Autowired
	CupomService cupomService;
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@PostMapping
	public ResponseEntity<Cupom> salvarCupom(@RequestBody Cupom cupom){
		
		Cupom cupomCriado = cupomService.salvarCupom(cupom);
		return ResponseEntity.status(HttpStatus.CREATED).body(cupomCriado);
	}
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@PutMapping("/{id}")
	public ResponseEntity<Cupom> atualizarCupom(@PathVariable(name = "id") Long id, 
			@RequestBody Cupom cupom){
		
		cupom.setId(id);
		Cupom cupomAtualizado = cupomService.atualizarCupom(cupom);
		return ResponseEntity.ok(cupomAtualizado);
	}
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@GetMapping("/{id}")
	public ResponseEntity<Cupom> carregarCupom(@PathVariable(name = "id") Long id){
		Cupom cupomCarregado = cupomService.carregarCupom(id);
		return ResponseEntity.ok(cupomCarregado);
	}
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@GetMapping
	public ResponseEntity<Page<Cupom>> listarCupons(Pageable pageable){
		Page<Cupom> cupoms = cupomService.listarCupons(pageable);
		return ResponseEntity.ok(cupoms);
	}
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@DeleteMapping("/{id}")
	public void deletarCupom(@PathVariable(name = "id") Long id){
		cupomService.deletarCupom(id);
	}
}
