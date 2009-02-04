package net.nanoflops.jnscm.type.list;

import net.nanoflops.jnscm.type.SymbolExp;

public interface List extends SymbolExp {

	public int length();
	
	public SymbolExp car();
	
	public SymbolExp cdr();
}
