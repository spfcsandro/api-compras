package com.compras.util;

import java.util.List;

public class ListaUtil {

	public static boolean possuiItens(List<?> lista) {
		return (lista != null && !lista.isEmpty());
	}
}