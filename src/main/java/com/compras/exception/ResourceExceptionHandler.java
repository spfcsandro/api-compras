package com.compras.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Erro> notFound(NotFoundException nf, HttpServletRequest request){
		Erro erro = new Erro(HttpStatus.NOT_FOUND.value(), nf.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Erro> validation(MethodArgumentNotValidException methodArg, HttpServletRequest request) {
		ValidacaoErro erro = new ValidacaoErro(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodArg.getMessage(), System.currentTimeMillis());
		for (FieldError x : methodArg.getBindingResult().getFieldErrors()) {
			erro.adicionarErro(x.getField(), x.getDefaultMessage());
		}		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<Erro> dataIntegrity(DataIntegrityException dataInteg, HttpServletRequest request) {
		Erro erro = new Erro(HttpStatus.BAD_REQUEST.value(), dataInteg.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Erro> dataIntegrity(BadCredentialsException badCred, HttpServletRequest request) {
		Erro erro = new Erro(HttpStatus.UNAUTHORIZED.value(), badCred.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Erro> handleAccessDeniedException(AccessDeniedException ex) {
		Erro erro = new Erro(HttpStatus.FORBIDDEN.value(), ex.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
	}
}
