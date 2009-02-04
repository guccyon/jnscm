package net.nanoflops.json;

public class JsonString implements JSON {
	
	private String value;
	
	public JsonString(String value) {
		this.value = value;
	}

	public String toJSON() {
		return "\"" + value + "\"";
	}
}
