package net.nanoflops.jnscm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import net.nanoflops.jnscm.exception.EvalException;
import net.nanoflops.jnscm.exception.ParseException;
import net.nanoflops.jnscm.javaform.Begin;
import net.nanoflops.jnscm.parser.Parser;
import net.nanoflops.jnscm.type.list.List;


public class JNanoScheme {
	
	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			
			JNanoScheme scm = new JNanoScheme(System.out);

			while(true) {
				writer().print("jnscm>");
				writer().flush();
				String src = trim(br.readLine());
				if ("".equals(src) || "\\q".equals(src)) {
					break;
				}
				Result result = scm.exe(src);
				writer().println(result.result);
				writer().print("--> ");
				writer().printf("%d msec.", result.calltime);
				writer().printf(" %d times", result.callcnt);
				writer().println();
				sand.get().reset();
			}
		} catch (Exception e) {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
	}
	
	static ThreadLocal<SandBox> sand = new ThreadLocal<SandBox>();
	
	public static void checkCount() {
		if(sand.get() != null) sand.get().checkLimitCount();
	}
	
	public JNanoScheme(OutputStream os) {
		this(new PrintWriter(os));
	}
	
	public JNanoScheme(final PrintWriter pw) {
		sand.set(new SandBox(pw));
		sand.get().setContext(ContextLoader.createGlobal());
	}
	
	public static PrintWriter writer() {
		return sand.get().getWriter();
	}
	
	public static Context global() {
		return sand.get().getGlobalContext();
	}
	
	public Result exe(String src) {
		Result res = new Result();
		long start = System.currentTimeMillis();
		try {
			List lists = Parser.parse(src);
			res.result = new Begin().invoke(global(), lists).toString();
		} catch (ParseException pe) {
			res.result = "Syntax Error -> " + pe.getMessage();
		} catch (EvalException ee) {
			res.result = "Runtime Error -> " + ee.getMessage();
		} catch (RuntimeException re) {
			res.result = "Fatal Error -> " + re.getMessage();
			re.printStackTrace();
		}

		res.callcnt = sand.get().getCallNum();
		res.calltime = System.currentTimeMillis() - start;
		writer().flush();
		return res;
	}
	
	public void setLimit(long limit) {
		sand.get().setLimit(limit);
	}
	
	private static String trim(String str) {
		return str.replaceFirst("^\\s*", "").replaceFirst("\\s*$", "");
	}
}
