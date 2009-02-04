package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Bool;
import net.nanoflops.jnscm.type.atom.Num;
import net.nanoflops.jnscm.type.atom.Str;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;

public class Equals extends JavaForm {

	public SymbolExp invoke(Context ctx, List args) {
		SymbolExp s1 = args.car();
		SymbolExp s2 = new Car(1).car(args);
		if (s1 == s2) return toBool(true);
		if (s1.getClass() != s2.getClass()) return toBool(false);
		
		if (s1 instanceof Num) {
			return toBool(((Num)s1).valueOf() == ((Num)s2).valueOf());
		} else if (s1 instanceof Str) {
			return toBool(((Str)s1).toString().equals(
						  ((Str)s2).toString()) );
		} else {
			return toBool(false);	
		}
	}
	
	private Bool toBool(boolean bol) {
		return bol ? Bool.T : Bool.F;
	}
}
