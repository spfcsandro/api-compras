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
public class UsuarioClienteDTO {
	
	@NotEmpty(message = "{campo.validacao.vazio}")
	@Length(min=5, max=80, message = "{campo.validacao.tamanho}")
	private String nome;
	@NotEmpty(message = "{campo.validacao.vazio}")
	@Email
	private String email;
	@Getter(onMethod = @__({@JsonIgnore}))
	@Setter(onMethod = @__({@JsonProperty}))
	@NotEmpty(message = "{campo.validacao.vazio}")
	private String senha;
}
