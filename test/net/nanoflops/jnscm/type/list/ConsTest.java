package net.nanoflops.jnscm.type.list;

import static org.junit.Assert.assertEquals;
import net.nanoflops.jnscm.JNanoScheme;
import net.nanoflops.jnscm.exception.EvalException;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Num;
import net.nanoflops.jnscm.type.atom.Symbol;

import org.junit.Test;

public class ConsTest {
	private JNanoScheme scm = new JNanoScheme(System.out);

	@Test
	public void testCar() {
		SymbolExp sym = Symbol.createSymbol("test");
		SymbolExp val = new Num(10);
		
		assertEquals(new Cons(sym, val).car(), sym);
	}

	@Test
	public void testCdr() {
		SymbolExp sym = Symbol.createSymbol("test2");
		SymbolExp val = new Num(20);
		
		assertEquals(new Cons(sym, val).cdr(), val);
	}

	@Test
	public void testCdr2() {
		SymbolExp sym = Symbol.createSymbol("test2");
		Cons cons = new Cons(new Num(20));
		assertEquals(new Cons(sym, cons).cdr(), cons);
	}

	@Test(expected=EvalException.class)
	public void testEval() {
		Cons cons = new Cons(new Num(30));
		assertEquals(cons.eval(scm.global()).toString(), "30");
	}

	@Test
	public void testLength() {
		Cons cons = new Cons(new Num(10),
				new Cons(new Num(20)));
		assertEquals(cons.length(), 2);
		
	}

	@Test
	public void testLength2() {
		Cons cons = new Cons(new Num(10),new Num(20));
		assertEquals(cons.length(), 1);
		
	}

	@Test
	public void testLength3() {
		Cons cons = new Cons(new Num(10));
		assertEquals(cons.length(), 1);
		
	}
	
	@Test
	public void testToString() {
		Cons cons = new Cons(new Num(5),
				new Cons(new Cons(
						new Num(1), new Num(2))));
		System.out.println(cons);
	}
}
