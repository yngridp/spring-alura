package com.aIura.aiura.medico;

import com.aIura.aiura.endereco.Endereco;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

//entidade JPA
@Table(name="medicos")
@Entity(name="Medico")
@Getter //nao precisa criar os getters manualmente
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String crm;
	private String telefone;
	
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	@Embedded
	private Endereco endereco;
	
	private Boolean ativo;
	
	public Medico() {}  
	
	public Medico(DadosCadastradoMedico dados) {
		   this.ativo = true;
		   this.nome = dados.nome();
		   this.email = dados.email();
		   this.crm = dados.crm();
		   this.telefone = dados.telefone();
		   this.especialidade = dados.especialidade();
		   this.endereco = new Endereco(dados.endereco());
		}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCrm() {
		return crm;
	}

	public String getTelefone() {
		return telefone;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void atualizarInformacoes(@Valid DadosAtualizacaoMedico dados) {
		if(dados.nome() != null) {
			this.nome = dados.nome();
		}
		if(dados.telefone() != null) {
			this.telefone = dados.telefone();
		}
		if(dados.endereco() != null) {
			this.endereco.atualizarInformacoes(dados.endereco());
		}
		
		
		
	}

	public void excluir() {
		this.ativo = false;
		
	}
	

	
}
