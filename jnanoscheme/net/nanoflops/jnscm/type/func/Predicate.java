package net.nanoflops.jnscm.type.func;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Bool;
import net.nanoflops.jnscm.type.atom.Num;
import net.nanoflops.jnscm.type.atom.Str;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.list.List;

public interface Predicate extends Procedure {
	
	public static class NumberP implements Procedure {
		public SymbolExp invoke(Context ctx, List args) {
			return args.car() instanceof Num ? Bool.T : Bool.F;
		}
	}
	
	public static class NullP implements Procedure {
		public SymbolExp invoke(Context ctx, List args) {
			return (args.car() == Symbol.NIL) ? Bool.T : Bool.F;
		}
	}
	
	public static class SymbolP implements Procedure {
		public SymbolExp invoke(Context ctx, List args) {
			return args.car() instanceof Symbol ? Bool.T : Bool.F;
		}
	}
	
	public static class StringP implements Procedure {
		public SymbolExp invoke(Context ctx, List args) {
			return args.car() instanceof Str ? Bool.T : Bool.F;
		}
	}
}