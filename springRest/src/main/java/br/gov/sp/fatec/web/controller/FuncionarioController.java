package br.gov.sp.fatec.web.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import br.gov.sp.fatec.model.Funcionario;
import br.gov.sp.fatec.service.FuncionarioService;
import br.gov.sp.fatec.view.View;

@RestController
@RequestMapping(value = "/funcionario")
public class FuncionarioController {
	@Autowired
	private FuncionarioService funcionarioService;

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
	
	@RequestMapping(value = "/get")
	public ResponseEntity<Funcionario> pesquisar(@RequestParam(value="id", defaultValue="0") Long id) {
		return new ResponseEntity<Funcionario>(this.funcionarioService.getFuncionario(id), HttpStatus.OK);
	}
	
	@JsonView({View.Main.class})
	@RequestMapping(value = "/getAll")
	public ResponseEntity<Collection<Funcionario>> getAll() {
		return new ResponseEntity<Collection<Funcionario>>(this.funcionarioService.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.All.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario save(@RequestBody Funcionario func, HttpServletRequest request, HttpServletResponse response) {
		func = funcionarioService.save(func);
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/funcionario/get?id=" + func.getId());
		return func;
	}
	
}
