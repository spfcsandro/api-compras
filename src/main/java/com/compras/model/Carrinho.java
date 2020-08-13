package com.compras.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class Carrinho implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario cliente;
	@OneToMany(mappedBy = "carrinho")
	private Set<ItemCarrinho> itens = new HashSet<>();
	
	/*
	 * public double getValorTotal() { double soma = 0.0; for (ItemCarrinho item :
	 * itens) { soma += item.getSubTotal(); } return soma; }
	 */
}
