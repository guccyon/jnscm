package net.nanoflops.jnscm.javaform.num;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Num;
import net.nanoflops.jnscm.type.atom.Str;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;
import net.nanoflops.jnscm.util.ConditionUtil;

public class Sub extends JavaForm {

	public SymbolExp invoke(Context ctx, List args) {
		if (args.length() == 0) return new Str("Not enough arguments number");
		int accum = ((Num)args.car()).valueOf();
		return ConditionUtil.not(args.cdr()) ?
				new Num(-accum) : new Num(proc(accum, (List)args.cdr()));
	}
	
	private int proc(int accum, List args) {
		if (args == Symbol.NIL) return accum;
		return proc(accum - ((Num)args.car()).valueOf(), (List)args.cdr());
	}
}
