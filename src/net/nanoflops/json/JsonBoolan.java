package net.nanoflops.json;

public class JsonBoolan implements JSON {
	
	private boolean value;
	
	public JsonBoolan(boolean value) {
		this.value = value;
	}
	
	public boolean getValue(){
		return value;
	}
	
	public void setValue(boolean value) {
		this.value = value;
	}

	public String toJSON() {
		return String.valueOf(value);
	}
}
