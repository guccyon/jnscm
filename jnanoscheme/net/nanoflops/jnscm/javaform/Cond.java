package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.atom.Undefined;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;
import net.nanoflops.jnscm.util.ConditionUtil;

public class Cond extends JavaForm {

	public SymbolExp invoke(Context ctx, List args) {
		return recursiveEval(ctx, args);
	}

	private SymbolExp recursiveEval(Context ctx, List sexps) {
		if (sexps == Symbol.NIL) return new Undefined();
		List list = (List)sexps.car();
		if (ConditionUtil.is(list.car().eval(ctx))) {
			return ((List)list.cdr()).car().eval(ctx);
		}
		return recursiveEval(ctx, (List)sexps.cdr());
	}
}
