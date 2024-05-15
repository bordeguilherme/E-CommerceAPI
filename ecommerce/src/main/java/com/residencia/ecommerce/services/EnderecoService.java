package com.residencia.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.residencia.ecommerce.dto.EnderecoWsDTO;
import com.residencia.ecommerce.entities.Endereco;
import com.residencia.ecommerce.exceptions.NoSuchElementException;
import com.residencia.ecommerce.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepo;

	public List<Endereco> listarEnderecos() {
		return enderecoRepo.findAll();
	}

	public Endereco buscarEnderecoPorId(Long id) {
		return enderecoRepo.findById(id).orElseThrow(()-> new NoSuchElementException("Endereco", id));
		
	}

	public Endereco salvarEndereco(Endereco endereco) {
		Endereco enderecoCep = getEnderecoByCep(endereco);
		return enderecoRepo.save(enderecoCep);
	}

	public Endereco atualizarEndereco(Endereco endereco) {
		return enderecoRepo.save(endereco);
	}

	public Boolean deletarEndereco(Endereco endereco) {
		if (endereco == null) {
			return false;
		}
		Endereco enderecoExistente = buscarEnderecoPorId(endereco.getIdEndereco());

		if (enderecoExistente == null) {
			return false;
		}

		enderecoRepo.delete(endereco);

		Endereco enderecoContinuaExistindo = buscarEnderecoPorId(endereco.getIdEndereco());

		if (enderecoContinuaExistindo == null) {
			return true;
		}

		return false;
	}

	public Endereco getEnderecoByCep(Endereco endereco) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://viacep.com.br/ws/" + endereco.getCep() + "/json";
		EnderecoWsDTO enderecoDto = restTemplate.getForObject(uri, EnderecoWsDTO.class);
		

		try {

			if (enderecoDto != null) {
				endereco.setCep(enderecoDto.getCep());
				endereco.setRua(enderecoDto.getLogradouro());
				endereco.setBairro(enderecoDto.getBairro());
				endereco.setCidade(enderecoDto.getLocalidade());
				endereco.setComplemento(enderecoDto.getComplemento());
				endereco.setUf(enderecoDto.getUf());
			} else {
				System.out.println("CEP não encontrado ou inválido");
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao consultar o CEP: " + e.getMessage());
		}

		return endereco;
	}

//	public EnderecoWsDTO consultaCep(String cep) {
//		RestTemplate restTemplate = new RestTemplate();
//		String uri = "https://viacep.com.br/ws/{cep}/json/";
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("cep", cep);
//		EnderecoWsDTO enderecoDto = restTemplate.getForObject(uri, EnderecoWsDTO.class, params);
//
//		return enderecoDto;
//	}
//	
//	
//	public EnderecoWsDTO getEnderecoResumidoPorId(Long id) {
//
//		Endereco endereco = enderecoRepo.findById(id).orElse(null);
//
//		if (endereco != null) {
//			EnderecoWsDTO enderecoResDTO = new EnderecoWsDTO(endereco.getCep(), endereco.getRua(), endereco.getBairro(), endereco.getCidade(), endereco.getUf(), endereco.getComplemento());
//			
//			return enderecoResDTO;
//		}
//		return null;
//	}
}
