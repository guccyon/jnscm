package net.nanoflops.jnscm;

public class Result {
	long callcnt;
	
	long calltime;
	
	String result;
	
	public long getCnt() {
		return callcnt;
	}
	
	public long getTime() {
		return calltime;
	}
	
	public String getResult() {
		return result;
	}
}
