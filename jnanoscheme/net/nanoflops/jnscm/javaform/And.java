package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;
import net.nanoflops.jnscm.util.ConditionUtil;

public class And extends JavaForm {

	public SymbolExp invoke(Context ctx, List args) {
		SymbolExp result = args.car();
		if (args.cdr() == Symbol.NIL ||	
				ConditionUtil.not(result)) return result;
		return invoke(ctx, (List) args.cdr());
	}
}
