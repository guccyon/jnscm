package net.nanoflops.json;

import java.math.BigDecimal;

public class JsonNumber implements JSON {
	
	private BigDecimal value;
	
	public JsonNumber(int value) {
		this.value = new BigDecimal(value);
	}
	
	public JsonNumber(long value) {
		this.value = new BigDecimal(value);
	}
	
	public JsonNumber(float value) {
		this.value = new BigDecimal(value);
	}
	
	public int getInt() {
		return value.intValue();
	}
	
	public double getDouble() {
		return value.doubleValue();
	}

	public String toJSON() {
		return value.toString();
	}
}
