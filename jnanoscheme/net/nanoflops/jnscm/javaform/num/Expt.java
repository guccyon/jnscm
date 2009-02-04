package net.nanoflops.jnscm.javaform.num;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.exception.EvalException;
import net.nanoflops.jnscm.javaform.Car;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Num;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;

public class Expt extends JavaForm{

	public SymbolExp invoke(Context ctx, List args) {
		if (args.length() != 2) throw new EvalException("Illegal arguments number -> " + args);
		int n1 = ((Num)args.car()).valueOf();
		int n2 = ((Num)new Car(1).car(args)).valueOf();
		return new Num((int)Math.pow(n1, n2));
	}

}
