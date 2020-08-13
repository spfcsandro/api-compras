package com.compras.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.compras.util.HashUtil;

@Configuration
public class PasswordEncoderConfiguration implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		return HashUtil.gerarHash(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return HashUtil.gerarHash(rawPassword.toString()).equals(encodedPassword);
	}

}
