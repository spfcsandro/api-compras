package com.compras.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compras.model.Cupom;
import com.compras.repository.CupomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CupomService {

	private final CupomRepository cupomRepository;
	
	public Page<Cupom> listarCupons(Pageable pageable){
		return cupomRepository.findAll(pageable);
	}
	
	public Cupom carregarCupom (Long id){
		return cupomRepository.findById(id).get();
	}
	
	public Cupom salvarCupom(Cupom cupom) {
		return cupomRepository.save(cupom);
	}
	
	public Cupom atualizarCupom(Cupom cupom) {
		return cupomRepository.save(cupom);
	}
	
	public void deletarCupom(Long id) {
		Optional<Cupom> cupom = cupomRepository.findById(id);
		cupomRepository.delete(cupom.get());
	}
}
