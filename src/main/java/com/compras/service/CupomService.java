package com.compras.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compras.exception.NegocioException;
import com.compras.model.Cupom;
import com.compras.repository.CupomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CupomService {

	private final CupomRepository cupomRepository;
	private final MessageSource messageSource;
	
	public Page<Cupom> listarCupons(Pageable pageable){
		return cupomRepository.findAll(pageable);
	}
	
	public Cupom carregarCupom (Long id){
		return cupomRepository.findById(id).get();
	}
	
	public Cupom salvarCupom(Cupom cupom) {
		validarCupomComMesmoCodigo(cupom);
		return cupomRepository.save(cupom);
	}
	
	private void validarCupomComMesmoCodigo(Cupom cupom) {
		Optional<Cupom> cupomBanco = cupomRepository.findByCodigo(cupom.getCodigo());
		
		if(cupomBanco.isPresent()){
			throw new NegocioException(messageSource.getMessage("negocio.validacao.codigo.existente", new Object[] {"'"+ cupom.getCodigo() +"'"}, null));
		}
	}
	
	public Cupom atualizarCupom(Cupom cupom) {
		return cupomRepository.save(cupom);
	}
	
	public void deletarCupom(Long id) {
		Optional<Cupom> cupom = cupomRepository.findById(id);
		cupomRepository.delete(cupom.get());
	}
}
