package net.nanoflops.jnscm.javaform.num;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Num;
import net.nanoflops.jnscm.type.atom.Str;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;

public class Divide extends JavaForm {

	public SymbolExp invoke(Context ctx, List args) {
		if (args.length() <= 1) return new Str("Not enough arguments number");
		Num accum = (Num) args.car();
		return new Num(proc(accum.valueOf(), (List)args.cdr()));
	}
	
	private int proc(int accum, List args) {
		if (args == Symbol.NIL) return accum;
		return proc(accum / ((Num)args.car()).valueOf(), (List)args.cdr());
	}
}
