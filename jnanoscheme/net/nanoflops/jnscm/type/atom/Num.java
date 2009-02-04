package net.nanoflops.jnscm.type.atom;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;

public class Num extends Atom {
	
	private int value;
	
	public Num(int value) {
		this.value = value;
	}

	public SymbolExp eval(Context ctx) {
		return this;
	}

	public String toString() {
		return String.valueOf(value);
	}
	
	public int valueOf() {
		return value;
	}
}
