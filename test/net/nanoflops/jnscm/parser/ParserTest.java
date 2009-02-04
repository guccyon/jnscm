package net.nanoflops.jnscm.parser;

import net.nanoflops.jnscm.type.SymbolExp;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testParse() {
		SymbolExp sexp = Parser.parse("(quote (1 2 3))" +
				"(define x 100)");
		System.out.println(sexp);
		//fail("‚Ü‚¾À‘•‚³‚ê‚Ä‚¢‚Ü‚¹‚ñB");
	}

}
