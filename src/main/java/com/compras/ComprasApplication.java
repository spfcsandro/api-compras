package com.compras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.compras.enums.Role;
import com.compras.model.Usuario;
import com.compras.repository.UsuarioRepository;
import com.compras.util.AuthUtils;
import com.compras.util.HashUtil;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
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
			usuario.setSenha(HashUtil.gerarHash("123456"));
			usuario.setEmail("spfcsandro@gmail.com");
			usuario.setRole(Role.ADMINISTRADOR);	
			
			usuarioRepository.save(usuario);
		}
	}
}
