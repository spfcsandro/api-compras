package com.compras.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "{campo.validacao.vazio}")
	private String codigo;
	@NotEmpty(message = "{campo.validacao.vazio}")
	private String descricao;
	private Double valor = 0.0;
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
}
