package com.compras.exception;

public class NegocioException extends RuntimeException{

	public NegocioException(String message) {
		super(message);
	}
}