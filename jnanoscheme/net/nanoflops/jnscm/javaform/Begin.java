package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;

public class Begin extends JavaForm {

	// 引数はContextにパラメータとしてバインド済み
	public SymbolExp invoke(Context ctx, List args) {
		return recursiveEval(ctx, args);
	}

	private SymbolExp recursiveEval(Context ctx, List sexps) {
		SymbolExp result = sexps.car().eval(ctx);
		return sexps.cdr() == Symbol.NIL ?
				result : recursiveEval(ctx, (List)sexps.cdr());
	}
}
