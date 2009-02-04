package net.nanoflops.jnscm.type.list;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.JNanoScheme;
import net.nanoflops.jnscm.exception.EvalException;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Str;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.func.Function;
import net.nanoflops.jnscm.type.func.SpecialForm;

public class Cons implements List {
	private SymbolExp car = Symbol.NIL;
	private SymbolExp cdr = Symbol.NIL;
	
	public Cons(SymbolExp car) {
		this.car = car;
	}
	
	public Cons(SymbolExp car, SymbolExp cdr) {
		this.car = car;
		this.cdr = cdr;
	}
	
	public SymbolExp car() {
		return car;
	}
	
	public SymbolExp cdr() {
		return cdr;
	}

	public SymbolExp eval(Context ctx) {
		try {
			Function func = (Function)this.car().eval(ctx);
			List args = (List)this.cdr();
			if (!(func instanceof SpecialForm)) {
				args = evalArgs(ctx, args);
			}
			
			JNanoScheme.checkCount();
			return func.apply(ctx, (List) args);
		} catch (ClassCastException cce) {
			throw new EvalException("not defined Function: " + this.car());
		}
	}
	
	private List evalArgs(Context ctx, List args) {
		if (args == Symbol.NIL) return Symbol.NIL;
		return new Cons(args.car().eval(ctx), evalArgs(ctx, (List)args.cdr()));
	}

	public String toString() {
		return "(" + toString(this) + ")";
	}
	
	private String toString(Cons cons) {
		
		String car = toString(cons.car);
		String cdr = (this.cdr == Symbol.NIL) ? "" : toString(cons.cdr);
		return car + spliter(cons) + cdr;
	}
	
	
	private String toString(SymbolExp sexp) {
		if (sexp instanceof Str) {
			return '"' + sexp.toString() + '"';
		} else if (sexp instanceof Cons) {
			Cons cons = (Cons) sexp;
			String car = (cons.car instanceof Cons) ? cons.car.toString() : toString(cons.car);
			String cdr = (cons.cdr == Symbol.NIL) ? "" : toString(cons.cdr);
			return car + spliter(cons) + cdr;
		} else {
			return sexp.toString();
		}
	}
	
	private String spliter(Cons cons) {
		if (cons.cdr == Symbol.NIL) return "";
		else if (cons.cdr instanceof Cons) return " ";
		else return " . ";
	}
	
	public int length() {
		return car == Symbol.NIL ? 0 : count(this, 1);
	}
	
	private int count(Cons cons, int accum) {
		if (!(cons.cdr() instanceof Cons)) return accum;
		return count((Cons)cons.cdr(), ++accum);
	}

	public Cons append(SymbolExp sexp) {
		Cons cons = this;
		while(cons.cdr instanceof Cons) {
			cons = (Cons)cons.cdr;
		}
		if (cons.cdr == Symbol.NIL) cons.cdr = sexp;
		return this;
	}
	
	public SymbolExp duplicate() {
		return new Cons(this.car.duplicate(), this.cdr.duplicate());
	}
}
