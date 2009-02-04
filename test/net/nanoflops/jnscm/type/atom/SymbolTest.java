package net.nanoflops.jnscm.type.atom;

import static org.junit.Assert.assertEquals;
import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.ContextLoader;

import org.junit.Test;

public class SymbolTest {
	private Context ctx = ContextLoader.createGlobal();

	@Test
	public void createSymbol() {
		assertEquals(Symbol.createSymbol("test"),
				Symbol.createSymbol("test"));
	}

	@Test
	public void eval() {
		Symbol sym = Symbol.createSymbol("test2");
		ctx.set(sym, Symbol.createSymbol("value2"));
		assertEquals(sym.eval(ctx), Symbol.createSymbol("value2"));
	}

	@Test
	public void testToString() {
		Symbol sym = Symbol.createSymbol("test3");
		assertEquals(sym.toString(), "test3");
	}

}
