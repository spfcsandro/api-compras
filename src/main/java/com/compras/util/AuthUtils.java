package com.compras.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {

	public static String usuarioLogado() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
	}
}
