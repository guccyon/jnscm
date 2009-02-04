package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Bool;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;

public class Eq extends JavaForm {

	public SymbolExp invoke(Context ctx, List args) {
		SymbolExp s1 = args.car();
		SymbolExp s2 = new Car(1).car(args);
		return s1 == s2 ? Bool.T : Bool.F;
	}
}
