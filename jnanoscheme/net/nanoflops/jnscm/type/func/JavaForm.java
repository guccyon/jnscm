package net.nanoflops.jnscm.type.func;

import net.nanoflops.jnscm.type.func.Procedure;

public abstract class JavaForm implements Procedure{

	public String toString() {
		return "#<JAVA-form> Class: " + this.getClass().getSimpleName();
	}
}
