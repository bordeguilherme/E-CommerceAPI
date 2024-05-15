package com.residencia.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.dto.RelatorioPedidoDTO;
import com.residencia.ecommerce.entities.Pedido;
import com.residencia.ecommerce.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> listarPedidos() {
		return new ResponseEntity<>(pedidoService.listarPedidos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable long id) {
		Pedido pedido = pedidoService.buscarPedidoPorId(id);


			return new ResponseEntity<>(pedido, HttpStatus.OK);
	}

	@GetMapping("/porid")
	public ResponseEntity<Pedido> buscarPedidoPorId(@RequestParam long id) {
		return new ResponseEntity<>(pedidoService.buscarPedidoPorId(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedido) {
		return new ResponseEntity<>(pedidoService.salvarPedido(pedido), HttpStatus.CREATED);
	}
	
	@GetMapping("/relatorio/{id}")
	public ResponseEntity<RelatorioPedidoDTO> geraRelatorio(@PathVariable Long id) {
		return new ResponseEntity<>(pedidoService.gerarRelatorioPedido(id), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Pedido> atualizar(@RequestBody Pedido pedido) {
		return new ResponseEntity<>(pedidoService.atualizarPedido(pedido), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deletarPedido(@RequestBody Pedido pedido) {
		if (Boolean.TRUE.equals(pedidoService.deletarPedido(pedido)))
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
		else
			return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
	}
}
