package br.gov.sp.fatec.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.model.Autorizacao;
import br.gov.sp.fatec.service.AutorizacaoService;

@RestController
// Um @RequestMapping(value = "/autorizacao") poderia ser incluido como base
public class AutorizacaoController {
	
	@Autowired
	private AutorizacaoService autorizacaoService;

	public void setAutorizacaoService(AutorizacaoService autorizacaoService) {
		this.autorizacaoService = autorizacaoService;
	}
	
	@RequestMapping(value = "/")
	public String hello() {
		return "Olá";
	}
	
	@RequestMapping(value = "/autorizacao/get/{nome}")
	public ResponseEntity<Collection<Autorizacao>> pesquisar(@PathVariable("nome") String nome) {
		// Seria possivel incluir um try/catch e retornar um HttpStatus.INTERNAL_SERVER_ERROR, por exemplo
		return new ResponseEntity<Collection<Autorizacao>>(autorizacaoService.buscar(nome), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/autorizacao/getAll")
	public ResponseEntity<Collection<Autorizacao>> getAll() {
		return new ResponseEntity<Collection<Autorizacao>>(autorizacaoService.todos(), HttpStatus.OK);
	}
	
}
