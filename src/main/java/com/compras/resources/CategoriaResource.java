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

import com.compras.model.Categoria;
import com.compras.service.CategoriaService;

import io.swagger.annotations.Api;

@Api(value = "Categoria", description = "Endpoint de Categoria", tags = {"Categoria"})
@RestController
@RequestMapping("/v1/categorias")
public class CategoriaResource {

	@Autowired
	CategoriaService categoriaService;
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@PostMapping
	public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria){
		
		Categoria categoriaCriado = categoriaService.salvarCategoria(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCriado);
	}
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable(name = "id") Long id, 
			@RequestBody Categoria categoria){
		
		categoria.setId(id);
		Categoria categoriaAtualizado = categoriaService.atualizarCategoria(categoria);
		return ResponseEntity.ok(categoriaAtualizado);
	}
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> carregarCategoria(@PathVariable(name = "id") Long id){
		Categoria categoriaCarregado = categoriaService.carregarCategoria(id);
		return ResponseEntity.ok(categoriaCarregado);
	}
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@GetMapping
	public ResponseEntity<Page<Categoria>> listarCategorias(Pageable pageable){
		Page<Categoria> categorias = categoriaService.listarCategorias(pageable);
		return ResponseEntity.ok(categorias);
	}
	
	@Secured({"ROLE_ADMINISTRADOR"})
	@DeleteMapping("/{id}")
	public void deletarCategoria(@PathVariable(name = "id") Long id){
		categoriaService.deletarCategoria(id);
	}
}
