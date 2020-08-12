package com.compras.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compras.model.Categoria;
import com.compras.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;
	
	public Page<Categoria> listarCategorias(Pageable pageable){
		return categoriaRepository.findAll(pageable);
	}
	
	public Categoria carregarCategoria (Long id){
		return categoriaRepository.findById(id).get();
	}
	
	public Categoria salvarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria atualizarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public void deletarCategoria(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		categoriaRepository.delete(categoria.get());
	}
}
