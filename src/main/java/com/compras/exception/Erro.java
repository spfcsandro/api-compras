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
public class Erro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String mensagem;
	private Long tempo;
}
