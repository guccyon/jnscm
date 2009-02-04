package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.func.Function;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.Cons;
import net.nanoflops.jnscm.type.list.List;

public class Map extends JavaForm {

	public SymbolExp invoke(Context ctx, List args) {
		Function func = (Function)args.car();
		List list = (List)new Car(1).car(args);
		return recurApply(ctx, func, list);
	}
	
	public List recurApply(Context ctx, Function func, List args) {
		if (args == Symbol.NIL) return args;
		SymbolExp result = new Cons(func, new Cons(args.car())).eval(ctx);
		return new Cons(result, recurApply(ctx, func, (List)args.cdr()));
	}
}
