package net.nanoflops.jnscm.type.atom;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;

public class Undefined extends Atom {

	public SymbolExp eval(Context ctx) {
		return Symbol.NIL;
	}
	
	public String toString() {
		return "#<undef>";
	}
}
