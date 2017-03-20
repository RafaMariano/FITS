package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	public List<Usuario> buscar(String nome) {
		return usuarioRepo.findByNomeContainsIgnoreCase(nome);
	}
	
	@Override
	public Usuario buscar(Long id) {
		return usuarioRepo.findOne(id);
	}

	@Override
	public List<Usuario> todos() {
		List<Usuario> retorno = new ArrayList<Usuario>();
		for(Usuario usuario: usuarioRepo.findAll()) {
			retorno.add(usuario);
		}
		return retorno;
	}
	
	@Override
	public Usuario salvar(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}

	/**
	 * @return the usuarioRepo
	 */
	public UsuarioRepository getUsuarioRepo() {
		return usuarioRepo;
	}

	/**
	 * @param usuarioRepo the usuarioRepo to set
	 */
	public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}

}
