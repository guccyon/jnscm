package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Bool;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;
import net.nanoflops.jnscm.util.ConditionUtil;

public class Not extends JavaForm {

	public SymbolExp invoke(Context ctx, List args) {
		return ConditionUtil.not(args.car()) ? Bool.T : Bool.F;
	}

}
