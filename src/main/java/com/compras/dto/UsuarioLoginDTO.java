package com.compras.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioLoginDTO {
	
	@NotEmpty(message = "{campo.validacao.vazio}")
	@Email
	private String email;
	@NotEmpty(message = "{campo.validacao.vazio}")
	private String senha;
}
