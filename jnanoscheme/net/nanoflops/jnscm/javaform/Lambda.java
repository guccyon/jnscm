package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.func.Closure;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;

public class Lambda extends JavaForm {

	public SymbolExp invoke(Context ctx, List args) {
		List params = (List)args.car();
		List sexps = (List)args.cdr();
		return new Closure(ctx, createProcedure(sexps) , params);
	}
	
	private static JavaForm createProcedure(final List sexps) {
		return new JavaForm(){
			public SymbolExp invoke(Context ctx, List args) {
				return new Begin().invoke(ctx, sexps);
			}
		};
	}
}
