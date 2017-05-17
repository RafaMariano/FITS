package br.gov.sp.fatec.web.controller;


import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.model.Produto;
import br.gov.sp.fatec.service.ProdutoService;
import br.gov.sp.fatec.view.View;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@RequestMapping(value = "/get/{id}")
	@JsonView(View.All.class)
	public ResponseEntity<Produto> pesquisar(@PathVariable("id") Long id) {
		return new ResponseEntity<Produto>(this.produtoService.buscar(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get")
	@JsonView(View.All.class)
	public ResponseEntity<Iterator<Produto>> getProduto(@RequestParam(value = "nome", defaultValue = "0") String nome,
			@RequestParam(value = "preco", defaultValue = "0.000001") double preco) {
	
		if (nome.equals("0"))
			if(preco == 1.0E-6)
				return new ResponseEntity<Iterator<Produto>>(HttpStatus.BAD_REQUEST);
			else
				return new ResponseEntity<Iterator<Produto>>(this.produtoService.getProdutoPreco(preco), HttpStatus.OK);
		else
			if(preco == 1.0E-6)
				return new ResponseEntity<Iterator<Produto>>(this.produtoService.getProdutoNome(nome),HttpStatus.OK);
			else
				return new ResponseEntity<Iterator<Produto>>(this.produtoService.getProdutoNomePreco(nome, preco),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAll")
	@JsonView(View.Main.class)
	public ResponseEntity<Iterator<Produto>> getAll() {
		return new ResponseEntity<Iterator<Produto>>(this.produtoService.buscarTodos(), HttpStatus.OK);
	}
	
}