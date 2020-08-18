package com.compras.service.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.compras.util.HashUtil;

@Component
public class PasswordEncoderService implements PasswordEncoder{

	public String encode(CharSequence rawPassword) {
		return HashUtil.gerarHash(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return HashUtil.gerarHash(rawPassword.toString()).equals(encodedPassword);
	}

}
