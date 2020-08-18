package com.compras.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class ItemCarrinho implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_carrinho", nullable = false)
	private Carrinho carrinho;
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	private Integer quantidade;
	private Double preco = 0.0;
	@Transient
	private List<Cupom> cuponsAplicadoNoProduto = new ArrayList<>();
	
/*	public double getSubTotal(){ 
		 return (preco - desconto) * quantidade; 
	}
	*/
/*	private void descontos(ItemCarrinho item) {
		if(!ObjectUtil.isObjectNull(item.getProduto().getCategoria()) &&
				ListaUtil.possuiItens(item.getProduto().getCategoria().getCupons())){
			
			
		}
	}
	 */
	
}
