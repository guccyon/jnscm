package net.nanoflops.jnscm.type.atom;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;

public class Str extends Atom {
	
	private String value = "";
	
	public Str(String value) {
		this.value = value;
	}

	public SymbolExp eval(Context ctx) {
		return this;
	}

	public String toString() {
		return value;
	}
}
