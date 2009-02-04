package net.nanoflops.jnscm.type.atom;

import java.util.HashMap;
import java.util.Map;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.list.Cons;
import net.nanoflops.jnscm.type.list.List;


public class Symbol extends Atom {
	
	private final static Map<String, Symbol> cache = new HashMap<String, Symbol>();
	
	public static final Nil NIL = new Nil("nil");
	
	public static Symbol createSymbol(String value) {
		return cache.containsKey(value) ?
				cache.get(value) : new Symbol(value);
	}
	
	private Symbol(String value) {
		this.value = value;
		cache.put(value, this);
	}
	
	private String value;

	public SymbolExp eval(Context ctx) {
		return ctx.lookup(this);
	}

	public String toString() {
		return value;
	}

	/*
	 * NILƒNƒ‰ƒX
	 */
	private static class Nil extends Symbol implements List {
		private Nil(String value) {
			super(value);
		}

		public SymbolExp car() {
			return this;
		}

		public SymbolExp cdr() {
			return this;
		}

		public SymbolExp cons(SymbolExp sexp) {
			return new Cons(sexp, this);
		}
		
		public SymbolExp eval(Context ctx) {
			return this;
		}

		public int length() {
			return 0;
		}
		
		@Override
		public String toString() {
			return "NIL";
		}
	}
}
