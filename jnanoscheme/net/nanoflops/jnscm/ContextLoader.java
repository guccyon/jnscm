package net.nanoflops.jnscm;

import net.nanoflops.jnscm.exception.IOException;
import net.nanoflops.jnscm.javaform.And;
import net.nanoflops.jnscm.javaform.Append;
import net.nanoflops.jnscm.javaform.Begin;
import net.nanoflops.jnscm.javaform.Car;
import net.nanoflops.jnscm.javaform.Cdr;
import net.nanoflops.jnscm.javaform.CompareNumber;
import net.nanoflops.jnscm.javaform.Cond;
import net.nanoflops.jnscm.javaform.Construct;
import net.nanoflops.jnscm.javaform.Define;
import net.nanoflops.jnscm.javaform.Eq;
import net.nanoflops.jnscm.javaform.Equals;
import net.nanoflops.jnscm.javaform.If;
import net.nanoflops.jnscm.javaform.Lambda;
import net.nanoflops.jnscm.javaform.Load;
import net.nanoflops.jnscm.javaform.Map;
import net.nanoflops.jnscm.javaform.Not;
import net.nanoflops.jnscm.javaform.Or;
import net.nanoflops.jnscm.javaform.Set;
import net.nanoflops.jnscm.javaform.num.Add;
import net.nanoflops.jnscm.javaform.num.Divide;
import net.nanoflops.jnscm.javaform.num.Modulo;
import net.nanoflops.jnscm.javaform.num.Multi;
import net.nanoflops.jnscm.javaform.num.Sub;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Undefined;
import net.nanoflops.jnscm.type.func.Closure;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.func.Predicate;
import net.nanoflops.jnscm.type.func.Procedure;
import net.nanoflops.jnscm.type.func.SpecialForm;
import net.nanoflops.jnscm.type.list.List;

public class ContextLoader {
	
	public static Context createGlobal() {
		Context ctx = new Context();
		loadSPForm(ctx);
		load(ctx);
		
		try {
			new Load().invoke(ctx, "scm/__standard__.scm");
		} catch (RuntimeException re) {
			throw new IOException("Initialized Error", re);
		}
		return ctx;
	}
	
	private static void loadSPForm(Context ctx) {
		ctx.set("define", new SpecialForm(ctx, new Define()));
		ctx.set("if", new SpecialForm(ctx, new If()));
		ctx.set("lambda", new SpecialForm(ctx, new Lambda()));
		ctx.set("set!", new SpecialForm(ctx, new Set()));
		ctx.set("quote", new SpecialForm(ctx, new JavaForm(){
			public SymbolExp invoke(Context ctx, List args) { return args.car(); }
		}));
		ctx.set("cond", new SpecialForm(ctx, new Cond()));
	}
	
	static void load(Context ctx) {
		
		ctx.set("car", new Closure(ctx, new Car()));
		ctx.set("cadr", new Closure(ctx, new Car(1)));
		ctx.set("caddr", new Closure(ctx, new Car(2)));
		ctx.set("cadddr", new Closure(ctx, new Car(3)));
		
		ctx.set("cdr", new Closure(ctx, new Cdr()));
		ctx.set("cddr", new Closure(ctx, new Cdr(2)));
		ctx.set("cdddr", new Closure(ctx, new Cdr(3)));
		ctx.set("cddddr", new Closure(ctx, new Cdr(4)));
		
		ctx.set("cons", new Closure(ctx, new Construct()));
		ctx.set("append", new Closure(ctx, new Append(true)));
		ctx.set("append!", new Closure(ctx, new Append(false)));
		
		ctx.set("+", new Closure(ctx, new Add()));
		ctx.set("-", new Closure(ctx, new Sub()));
		ctx.set("*", new Closure(ctx, new Multi()));
		ctx.set("/", new Closure(ctx, new Divide()));
		ctx.set("=", new Closure(ctx, new CompareNumber("=")));
		ctx.set("modulo", new Closure(ctx, new Modulo()));
		ctx.set("remainder", new Closure(ctx, new Modulo()));
		ctx.set("<", new Closure(ctx, new CompareNumber("<")));
		ctx.set(">", new Closure(ctx, new CompareNumber(">")));
		ctx.set("expt", new Closure(ctx, new CompareNumber(">")));
		ctx.set("number?", new Closure(ctx, new Predicate.NumberP()));
		ctx.set("string?", new Closure(ctx, new Predicate.StringP()));
		ctx.set("null?", new Closure(ctx, new Predicate.NullP()));
		ctx.set("symbol?", new Closure(ctx, new Predicate.SymbolP()));
		ctx.set("and", new Closure(ctx, new And()));
		ctx.set("or", new Closure(ctx, new Or()));
		ctx.set("not", new Closure(ctx, new Not()));
		ctx.set("begin", new Closure(ctx, new Begin()));
		ctx.set("load", new Closure(ctx, new Load()));
		ctx.set("map", new Closure(ctx, new Map()));
		ctx.set("eq?", new Closure(ctx, new Eq()));
		ctx.set("eqv?", new Closure(ctx, new Equals()));
		
		ctx.set("list", new Closure(ctx, new Procedure(){
			public SymbolExp invoke(Context ctx, List args) { return args; }
		}));

		ctx.set("newline", new Closure(null, new JavaForm(){
			public SymbolExp invoke(Context ctx, List args) { JNanoScheme.writer().println(); return new Undefined(); }
		}));

		ctx.set("display", new Closure(null, new Procedure(){
			public SymbolExp invoke(Context ctx, List args) { JNanoScheme.writer().print(args.car()); return new Undefined();}
		}));
		
		ctx.set("dup", new Closure(null, new Procedure(){
			public SymbolExp invoke(Context ctx, List args) { return args.duplicate(); }
		}));
	}
}
