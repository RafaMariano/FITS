package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.model.Usuario;

public interface UsuarioService {
	
	public List<Usuario> buscar(String nome);
	public Usuario buscar(Long id);
	public List<Usuario> todos();
	public Usuario salvar(Usuario usuario);

}
