package net.nanoflops.jnscm.parser;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.nanoflops.jnscm.exception.ParseException;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Bool;
import net.nanoflops.jnscm.type.atom.Num;
import net.nanoflops.jnscm.type.atom.Str;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.jnscm.type.list.Cons;
import net.nanoflops.jnscm.type.list.List;


public class Parser {

	public static List parse(String src) {
		return makeSyntaxTree(splitTokens(src));
	}
	
	private static LinkedList<String> splitTokens(String src) {
		Pattern pat = Pattern.compile(
				"\"(?:[^\"])*\"" + "|" + // 文字列
				"\\(" + "|" + // 前括弧リストの始まり
				"\\)" + "|" + // 後括弧リストの終わり
				"\\'" + "|" + // quoteの略記法
				";[^\r|\n]+" + "|" + // コメント行
				"[^()\\s\r\n]+"); // 括弧とスペース以外の文字の連続
				
		Matcher mat = pat.matcher(src);
		LinkedList<String> tokens = new LinkedList<String>();
		LinkedList<Integer> stack = new LinkedList<Integer>();
		while(mat.find()) {
			if (mat.group().startsWith(";")) continue;
			if (mat.group().equals("'")) {
				stack.add(0);
				tokens.add("(");
				tokens.add("quote");
			} else {
				tokens.add(mat.group());	
			}
			
			if (!stack.isEmpty()) {
				if (mat.group().equals("(")) {
					stack.add(stack.removeLast().intValue()+1);
				} else if (mat.group().equals(")")) {
					int num = stack.removeLast() - 1;
					if (num == 0) {
						tokens.add(")");
					} else {
						stack.addLast(num);
					}
				}
			}
		}
		return tokens;
	}
	
	private static List makeSyntaxTree(LinkedList<String> tokens) {
		String s = tokens.removeFirst();
		SymbolExp sexp = "(".equals(s) ? createList(tokens) : createAtom(s);
		if (!tokens.isEmpty()) {
			List tree = makeSyntaxTree(tokens);
			return new Cons(sexp, tree);
		}
		return new Cons(sexp);
	}
	
	private static SymbolExp createList(LinkedList<String> tokens) {
		if (tokens.isEmpty()) throw new ParseException("Unexpected tarmination");
		String s = tokens.removeFirst();
		if ("(".equals(s)) {
			SymbolExp list = createList(tokens);
			return new Cons(list, createList(tokens));
		} else if (")".equals(s)) {
			return Symbol.NIL;
		} else {
			SymbolExp car = createAtom(s);
			SymbolExp cdr = createList(tokens);
			return new Cons(car, cdr);
		}
	}
	
	private static SymbolExp createAtom(String token) {
		if (token.matches("[+-]?\\d+")) {
			return new Num(new Integer(token));
		} else if (Bool.T.toString().equals(token.toUpperCase())) {
			return Bool.T;
		} else if (Bool.F.toString().equals(token.toUpperCase())) {
			return Bool.F;
		} else if (token.matches("^\".*\"$")) {
			return new Str(token.replaceFirst("^\"", "").replaceFirst("\"$", ""));
		}
		return Symbol.createSymbol(token);
	}
}
