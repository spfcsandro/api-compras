package com.compras.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compras.model.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long>{

	Optional<Cupom> findByCodigo(String codigo);

//	List<Cupom> findAllCumulativoIsFalse();

}
