package com.compras.exception;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

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
	public ResponseEntity<Erro> notFound(NotFoundException notFoundException, HttpServletRequest request){
		Erro erro = new Erro(HttpStatus.NOT_FOUND.value(), notFoundException.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Erro> validation(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest request) {
		ValidacaoErro erro = new ValidacaoErro(HttpStatus.UNPROCESSABLE_ENTITY.value(), methodArgumentNotValidException.getMessage(), System.currentTimeMillis());
		for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			erro.adicionarErro(fieldError.getField(), fieldError.getDefaultMessage());
		}		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<Erro> dataIntegrity(DataIntegrityException dataIntegrityException, HttpServletRequest request) {
		Erro erro = new Erro(HttpStatus.BAD_REQUEST.value(), dataIntegrityException.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Erro> dataIntegrity(BadCredentialsException badCredentialsException, HttpServletRequest request) {
		Erro erro = new Erro(HttpStatus.UNAUTHORIZED.value(), badCredentialsException.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Erro> handleAccessDeniedException(AccessDeniedException accessDeniedException) {
		Erro erro = new Erro(HttpStatus.FORBIDDEN.value(), accessDeniedException.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Erro> constraintViolation(ConstraintViolationException constraintViolationException, HttpServletRequest request) {
		Erro erro = new Erro(HttpStatus.INTERNAL_SERVER_ERROR.value(), constraintViolationException.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}
}
