package com.residencia.ecommerce.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.dto.RelatorioPedidoDTO;
import com.residencia.ecommerce.entities.ItemPedido;
import com.residencia.ecommerce.entities.Pedido;
import com.residencia.ecommerce.exceptions.NoSuchElementException;
import com.residencia.ecommerce.repositories.ItemPedidoRepository;
import com.residencia.ecommerce.repositories.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepo;

	@Autowired
	ItemPedidoRepository itemPedidoRepo;

	@Autowired
	EmailService emailService;

	public List<Pedido> listarPedidos() {
		return pedidoRepo.findAll();
	}

	public Pedido buscarPedidoPorId(Long id) {
		return pedidoRepo.findById(id).orElseThrow(() -> new NoSuchElementException("pedido", id));

	}

	public Pedido salvarPedido(Pedido pedido) {
		pedido = pedidoRepo.save(pedido);
		return pedido;
	}

	public Pedido atualizarPedido(Pedido pedido) {
		return pedidoRepo.save(pedido);
	}

	public Boolean deletarPedido(Pedido pedido) {
		if (pedido == null) {
			return false;
		}
		Pedido pedidoExistente = buscarPedidoPorId(pedido.getIdPedido());

		if (pedidoExistente == null) {
			return false;
		}

		pedidoRepo.delete(pedido);

		Pedido pedidoContinuaExistindo = buscarPedidoPorId(pedido.getIdPedido());

		if (pedidoContinuaExistindo == null) {
			return true;
		}

		return false;

	}

	public RelatorioPedidoDTO gerarRelatorioPedido(Long idPedido) {
		Pedido pedido = pedidoRepo.findById(idPedido).get();
		RelatorioPedidoDTO relatorio = new RelatorioPedidoDTO();
		relatorio.setIdPedido(pedido.getIdPedido());
		relatorio.setDataPedido(pedido.getDataPedido());

		Double valorTotal = 0.0;

		List<ItemPedidoDTO> itensRelatorio = new ArrayList<>();

		for (ItemPedido item : pedido.getItemPedido()) {

	
			Double valorBruto = (item.getPrecoVenda()) * (item.getQuantidade());
			Double valorLiquido = valorBruto - (valorBruto * item.getPercentualDesconto());

			ItemPedidoDTO itemRelatorio = new ItemPedidoDTO();

			itemRelatorio.setCodigoProduto(item.getProduto().getIdProduto());
			itemRelatorio.setNomeProduto(item.getProduto().getNome());
			itemRelatorio.setPrecoVenda(item.getPrecoVenda());
			itemRelatorio.setQuantidade(item.getQuantidade());
			itemRelatorio.setValorBruto(valorBruto);
			itemRelatorio.setPercentualDesconto(item.getPercentualDesconto());
			itemRelatorio.setValorLiquido(valorLiquido);

			atualizaItemPedido2(item, valorBruto, valorLiquido);

			itensRelatorio.add(itemRelatorio);

			valorTotal += valorLiquido;
		}

		relatorio.setValorTotal(valorTotal);
		relatorio.setListaItemPedido(itensRelatorio);

		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH");
		Integer hora = Integer.parseInt(agora.format(format));
		String mensagem = "";

		if (hora >= 6 && hora < 13)
			mensagem = "Bom dia";
		else if (hora >= 13 && hora < 19)
			mensagem = "Boa tarde";
		else
			mensagem = "Boa noite";

		emailService.enviarEmail("projetogrupo5.2023.2.api@gmail.com", mensagem, relatorio.toString());

		return relatorio;
	}

	private void atualizaItemPedido2(ItemPedido item, Double valorBruto, Double valorLiquido) {
		ItemPedido itemPedido = itemPedidoRepo.findById(item.getIdItemPedido()).orElse(null);

		if (itemPedido != null) {
			itemPedido.setValorBruto(valorBruto);
			itemPedido.setValorLiquido(valorLiquido);
			itemPedidoRepo.save(itemPedido);
		}

	}

}
