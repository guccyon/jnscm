package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.exception.EvalException;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.Cons;
import net.nanoflops.jnscm.type.list.List;

public class Append extends JavaForm {
	
	private boolean immutable = true;
	
	public Append() {}
	
	public Append(boolean immutable) {
		this.immutable = immutable;
	}

	public SymbolExp invoke(Context ctx, List args) {
		Cons base = immutable ? (Cons)((Cons)args.duplicate()).car() : (Cons)args.car();
		return app(base, (List)args.cdr());
	}
	
	private Cons app(Cons list, List add) {
		if (add.cdr() instanceof Cons) {
			return app(list.append(add.car()), (List)add.cdr());
		} else {
			if (add.cdr() != Symbol.NIL)
				throw new EvalException("list required, but got " + add.car());
			return list.append(add.car());
		}
	}
}
