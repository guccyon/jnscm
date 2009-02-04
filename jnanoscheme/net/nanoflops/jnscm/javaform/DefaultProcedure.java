package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;

public class DefaultProcedure extends JavaForm {
	private List list;
	
	public DefaultProcedure(List list) {
		this.list = list;
	}

	public SymbolExp invoke(Context ctx, List args) {
		return recursiveEval(ctx, list);
	}

	private SymbolExp recursiveEval(Context ctx, List sexps) {
		SymbolExp result = sexps.car().eval(ctx);
		return sexps.cdr() == Symbol.NIL ?
				result : recursiveEval(ctx, (List)sexps.cdr());
	}

}
