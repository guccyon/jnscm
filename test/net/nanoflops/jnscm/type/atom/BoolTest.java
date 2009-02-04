package net.nanoflops.jnscm.type.atom;

import static org.junit.Assert.assertEquals;
import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.ContextLoader;

import org.junit.Test;

public class BoolTest {
	private Context ctx = ContextLoader.createGlobal();

	@Test
	public void eval() {
		assertEquals(Bool.F, Bool.F);
		assertEquals(Bool.T, Bool.T);
		assertEquals(Bool.F.eval(ctx), Bool.F.eval(ctx));
		assertEquals(Bool.T.eval(ctx), Bool.T.eval(ctx));
	}

	@Test
	public void toS() {
		assertEquals(Bool.T, "#T");
		assertEquals(Bool.F, "#F");
	}

	@Test
	public void valueOf() {
		assertEquals(Bool.T.valueOf(), true);
		assertEquals(Bool.F.valueOf(), false);
	}

}
