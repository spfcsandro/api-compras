package com.compras.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "{campo.validacao.vazio}")
	private String codigo;
	
	@NotEmpty(message = "{campo.validacao.vazio}")
	private String descricao;
	
	@ManyToMany
	@JoinTable(name = "categoria_cupom", 
		joinColumns = @JoinColumn(name = "id_categoria"),
		inverseJoinColumns = @JoinColumn(name = "id_cupom"))
	private List<Cupom> cupons = new ArrayList<>();
}
