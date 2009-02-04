package net.nanoflops.jnscm.type.atom;

import net.nanoflops.jnscm.type.SymbolExp;

public abstract class Atom implements SymbolExp {
	public SymbolExp duplicate() {
		return this;
	}
}
