package net.nanoflops.jnscm.type.func;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.exception.EvalException;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.list.List;

public class Closure extends Function {
	private List parameters;

	public Closure(Context ctx, Procedure proc) {
		this(ctx, proc, Symbol.NIL);
	}

	public Closure(Context ctx, Procedure proc, List params) {
		super(ctx, proc);
		parameters = params;
	}

	@Override
	public SymbolExp apply(Context parent, List args) {
		try {
			if (parameters != Symbol.NIL && parameters.length() != args.length()) 
				throw new EvalException("Illegal arguments number :" + parameters);
			Context activation = bindParameter(new Context(super.lexical), parameters, args);
			return proc.invoke(activation, args);
		} catch (ClassCastException cce) {
			throw new EvalException("unexpected type: " + cce.getMessage());
		}
	}
	
	private Context bindParameter(Context ctx, List params, List args) {
		if (params == Symbol.NIL) return ctx;
		ctx.set((Symbol) params.car(), args.car());
		return bindParameter(ctx, (List)params.cdr(), (List)args.cdr());
	}

	public String toString() {
		return "#<lexical-closure> [parameters] " +
				((parameters.car() == Symbol.NIL) ? "(void)" : parameters);
				
	}
}
