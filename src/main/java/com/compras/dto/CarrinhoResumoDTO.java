package com.compras.dto;

import java.util.ArrayList;
import java.util.List;

import com.compras.model.Cupom;

import lombok.Data;

@Data
public class CarrinhoResumoDTO {

	private List<CarrinhoResumoItemDTO> itens = new ArrayList<>();
	private Double total = 0.0;
}
