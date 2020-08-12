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

import com.compras.model.Categoria;
import com.compras.service.CategoriaService;

@RestController
@RequestMapping("/v1/categorias/")
public class CategoriaResource {

	@Autowired
	CategoriaService categoriaService;
	
	@PostMapping
	public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria){
		
		Categoria categoriaCriado = categoriaService.salvarCategoria(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCriado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable(name = "id") Long id, 
			@RequestBody Categoria categoria){
		
		categoria.setId(id);
		Categoria categoriaAtualizado = categoriaService.atualizarCategoria(categoria);
		return ResponseEntity.ok(categoriaAtualizado);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> carregarCategoria(@PathVariable(name = "id") Long id){
		Categoria categoriaCarregado = categoriaService.carregarCategoria(id);
		return ResponseEntity.ok(categoriaCarregado);
	}
	
	@GetMapping
	public ResponseEntity<Page<Categoria>> listarCategorias(Pageable pageable){
		Page<Categoria> categorias = categoriaService.listarCategorias(pageable);
		return ResponseEntity.ok(categorias);
	}
}
