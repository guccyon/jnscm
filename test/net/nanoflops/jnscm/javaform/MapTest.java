package net.nanoflops.jnscm.javaform;

import static org.junit.Assert.assertEquals;
import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.ContextLoader;
import net.nanoflops.jnscm.parser.Parser;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.list.Cons;
import net.nanoflops.jnscm.type.list.List;

import org.junit.Before;
import org.junit.Test;

public class MapTest {
	private Context ctx = ContextLoader.createGlobal();
	
	SymbolExp list;
	
	@Before
	public void start(){
		SymbolExp fn = Parser.parse("(lambda (x) (+ x 1))");
		SymbolExp l = Parser.parse("(quote (1 3 5 7 9 11))");
		list =  new Cons(fn.eval(ctx),
				new Cons(l.eval(ctx)));
	}

	@Test
	public void testEval() {
		assertEquals(new Map().invoke(ctx, (List)list).toString(), 
				"(2 4 6 8 10 12)");
	}

}
