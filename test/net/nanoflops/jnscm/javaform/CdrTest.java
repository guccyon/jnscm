package net.nanoflops.jnscm.javaform;

import static org.junit.Assert.assertEquals;
import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.ContextLoader;
import net.nanoflops.jnscm.type.atom.Num;
import net.nanoflops.jnscm.type.func.Procedure;
import net.nanoflops.jnscm.type.list.Cons;
import net.nanoflops.jnscm.type.list.List;

import org.junit.Before;
import org.junit.Test;

public class CdrTest {
	private Context ctx = ContextLoader.createGlobal();

	Procedure eval = new Cdr();
	Procedure eval3 = new Cdr(3);
	
	List list;
	@Before
	public void start() {
		list =  new Cons(new Num(1),
				new Cons(new Num(2),
				new Cons(new Num(3),
				new Cons(new Num(4),
				new Cons(new Num(5),
				new Cons(new Num(6),
				new Cons(new Num(7))))))));
	}

	@Test
	public void testEval() {
		List cd = (List)eval.invoke(ctx, list);
		assertEquals("2", cd.car().toString());
	}

	@Test
	public void testEval2() {
		List cd = (List)eval3.invoke(ctx, list);
		assertEquals("4", cd.car().toString());
	}

}
