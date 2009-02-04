package net.nanoflops.jnscm.type.func;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.exception.EvalException;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.list.List;

public class SpecialForm extends Function {

	public SpecialForm(Context ctx, Procedure proc) {
		super(ctx, proc);
	}

	public SymbolExp apply(Context parent, List args) {
		try {
			return proc.invoke(parent, args);
		} catch (ClassCastException cce) {
			throw new EvalException("unexpected type: " + cce.getMessage());
		}
	}

	public String toString() {
		return "#<special-form>";
	}
}
