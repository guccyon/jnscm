package net.nanoflops.jnscm.javaform;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Bool;
import net.nanoflops.jnscm.type.atom.Num;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.List;

public class CompareNumber extends JavaForm {
	
	private Compare comp;
	
	public CompareNumber(String type) {
		if ("=".equals(type)) {
			this.comp = new Compare() {
				public boolean compare(Num num1, Num num2) {
					return num1.valueOf() == num2.valueOf();
				}
			};
		} else if ("<".equals(type)) {
			this.comp = new Compare() {
				public boolean compare(Num num1, Num num2) {
					return num1.valueOf() < num2.valueOf();
				}
			};
			
		} else if (">".equals(type)) {
			this.comp = new Compare() {
				public boolean compare(Num num1, Num num2) {
					return num1.valueOf() > num2.valueOf();
				}
			};
			
		} else {
			throw new IllegalArgumentException("Illegal compare type -> " + type);
		}
	}

	public SymbolExp invoke(Context ctx, List args) {
		return toBool(this.comp.compare(
				(Num)args.car(), 
				(Num)new Car(1).car(args)));
	}
	
	private Bool toBool(boolean bol) {
		return bol ? Bool.T : Bool.F;
	}
	
	private interface Compare {
		boolean compare(Num num1, Num num2);
	}
}
