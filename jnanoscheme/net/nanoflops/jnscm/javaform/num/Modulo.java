package net.nanoflops.jnscm.javaform.num;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.javaform.Car;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Num;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;

public class Modulo extends JavaForm {

	public SymbolExp invoke(Context ctx, List args) {
		Num a = (Num)args.car();
		Num b = (Num) new Car(1).car(args);
		return new Num(a.valueOf() % b.valueOf());
	}

}
