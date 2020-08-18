package com.compras.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.compras.filter.AuthorizationFilter;
import com.compras.service.UsuarioService;
import com.compras.service.security.PasswordEncoderService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PasswordEncoderService passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers(HttpMethod.POST, "/v1/usuarios/cadastrar-cliente")
		.antMatchers(HttpMethod.POST, "/v1/usuarios")
		.antMatchers(HttpMethod.POST, "/v1/usuarios/login")
		.antMatchers("/v2/api-docs", 
				 "/configuration/ui", 
				 "/swagger-resources/**",
				 "/configuration/security",
				 "/swagger-ui.html",
				  "/webjars/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/v2/api-docs", 
					 "/swagger-resources/configuration/ui", 
					 "/swagger-resources", 
					 "/swagger-resources/configuration/security", 
					 "/swagger-ui.html", "/webjars/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable();
		http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	/*	CharacterEncodingFilter filter = new CharacterEncodingFilter(); 
		filter.setEncoding("UTF-8"); 
		filter.setForceEncoding(true); 
		http.addFilterBefore(filter, CsrfFilter.class); */
	}
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	
}
