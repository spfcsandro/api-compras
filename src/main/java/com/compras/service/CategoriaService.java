package com.compras.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compras.exception.NegocioException;
import com.compras.exception.NotFoundException;
import com.compras.model.Categoria;
import com.compras.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;
	private final MessageSource messageSource;
	
	public Page<Categoria> listarCategorias(Pageable pageable){
		return categoriaRepository.findAll(pageable);
	}
	
	public Categoria carregarCategoria (Long id){
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new NotFoundException("Objeto não encontrado"));
	}
	
	public Categoria salvarCategoria(Categoria categoria) {
		validarCategoriaComMesmoCodigo(categoria);
		return categoriaRepository.save(categoria);
	}
	
	private void validarCategoriaComMesmoCodigo(Categoria categoria) {
		Optional<Categoria> categoriaBanco = categoriaRepository.findByCodigo(categoria.getCodigo());
		
		if(categoriaBanco.isPresent()){
			throw new NegocioException(messageSource.getMessage("negocio.validacao.codigo.existente", new Object[] {"'"+ categoria.getCodigo() +"'"}, null));
		}
	}
	
	public Categoria atualizarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public void deletarCategoria(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		categoriaRepository.delete(categoria.get());
	}
}
