package net.nanoflops.jnscm.javaform;

import static org.junit.Assert.*;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.ContextLoader;
import net.nanoflops.jnscm.parser.Parser;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.list.List;

import org.junit.Test;

public class CondTest {
	private Context ctx = ContextLoader.createGlobal();

	@Test
	public void testInvoke() {
		String src = "((> 0 1)(+ 5 5))(#f 100)";
		List sexp = (List)Parser.parse(src);
		SymbolExp result = new Cond().invoke(ctx, sexp);
		System.out.println(result);
		//fail("‚Ü‚¾À‘•‚³‚ê‚Ä‚¢‚Ü‚¹‚ñB");
	}

}
