package com.compras.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compras.dto.UsuarioClienteDTO;
import com.compras.enums.ROLE;
import com.compras.exception.NotFoundException;
import com.compras.model.Usuario;
import com.compras.repository.UsuarioRepository;
import com.compras.util.HashUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	
	public Page<Usuario> listarUsuarios(Pageable pageable){
		return usuarioRepository.findAll(pageable);
	}
	
	public Usuario carregarUsuario (Long id){
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(() -> new NotFoundException(""));
	}
	
	public Usuario salvarUsuario(Usuario usuario) {
		usuario.setSenha(HashUtil.gerarHash(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}
	
	public Usuario atualizarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void deletarUsuario(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		usuarioRepository.delete(usuario.get());
	}
	
	public Usuario login(String email, String senha) {
		return usuarioRepository.findByEmailAndSenha(email, HashUtil.gerarHash(senha));
	}

	public Usuario cadastrarCliente(UsuarioClienteDTO usuarioClienteDTO) {
		return usuarioRepository.save(criarUsuarioCliente(usuarioClienteDTO));
	}

	private Usuario criarUsuarioCliente(UsuarioClienteDTO usuarioClienteDTO) {
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioClienteDTO.getNome());
		usuario.setEmail(usuarioClienteDTO.getEmail());
		usuario.setSenha(HashUtil.gerarHash(usuarioClienteDTO.getSenha()));
		usuario.setRole(ROLE.CLIENTE);
		return usuario;
	}
}
