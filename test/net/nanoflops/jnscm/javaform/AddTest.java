package net.nanoflops.jnscm.javaform;

import static org.junit.Assert.assertEquals;
import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.ContextLoader;
import net.nanoflops.jnscm.javaform.num.Add;
import net.nanoflops.jnscm.type.atom.Num;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.func.Closure;
import net.nanoflops.jnscm.type.list.Cons;
import net.nanoflops.jnscm.type.list.List;

import org.junit.Before;
import org.junit.Test;

public class AddTest {
	
	private Context ctx = ContextLoader.createGlobal();
	
	@Before
	public void load() {
		ctx.set("+", new Closure(ctx, new Add()));
	}

	@Test
	public void testEval() {
		List list = new Cons(Symbol.createSymbol("+"),
				new Cons(new Num(10),
						new Cons(new Num(20))));
		
		assertEquals(list.eval(ctx).toString(), new Num(30).toString());
	}

}
