package com.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compras.model.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long>{

//	List<Cupom> findAllCumulativoIsFalse();

}
