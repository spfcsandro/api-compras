package com.compras.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compras.dto.CarrinhoResumoDTO;
import com.compras.dto.CarrinhoResumoItemDTO;
import com.compras.enums.TipoDesconto;
import com.compras.exception.NotFoundException;
import com.compras.model.Carrinho;
import com.compras.model.Categoria;
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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarrinhoService {
	
	private final CarrinhoRepository carrinhoRepository;
	private final ItemCarrinhoRepository itemCarrinhoRepository;
	private final CupomRepository cupomRepository;
	private final UsuarioRepository usuarioRepository;
	
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
		CarrinhoResumoDTO carrinhoResumoDTO = new CarrinhoResumoDTO();
		List<Cupom> cuponsBanco = cupomRepository.findAll();
		List<ItemCarrinho> itens = itemCarrinhoRepository.findByCarrinhoId(carrinho.getId());
		
		for (ItemCarrinho itemCarrinho : itens) {
			aplicarDescontoCategoriaItem(itemCarrinho, carrinhoResumoDTO);
			carrinhoResumoDTO.getItens().add(criarCarrinhoResumoItemDTO(itemCarrinho));
		}
		
		//aplicarDescontoGlobal(carrinho, cuponsBanco);
		return carrinhoResumoDTO;
	}

	private CarrinhoResumoItemDTO criarCarrinhoResumoItemDTO(ItemCarrinho itemCarrinho) {
		CarrinhoResumoItemDTO itemDTO = new CarrinhoResumoItemDTO();
		itemDTO.setId(itemCarrinho.getId());
		itemDTO.setCodigoProduto(itemCarrinho.getProduto().getCodigo());
		itemDTO.setDescricaoproduto(itemCarrinho.getProduto().getDescricao());
		itemDTO.setQuantidade(itemCarrinho.getQuantidade());
		itemDTO.setValorProduto(itemCarrinho.getProduto().getValor());
		itemDTO.setValorProdutoComDesconto(itemCarrinho.getPreco());
		itemDTO.getCuponsAplicadoNoProduto().addAll(itemCarrinho.getCuponsAplicadoNoProduto());
		return itemDTO;
	}

	private Carrinho carregarCarrinhoPorUsuario() {
		Usuario usuario = usuarioRepository.findByEmail(AuthUtils.usuarioLogado()).orElseThrow(() -> new NotFoundException("Objeto não encontrado"));
		Carrinho carrinho = carrinhoRepository.findByClienteId(usuario.getId());
		return carrinho;
	}

	private void aplicarDescontoGlobal(Carrinho carrinho, List<Cupom> cuponsBanco) {
		for (Cupom cupom : cuponsBanco) {
			Double total = carrinho.getTotal();
			if(total > 0) {
				if(verificaSeOTipoDeDescontoDoCupomEPercentual(cupom)) {
					total -= (total * (cupom.getDesconto() / 100));
				}else {
					total -= cupom.getDesconto();
				}
				//carrinho.getCupons().add(cupom);
			}
			carrinho.setTotal(total);
		}
	}


	private void aplicarDescontoCategoriaItem(ItemCarrinho itemCarrinho, CarrinhoResumoDTO carrinhoResumoDTO) {
		
		Double total = carrinhoResumoDTO.getTotal();
		
		if(existeCategoriaComCupom(itemCarrinho)){
			Categoria categoria = itemCarrinho.getProduto().getCategoria();
			Double valorDescontoCumulativo = 0.0;
			
			if(categoria.getCupons().size() > 1) {
				for (Cupom cupom : categoria.getCupons()) {
					valorDescontoCumulativo = calcularDescontosAplicarValorNoProdutoEAdicionarCupomNaListaDoCarrinhoResumo(
							itemCarrinho, carrinhoResumoDTO, valorDescontoCumulativo, cupom);
				}
			}else {
				Cupom cupom = categoria.getCupons().get(0);
				valorDescontoCumulativo = calcularDescontosAplicarValorNoProdutoEAdicionarCupomNaListaDoCarrinhoResumo(
						itemCarrinho, carrinhoResumoDTO, valorDescontoCumulativo, cupom);
			}
			
			total = somarETotalizarOsProdutosComDescontos(itemCarrinho, carrinhoResumoDTO, total,
					valorDescontoCumulativo);
			
		}else {
			itemCarrinho.setPreco(0.0);
			total += itemCarrinho.getProduto().getValor();
			carrinhoResumoDTO.setTotal(total);
		}
	}

	private Double somarETotalizarOsProdutosComDescontos(ItemCarrinho itemCarrinho, CarrinhoResumoDTO carrinhoResumoDTO,
			Double total, Double valorDescontoCumulativo) {
		itemCarrinho.setPreco(valorDoProdutoMenosOsDescontosCumulativos(itemCarrinho, valorDescontoCumulativo));
		total += valorDoProdutoMenosOsDescontosCumulativos(itemCarrinho, valorDescontoCumulativo);
		carrinhoResumoDTO.setTotal(total);
		return total;
	}

	private Double calcularDescontosAplicarValorNoProdutoEAdicionarCupomNaListaDoCarrinhoResumo(
			ItemCarrinho itemCarrinho, CarrinhoResumoDTO carrinhoResumoDTO, Double valorDescontoCumulativo,
			Cupom cupom) {
		if(verificaSeOTipoDeDescontoDoCupomEPercentual(cupom)) {
			valorDescontoCumulativo += aplicaDescontoPercentualDoCupomNoItemDoCarrinho(itemCarrinho, cupom);
		}else {
			valorDescontoCumulativo += cupom.getDesconto();
		}
		itemCarrinho.getCuponsAplicadoNoProduto().add(cupom);
		return valorDescontoCumulativo;
	}

	private Double valorDoProdutoMenosOsDescontosCumulativos(ItemCarrinho itemCarrinho,
			Double valorDescontoCumulativo) {
		return itemCarrinho.getProduto().getValor() - valorDescontoCumulativo;
	}

	private boolean verificaSeOTipoDeDescontoDoCupomEPercentual(Cupom cupom) {
		return TipoDesconto.PERCENTUAL.equals(cupom.getTipoDesconto());
	}

	private Double aplicaDescontoPercentualDoCupomNoItemDoCarrinho(ItemCarrinho itemCarrinho, Cupom cupom) {
		return itemCarrinho.getProduto().getValor() * (cupom.getDesconto() / 100);
	}

	private boolean existeCategoriaComCupom(ItemCarrinho itemCarrinho) {
		return !ObjectUtil.isObjectNull(itemCarrinho.getProduto().getCategoria()) && 
				ListaUtil.possuiItens(itemCarrinho.getProduto().getCategoria().getCupons());
	}

}
