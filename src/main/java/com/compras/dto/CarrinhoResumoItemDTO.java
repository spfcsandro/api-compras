package com.compras.dto;

import java.util.ArrayList;
import java.util.List;

import com.compras.model.Cupom;

import lombok.Data;

@Data
public class CarrinhoResumoItemDTO {

	private Long id;
	private String codigoProduto;
	private String descricaoproduto;
	private Integer quantidade;
	private Double valorProduto = 0.0;
	private Double valorProdutoComDesconto = 0.0;
	private List<Cupom> cuponsAplicadoNoProduto = new ArrayList<>();

}
