package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.ContextLoader;
import net.nanoflops.jnscm.parser.Parser;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.list.List;

import org.junit.Test;

public class AppendTest {
	private Context ctx = ContextLoader.createGlobal();

	@Test
	public void testInvoke() {
		String src = "((1 2 3 4 5)(a b c d e) (9 9 9) (100))";
		List sexp = (List)Parser.parse(src);
		SymbolExp result = new Append().invoke(ctx, sexp);
		System.out.println(result);
		//fail("Ç‹Çæé¿ëïÇ≥ÇÍÇƒÇ¢Ç‹ÇπÇÒÅB");
	}

}
