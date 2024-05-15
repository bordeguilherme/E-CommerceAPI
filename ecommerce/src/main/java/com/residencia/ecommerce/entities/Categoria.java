package com.residencia.ecommerce.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCategoria", scope=Categoria.class)
@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Long idCategoria;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@OneToMany(mappedBy = "categoria")
	private List<Produto> produtos;

	// constructor
	public Categoria() {
	}

	public Categoria(Long idCategoria, String nome, String descricao, List<Produto> produtos) {
		this.idCategoria = idCategoria;
		this.nome = nome;
		this.descricao = descricao;
		this.produtos = produtos;
	}

	// get e set
	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
