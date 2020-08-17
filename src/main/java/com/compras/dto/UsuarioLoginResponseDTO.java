package com.compras.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UsuarioLoginResponseDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String token;
	private Long expireIn;
	private String tokenProvider;
}
