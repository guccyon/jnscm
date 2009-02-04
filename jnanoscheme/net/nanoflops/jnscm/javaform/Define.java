package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.exception.ParseException;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.Cons;
import net.nanoflops.jnscm.type.list.List;

public class Define extends JavaForm {

	public SymbolExp invoke(Context ctx, List args) {
		if (args.length() != 2) {
			throw new ParseException("SyntaxError");
		}
		
		if (args.car() instanceof Symbol) {
			Symbol sym = (Symbol)args.car();
			List cdr = (List)args.cdr();
			SymbolExp sexp = cdr.car().eval(ctx);
			return ctx.set(sym, sexp);
			
		} else if (args.car() instanceof List && args.cdr() instanceof List) {
			List car = (List)args.car();
			Symbol sym = (Symbol)car.car();
			List list = new Cons((List)car.cdr(), args.cdr());
			return ctx.set(sym, new Lambda().invoke(ctx, list));
		} else {
			throw new ParseException("SyntaxError");
		}
	}
}
