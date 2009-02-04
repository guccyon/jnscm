package net.nanoflops.jnscm.util;

import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Bool;
import net.nanoflops.jnscm.type.atom.Symbol;


public class ConditionUtil {
	public static boolean is(SymbolExp sexp) {
		if (sexp == Bool.F || sexp == Symbol.NIL) {
			return false;
		}
		return true;
	}
	
	public static boolean not(SymbolExp sexp) {
		return !is(sexp);
	}
}
