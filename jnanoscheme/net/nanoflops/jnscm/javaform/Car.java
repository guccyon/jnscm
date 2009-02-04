package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.list.Cons;
import net.nanoflops.jnscm.type.list.List;

public class Car extends Cdr {
	
	public Car() {
		super(0);
	}
	
	public Car(int depth) {
		super(depth);
	}

	public SymbolExp invoke(Context ctx, List args) {
		return recurCdr((List)args.car(), 0).car();
	}
	
	public SymbolExp car(List args) {
		return this.invoke(null, new Cons(args));
	}
}
