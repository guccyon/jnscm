package net.nanoflops.jnscm.type.atom;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;

public class Bool extends Atom {
	
	public static final Bool T = new Bool(true, "#T");
	public static final Bool F = new Bool(false, "#F");
	
	private boolean value;
	
	private String disp;

	private Bool(boolean value, String disp) {
		this.value = value;
		this.disp = disp;
	}
	
	public SymbolExp eval(Context ctx) {
		return this;
	}
	
	public String toString() {
		return disp;
	}
	
	public boolean valueOf() {
		return value;
	}
}
