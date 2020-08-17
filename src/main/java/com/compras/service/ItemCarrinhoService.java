package com.compras.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compras.dto.CarrinhoResumoDTO;
import com.compras.enums.TipoDesconto;
import com.compras.exception.NotFoundException;
import com.compras.model.Carrinho;
import com.compras.model.Cupom;
import com.compras.model.ItemCarrinho;
import com.compras.model.Usuario;
import com.compras.repository.CarrinhoRepository;
import com.compras.repository.CupomRepository;
import com.compras.repository.ItemCarrinhoRepository;
import com.compras.repository.UsuarioRepository;
import com.compras.util.AuthUtils;
import com.compras.util.ListaUtil;
import com.compras.util.ObjectUtil;

@Service
public class ItemCarrinhoService {
	
	@Autowired
	ItemCarrinhoRepository itemCarrinhoRepository;
	@Autowired
	CarrinhoRepository carrinhoRepository;
	@Autowired
	CupomRepository cupomRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public ItemCarrinho adicionarItemCarrinho(@Valid ItemCarrinho itemCarrinho) {
		Carrinho carrinho = carregarCarrinhoPorUsuario();
		
		if(ObjectUtil.isObjectNull(carrinho)) {
			carrinho = new Carrinho();
			carrinho.setCliente(usuarioRepository.findByEmail(AuthUtils.usuarioLogado()).get());
			carrinhoRepository.save(carrinho);
		}
		
		itemCarrinho.setCarrinho(carrinho);
		itemCarrinhoRepository.save(itemCarrinho);
		
		return itemCarrinho;
	}
	
	public CarrinhoResumoDTO listarItemCarrinho() {
		Carrinho carrinho = carregarCarrinhoPorUsuario();
		List<Cupom> cuponsBanco = cupomRepository.findAll();
		List<ItemCarrinho> itens = itemCarrinhoRepository.findByCarrinhoId(carrinho.getId());
		
		for (ItemCarrinho itemCarrinho : itens) {
			aplicarDescontoCategoriaItem(itemCarrinho, carrinho);
		}
		
		aplicarDescontoGlobal(carrinho, cuponsBanco);
		return criarCarrinhoResumoDTO(carrinho);
	}

	private CarrinhoResumoDTO criarCarrinhoResumoDTO(Carrinho carrinho) {
		CarrinhoResumoDTO carrinhoResumoDTO = new CarrinhoResumoDTO();
		carrinhoResumoDTO.setId(carrinho.getId());
		carrinhoResumoDTO.setCliente(carrinho.getCliente());
		carrinhoResumoDTO.setItens(carrinho.getItens());
		carrinhoResumoDTO.setCupons(carrinho.getCupons());
		carrinhoResumoDTO.setTotal(carrinho.getTotal());
		return carrinhoResumoDTO;
	}

	private Carrinho carregarCarrinhoPorUsuario() {
		Usuario usuario = usuarioRepository.findByEmail(AuthUtils.usuarioLogado()).orElseThrow(() -> new NotFoundException("Objeto não encontrado"));
		Carrinho carrinho = carrinhoRepository.findByClienteId(usuario.getId());
		return carrinho;
	}

	private void aplicarDescontoGlobal(Carrinho carrinho, List<Cupom> cuponsBanco) {
		for (Cupom cupom : cuponsBanco) {
			double total = carrinho.getTotal();
			if(total > 0) {
				if(TipoDesconto.PERCENTUAL.equals(cupom.getTipoDesconto())) {
					total -= (total * (cupom.getDesconto() / 100));
				}else {
					total -= cupom.getDesconto();
				}
				carrinho.getCupons().add(cupom);
			}
			carrinho.setTotal(total);
		}
	}


	private void aplicarDescontoCategoriaItem(ItemCarrinho itemCarrinho, Carrinho carrinho) {
		
		double total = carrinho.getTotal();
		
		if(existeCategoriaComCupom(itemCarrinho)){
			for(Cupom cupom : itemCarrinho.getProduto().getCategoria().getCupons()) {
				
				itemCarrinho.getCarrinho().getCupons().add(cupom);
				
				if(TipoDesconto.PERCENTUAL.equals(cupom.getTipoDesconto())) {
					itemCarrinho.setPreco(itemCarrinho.getProduto().getValor() - (itemCarrinho.getProduto().getValor() * cupom.getDesconto() / 100));
				}else {
					itemCarrinho.setPreco(itemCarrinho.getProduto().getValor() - cupom.getDesconto());
				}
				carrinho.setTotal(total += itemCarrinho.getPreco());
			}
		}else {
			carrinho.setTotal(total += itemCarrinho.getProduto().getValor());
		}
	}

	private boolean existeCategoriaComCupom(ItemCarrinho itemCarrinho) {
		return !ObjectUtil.isObjectNull(itemCarrinho.getProduto().getCategoria()) && 
				ListaUtil.possuiItens(itemCarrinho.getProduto().getCategoria().getCupons());
	}


}
