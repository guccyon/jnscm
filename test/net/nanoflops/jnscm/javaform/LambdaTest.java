package net.nanoflops.jnscm.javaform;

import static org.junit.Assert.fail;
import net.nanoflops.jnscm.Context;

import org.junit.Test;

public class LambdaTest {

	@Test
	public void testInvoke() {
		
		new Lambda().invoke(new Context(), null);
		
		
		fail("�܂���������Ă��܂���B");
	}

}
