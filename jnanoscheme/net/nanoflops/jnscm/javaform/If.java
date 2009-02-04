package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.exception.EvalException;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;
import net.nanoflops.jnscm.util.ConditionUtil;

public class If extends JavaForm {
	public SymbolExp invoke(Context ctx, List args) {
		int len = args.length();
		if (len < 2 || len > 3) 
			throw new EvalException("Illegal arguments number");
		
		if (ConditionUtil.is(args.car().eval(ctx))) {
			return new Car(1).car(args).eval(ctx);
		} else if(len <= 3){
			return new Car(2).car(args).eval(ctx);
		} else {
			return Symbol.NIL;
		}
	}
}
