package net.nanoflops.jnscm;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.func.Closure;
import net.nanoflops.jnscm.type.func.SpecialForm;


public class Context {
	
	private Context parent;
	
	private Map<Symbol, SymbolExp> namespace;
	
	public Context() {
		this(null);
	};
	
	public Context(Context parent) {
		namespace = new HashMap<Symbol, SymbolExp>();
		this.parent = parent;
	};
	
	public SymbolExp set(String name, SymbolExp value) {
		return this.set(Symbol.createSymbol(name), value);
	}
	
	public SymbolExp set(Symbol sym, SymbolExp value) {
		namespace.put(sym, value);
		return sym;
	}
	
	public SymbolExp remove(Symbol sym) {
		return namespace.remove(sym);
	}

	public SymbolExp lookup(String name) {
		return this.lookup(Symbol.createSymbol(name));
	}

	public SymbolExp lookup(Symbol sym) {
		if (namespace.containsKey(sym)) {
			return namespace.get(sym);
		} else if (parent != null) {
			return parent.lookup(sym);
		}
		
		return Symbol.createSymbol("nil");
	}
	
	public boolean hasOwnKey(Symbol sym) {
		return namespace.containsKey(sym);
	}
	
	public Context getParent() {
		return parent;
	}
	
	public String toString() {
		return namespace + " < " + parent;
	}
	
	public LinkedList<String> listClosure() {
		LinkedList<String> list = new LinkedList<String>();
		for(Symbol sym : namespace.keySet())
			if (namespace.get(sym) instanceof Closure)
				list.add(sym.toString());
		Collections.sort(list);
		return list;
	}
	
	public LinkedList<String> listSPForm() {
		LinkedList<String> list = new LinkedList<String>();
		for(Symbol sym : namespace.keySet())
			if (namespace.get(sym) instanceof SpecialForm)
				list.add(sym.toString());
		Collections.sort(list);
		return list;
	}
	
}
