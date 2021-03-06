package com.compras.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.compras.dto.UsuarioClienteDTO;
import com.compras.dto.UsuarioLoginResponseDTO;
import com.compras.enums.Role;
import com.compras.exception.NegocioException;
import com.compras.exception.NotFoundException;
import com.compras.model.Usuario;
import com.compras.repository.UsuarioRepository;
import com.compras.service.security.JwtManager;
import com.compras.util.HashUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService implements UserDetailsService{

	private final UsuarioRepository usuarioRepository;
	private final AuthenticationManager authManager;
	private final JwtManager jwtManager;
	private final MessageSource messageSource;
	
	public Page<Usuario> listarUsuarios(Pageable pageable){
		return usuarioRepository.findAll(pageable);
	}
	
	public Usuario carregarUsuario (Long id){
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(() -> new NotFoundException(""));
	}
	
	public Usuario salvarUsuario(Usuario usuario) {
		validarUsuarioComMesmoCodigo(usuario);
		usuario.setSenha(HashUtil.gerarHash(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}
	
	private void validarUsuarioComMesmoCodigo(Usuario usuario) {
		Optional<Usuario> usuarioBanco = usuarioRepository.findByEmail(usuario.getEmail());
		
		if(usuarioBanco.isPresent()){
			throw new NegocioException(messageSource.getMessage("negocio.validacao.email.existente", new Object[] {"'"+ usuario.getEmail() +"'"}, null));
		}
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
		usuario.setRole(Role.CLIENTE);
		return usuario;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(email)
								.orElseThrow(() -> new UsernameNotFoundException(""));
		
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name()));
        
		User userDetails = new User(usuario.getEmail(), usuario.getSenha(), authorities);
		return userDetails;
	}

	public UsuarioLoginResponseDTO autenticar(String username, String senha) {
		UsernamePasswordAuthenticationToken userAtuth = new UsernamePasswordAuthenticationToken(username, senha);
		Authentication auth = authManager.authenticate(userAtuth);
		SecurityContextHolder.getContext().setAuthentication(auth);
		User userDetails = (User) auth.getPrincipal();
		
		String email = userDetails.getUsername();
		List<String> roles = userDetails.getAuthorities()
									.stream()
									.map(authority ->  authority.getAuthority())
									.collect(Collectors.toList());
		
		UsuarioLoginResponseDTO usuarioLoginResponseDTO = jwtManager.createToken(email, roles);
		
		return usuarioLoginResponseDTO;
	}
}
