package com.compras.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampoMensagem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String campoValidacao;
	private String mensagem;
}
