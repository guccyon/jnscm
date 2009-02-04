package net.nanoflops.jnscm.type.atom;

import static org.junit.Assert.assertEquals;
import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.ContextLoader;

import org.junit.Test;

public class NumberTest {
	private Context ctx = ContextLoader.createGlobal();

	@Test
	public void testEval() {
		Num num = new Num(10);
		assertEquals(num.eval(ctx), num);
	}

	@Test
	public void testToString() {
		Num num = new Num(20);
		assertEquals(num.toString(), "20");
	}

	@Test
	public void testValueOf() {
		Num num = new Num(30);
		assertEquals(num.valueOf(), 30);
	}

}
