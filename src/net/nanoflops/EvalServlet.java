package net.nanoflops;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.nanoflops.jnscm.JNanoScheme;
import net.nanoflops.jnscm.Result;
import net.nanoflops.jnscm.type.atom.Symbol;
import net.nanoflops.json.JsonMap;
import net.nanoflops.json.JsonNumber;
import net.nanoflops.json.JsonString;

/**
 * Servlet implementation class for Servlet: EvalServlet
 *
 */
 public class EvalServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public EvalServlet() {
		super();
	}
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String src = request.getParameter("src");
		if (src != null && !src.equals("")) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				JNanoScheme scm = new JNanoScheme(baos);
				JNanoScheme.global().remove(Symbol.createSymbol("load"));
				scm.setLimit(1000000);
				Result result = scm.exe(src);
				JsonMap resp = new JsonMap();
				resp.put("callcnt", new JsonNumber(result.getCnt()));
				resp.put("exetime", new JsonNumber(result.getTime()));
				resp.put("result", new JsonString(htmlEncode(result.getResult())));
				resp.put("stdout", new JsonString(htmlEncode(baos.toString())));
				baos.close();
				response.getWriter().print(resp.toJSON());
			} catch (StackOverflowError e) {
				response.getWriter().print(
						new JsonString(e.getClass().getName()).toJSON());
			}
		} else {
			response.getWriter().print("src is empty!");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	private String htmlEncode(String value) {
		value = value.replaceAll( "<" , "&lt;" ).replaceAll(">" , "&gt;" );
		return value.replaceAll( "(\r\n|\n|\r)" , "<br>" );
	}
}