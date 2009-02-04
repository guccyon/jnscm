package net.nanoflops.jnscm;

import java.io.PrintWriter;

import net.nanoflops.jnscm.exception.EvalException;


public class SandBox {
	
	private long callNum;

	private PrintWriter pw;
	private Context global;
	private long limit = -1;
	
	SandBox(PrintWriter pw) {
		this.pw = pw;
		reset();
	}
	
	void setContext(Context global) {
		this.global = global;
	}
	
	long inc() {
		return ++callNum;
	}
	
	void reset() {
		callNum = 0;
	}
	
	void checkLimitCount() {
		callNum++;
		if( limit != -1 && callNum > limit) {
			throw new EvalException("too deep stack");
		}
	}
	void setLimit(long limit) {
		this.limit = limit;
	}
	
	public long getCallNum() {
		return callNum;
	}
	
	public PrintWriter getWriter() {
		return pw;
	}
	public Context getGlobalContext() {
		return global;
	}
}
