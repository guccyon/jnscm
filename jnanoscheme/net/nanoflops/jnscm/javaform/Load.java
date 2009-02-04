package net.nanoflops.jnscm.javaform;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import net.nanoflops.jnscm.Context;
import net.nanoflops.jnscm.parser.Parser;
import net.nanoflops.jnscm.type.SymbolExp;
import net.nanoflops.jnscm.type.atom.Str;
import net.nanoflops.jnscm.type.func.JavaForm;
import net.nanoflops.jnscm.type.list.Cons;
import net.nanoflops.jnscm.type.list.List;


public class Load extends JavaForm {

	public SymbolExp invoke(Context ctx, String path) {
		return this.invoke(ctx, new Cons(new Str(path)));
	}

	public SymbolExp invoke(Context ctx, List args) {
		Str path = (Str)args.car();
		String src = readFromScm(path.toString());
		List list = Parser.parse(src);
		new Begin().invoke(ctx, list);
		return path;
	}
	
	private String checkFile(String path) {
		if(path.matches("\\.scm$") || new File(path).exists()) {
			return path;
		} else if (new File(path + ".scm").exists()) {
			return path + ".scm";
		}
		return null;
	}
	
	private String readFromScm(String path) {
		BufferedReader br = null;
		try {
			String tmp;
			if ((tmp = checkFile(path)) != null) {
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(tmp)));
			} else {
				br = new BufferedReader(new InputStreamReader(
						this.getClass().getClassLoader().getResourceAsStream(path)));
			}
			
			StringBuilder sb = new StringBuilder();
			char[] buff = new char[256];
			int length;
			while((length = br.read(buff)) != -1) {
				sb.append(buff, 0, length);
			}
			
			return sb.toString();
			
		} catch (IOException ioe) {
			throw new net.nanoflops.jnscm.exception.IOException(
					"File not found -> " + path);
		}
	}
}
