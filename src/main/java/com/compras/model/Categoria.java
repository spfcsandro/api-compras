package com.compras.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import com.compras.enums.TipoDesconto;
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
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_desconto")
	private TipoDesconto tipoDesconto;
	private BigDecimal desconto;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "categorias")
	private List<Produto> produtos = new ArrayList<>();

}
