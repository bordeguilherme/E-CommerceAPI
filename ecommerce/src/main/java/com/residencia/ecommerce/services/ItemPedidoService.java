package com.residencia.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entities.ItemPedido;
import com.residencia.ecommerce.exceptions.NoSuchElementException;
import com.residencia.ecommerce.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository itemPedidoRepo;

    public List<ItemPedido> listarItemPedidos() {
		return itemPedidoRepo.findAll();
	}

	public ItemPedido buscarItemPedidoPorId(Long id) {
		return itemPedidoRepo.findById(id).orElseThrow(()-> new NoSuchElementException("itemPedido", id));
		
	}


	public ItemPedido salvarItemPedido(ItemPedido itemPedido) {
		ItemPedido itemPed = new ItemPedido(itemPedido.getIdItemPedido(), itemPedido.getQuantidade(), itemPedido.getPrecoVenda(), itemPedido.getPercentualDesconto(),
				itemPedido.getProduto(), itemPedido.getPedido());
		
		
		
		return itemPedidoRepo.save(itemPed);
	}

	public ItemPedido atualizarItemPedido(ItemPedido itemPedido) {
		return itemPedidoRepo.save(itemPedido);
	}

	public Boolean deletarItemPedido(ItemPedido itemPedido) {
		if (itemPedido == null) {
			return false;
		}
		ItemPedido itemPedidoExistente = buscarItemPedidoPorId(itemPedido.getIdItemPedido());

		if (itemPedidoExistente == null) {
			return false;
		}

		itemPedidoRepo.delete(itemPedido);

		ItemPedido itemPedidoContinuaExistindo = buscarItemPedidoPorId(itemPedido.getIdItemPedido());

		if (itemPedidoContinuaExistindo == null) {
			return true;
		}

		return false;

	}
}
