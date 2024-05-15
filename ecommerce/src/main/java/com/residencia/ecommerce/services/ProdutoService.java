package com.residencia.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.ecommerce.entities.Produto;
import com.residencia.ecommerce.exceptions.NoSuchElementException;
import com.residencia.ecommerce.repositories.ProdutoRepository;

import io.jsonwebtoken.io.IOException;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepo;

	public List<Produto> listarProdutos() {
		return produtoRepo.findAll();
	}

	public Produto buscarProdutoPorId(Long id) {
		return produtoRepo.findById(id).orElseThrow(()-> new NoSuchElementException("produto", id));
		
	}

	public Produto salvarProduto(Produto produto) {
		return produtoRepo.save(produto);
	}

	public Produto atualizarProduto(Produto produto) {
		return produtoRepo.save(produto);
	}

	public Boolean deletarProduto(Produto produto) {
		if (produto == null) {
			return false;
		}
		Produto produtoExistente = buscarProdutoPorId(produto.getIdProduto());

		if (produtoExistente == null) {
			return false;
		}

		produtoRepo.delete(produto);

		Produto produtoContinuaExistindo = buscarProdutoPorId(produto.getIdProduto());

		if (produtoContinuaExistindo == null) {
			return true;
		}

		return false;

	}

	public Produto salvarProdutoComFoto(String strProduto, MultipartFile arqImg) throws java.io.IOException {
		Produto produto = new Produto();

		try {
			ObjectMapper objMp = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			produto = objMp.readValue(strProduto, Produto.class);
		} catch (IOException e) {
			System.out.println("Erro ao converter a string Produto: " + e.toString());
		}

		produto.setImagem(arqImg.getBytes());

		return produtoRepo.save(produto);
	}

}
