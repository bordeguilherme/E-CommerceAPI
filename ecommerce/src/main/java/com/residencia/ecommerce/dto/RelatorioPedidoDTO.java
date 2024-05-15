package com.residencia.ecommerce.dto;


import java.util.List;
import java.util.Date;

public class RelatorioPedidoDTO {

	private Long idPedido;
	private Date dataPedido;
	private Double valorTotal;
	private List<ItemPedidoDTO> listaItemPedido;
	
	public RelatorioPedidoDTO() {
	}

	public RelatorioPedidoDTO(Long idPedido, Date dataPedido, Double valorTotal,
			List<ItemPedidoDTO> listaItemPedido) {
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
		this.listaItemPedido = listaItemPedido;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItemPedidoDTO> getListaItemPedido() {
		return listaItemPedido;
	}

	public void setListaItemPedido(List<ItemPedidoDTO> listaItemPedido) {
		this.listaItemPedido = listaItemPedido;
	}

	@Override
	public String toString() {
		
		String itensPedidos = "";
		
		for(ItemPedidoDTO item : this.listaItemPedido) {
			itensPedidos += "\n  -- " + item.toString();
		}
		
		return "-Relatório de Pedido-\n\nId do Pedido: " + idPedido + ""
				+ "\nData do Pedido: " + dataPedido + "\nValor Total:" + valorTotal
				+ "\nLista de Itens do Pedido:\n" + itensPedidos;
		
	}
	
}
