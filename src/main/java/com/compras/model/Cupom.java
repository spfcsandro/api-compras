package com.compras.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.compras.enums.TipoDesconto;

import lombok.Data;

@Data
@Entity
public class Cupom implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "{campo.validacao.vazio}")
	private String codigo;
	
	@NotEmpty(message = "{campo.validacao.vazio}")
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_desconto", nullable = false)
	@NotNull(message = "{campo.validacao.vazio}")
	private TipoDesconto tipoDesconto;

	private Double desconto;
}
