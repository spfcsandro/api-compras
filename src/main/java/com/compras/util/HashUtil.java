package com.compras.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

	public static String gerarHash(String valor) {
		return DigestUtils.sha256Hex(valor);
	}
}
