package com.compras.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class ItemCarrinho implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id_carrinho", nullable = false)
	private Carrinho carrinho;
	@OneToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	private Integer quantidade;
	private Double preco;
	private Double desconto;
	
	public double getSubTotal(){
		return (preco - desconto) * quantidade;
	}
	
}