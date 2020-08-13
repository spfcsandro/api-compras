package com.compras.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compras.dto.UsuarioClienteDTO;
import com.compras.model.Usuario;
import com.compras.service.UsuarioService;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioResource {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Usuario> salvarUsuario(@Valid @RequestBody Usuario usuario){
		Usuario usuarioCriado = usuarioService.salvarUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable(name = "id") Long id, 
			@RequestBody Usuario usuario){
		
		usuario.setId(id);
		Usuario usuarioAtualizado = usuarioService.atualizarUsuario(usuario);
		return ResponseEntity.ok(usuarioAtualizado);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> carregarUsuario(@PathVariable(name = "id") Long id){
		Usuario usuarioCarregado = usuarioService.carregarUsuario(id);
		return ResponseEntity.ok(usuarioCarregado);
	}
	
	@GetMapping
	public ResponseEntity<Page<Usuario>> listarUsuarios(Pageable pageable){
		Page<Usuario> usuarios = usuarioService.listarUsuarios(pageable);
		return ResponseEntity.ok(usuarios);
	}
	
	@DeleteMapping("/{id}")
	public void deletarUsuario(@PathVariable(name = "id") Long id){
		usuarioService.deletarUsuario(id);
	}
	
	@PostMapping("/cadastrar-cliente")
	public ResponseEntity<Usuario> login(@Valid @RequestBody UsuarioClienteDTO usuario){
		Usuario usuarioCadastrado = usuarioService.cadastrarCliente(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCadastrado);
	}
	
}
