package net.nanoflops.jnscm.type.func;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.list.List;

public interface Procedure {
	public SymbolExp invoke(Context ctx, List args);
}
