package com.aIura.aiura.medico;

import com.aIura.aiura.endereco.DadosEndereco;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
		@NotNull
		Long id, 
		String nome,
		String telefone,
		DadosEndereco endereco) {

}
