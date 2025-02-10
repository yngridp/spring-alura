package com.aIura.aiura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aIura.aiura.medico.DadosAtualizacaoMedico;
import com.aIura.aiura.medico.DadosCadastradoMedico;
import com.aIura.aiura.medico.DadosListagemMedico;
import com.aIura.aiura.medico.Medico;
import com.aIura.aiura.medico.MedicoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastradoMedico dados) {
		repository.save(new Medico(dados));
	}

	/*@GetMapping
	public List<DadosListagemMedico> Listar(){
		return repository.findAll().stream().map(DadosListagemMedico::new).toList(); 
		//convertendo lista de medicos para uma lista de dadoslistagemmedico	
	}*/
	//list com paginação 
	@GetMapping
	public Page<DadosListagemMedico> Listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new); 	
	}
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
	}
	//metodo excluir totalmente do banco de dados
	/*@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}*/
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		 medico.excluir();
	}
	
}
