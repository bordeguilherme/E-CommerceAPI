package com.residencia.ecommerce.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCliente", scope=Cliente.class)
@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long idCliente;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "nome_completo", nullable = false)
	private String nomeCompleto;

	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;

	@Column(name = "telefone", nullable = true)
	private String telefone;

	@Column(name = "data_nascimento", nullable = false)
	private Date dataNascimento;

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;

	@OneToOne
	@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco", unique = true)
	private Endereco endereco;

	// constructor
	public Cliente() {
	}

	public Cliente(Long idCliente, String email, String nomeCompleto, String cpf, String telefone, Date dataNascimento,
			List<Pedido> pedidos, Endereco endereco) {
		this.idCliente = idCliente;
		this.email = email;
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.pedidos = pedidos;
		this.endereco = endereco;
	}

	// get e set
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
