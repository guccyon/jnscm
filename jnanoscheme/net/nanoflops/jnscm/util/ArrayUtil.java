package net.nanoflops.jnscm.util;

import java.util.LinkedList;

public class ArrayUtil {
	
	public static String join(LinkedList<String> list, String delimiter) {
		if (list.size() == 0) return "";
		StringBuilder sb = new StringBuilder(list.removeFirst());
		for(String s: list) {
			sb.append(delimiter);
			sb.append(s);
		}
		return sb.toString();
	}
}
