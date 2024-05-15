package com.residencia.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entities.Cliente;
import com.residencia.ecommerce.exceptions.NoSuchElementException;
import com.residencia.ecommerce.repositories.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepo;

    public List<Cliente> listarClientes() {
		return clienteRepo.findAll();
	}

	public Cliente buscarClientePorId(Long id) {
		return clienteRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Cliente", id));

		
	}


	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepo.save(cliente);
	}

	public Cliente atualizarCliente(Cliente cliente) {
		return clienteRepo.save(cliente);
	}

	public Boolean deletarCliente(Cliente cliente) {
		if (cliente == null) {
			return false;
		}
		Cliente clienteExistente = buscarClientePorId(cliente.getIdCliente());

		if (clienteExistente == null) {
			return false;
		}

		clienteRepo.delete(cliente);

		Cliente clienteContinuaExistindo = buscarClientePorId(cliente.getIdCliente());

		if (clienteContinuaExistindo == null) {
			return true;
		}

		return false;

	}
}