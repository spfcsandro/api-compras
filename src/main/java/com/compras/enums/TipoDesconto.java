package com.compras.enums;

public enum TipoDesconto {

	PERCENTUAL("Percentual"),
	FIXO("Fixo");
	
	public String descricao;
	
	private TipoDesconto(String descricao) {
		this.descricao = descricao;
	}
	
}
