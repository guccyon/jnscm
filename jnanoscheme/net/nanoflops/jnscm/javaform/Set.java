package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.exception.EvalException;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;

public class Set extends JavaForm {

	public SymbolExp invoke(Context ctx, List args) {
		Symbol sym = (Symbol)args.car();
		if (ctx.hasOwnKey(sym)) {
			ctx.set(sym, (List)args.cdr().eval(ctx));
			return sym;
		} else if (ctx.getParent() != null) {
			return invoke(ctx.getParent(), args);	
		}
		throw new EvalException("symbol not defined -> " + sym);
	}
}
