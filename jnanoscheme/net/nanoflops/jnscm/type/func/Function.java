package net.nanoflops.jnscm.type.func;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.list.List;

public abstract class Function implements SymbolExp {
	
	protected Context lexical;
	
	protected Procedure proc;
	
	public Function(Context ctx, Procedure proc) {
		this.lexical = ctx;
		this.proc = proc;
	}

	public SymbolExp eval(Context ctx) {
		return this;
	}
	
	public abstract SymbolExp apply(Context parentScope, List args);
	
	public SymbolExp duplicate() {
		return this;
	}
}
