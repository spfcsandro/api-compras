package com.compras.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.compras.model.Cupom;
import com.compras.model.ItemCarrinho;
import com.compras.model.Usuario;

import lombok.Data;

@Data
public class CarrinhoResumoDTO {

	private Long id;
	private Usuario cliente;
	private Set<ItemCarrinho> itens = new HashSet<>();
	List<Cupom> cupons = new ArrayList<>();
	private double total = 0.0;
}
