package dev.joaomarcelo.controleFinanceiro.util;

import java.lang.reflect.Field;

public class FormatarPalavras {

	public static Object caixaAltaClasse(Object obj) {
		try {
			for (Field f : obj.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				if (f.getType().equals(String.class)) {
					f.set(obj, f.get(obj).toString().toUpperCase());
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();

		}
		return obj;
	}

	public static String caixaAlta(String string) {
		return string.toUpperCase();
	}


	public static String salvarNomeFormatado(String string) {
		
		string.toLowerCase();

		String[] arr = string.split(" ");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();

	}
}
