package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;

public class Cdr extends JavaForm {
	private int depth;
	
	public Cdr() {
		this(1);
	}
	public Cdr(int depth) {
		this.depth = depth;
	}

	public SymbolExp invoke(Context ctx, List args) {
		return recurCdr((List)args.car(), 0);
	}
	
	protected List recurCdr(List args, int dep) {
		if (this.depth <= dep) return args;
		return recurCdr((List)args.cdr(), dep + 1);
	}
	
	public List cdr(List args) {
		return (List)this.invoke(null, args);
	}
}
