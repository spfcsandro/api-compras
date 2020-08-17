package com.compras;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.compras.enums.ROLE;
import com.compras.model.Usuario;
import com.compras.repository.UsuarioRepository;

@SpringBootApplication
public class ComprasApplication implements CommandLineRunner {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(ComprasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		if(!usuarioRepository.findByEmail("spfcsandro@gmail.com").isPresent()) {		
			Usuario usuario = new Usuario();
			usuario.setNome("Sandro");
			usuario.setSenha("123456");
			usuario.setEmail("spfcsandro@gmail.com");
			usuario.setRole(ROLE.ADMINISTRADOR);	
			
			usuarioRepository.save(usuario);
		}
	}
}
