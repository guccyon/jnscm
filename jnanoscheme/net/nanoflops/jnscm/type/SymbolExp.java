package net.nanoflops.jnscm.type;

import net.nanoflops.jnscm.Context;

public interface SymbolExp {
	SymbolExp eval(Context ctx);
	String toString();
	SymbolExp duplicate();
}
