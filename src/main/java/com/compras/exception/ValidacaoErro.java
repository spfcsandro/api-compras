package com.compras.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoErro extends Erro{
	
	private static final long serialVersionUID = 1L;
	
	private List<CampoMensagem> errors = new ArrayList<>();
	
	public ValidacaoErro(Integer status, String message, Long tempo) {
		super(status, message, tempo);
	}
	
	public List<CampoMensagem> getErrors() {
		return errors;
	}

	public void adicionarErro(String campo, String menssagem) {
		errors.add(new CampoMensagem(campo, menssagem));
	}
}
