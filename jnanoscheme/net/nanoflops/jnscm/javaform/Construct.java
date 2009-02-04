package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.Cons;
import net.nanoflops.jnscm.type.list.List;

public class Construct extends JavaForm {
	public SymbolExp invoke(Context ctx, List args) {
		return new Cons(args.car(), ((List)args.cdr()).car());
	}
}
