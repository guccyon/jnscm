package net.nanoflops.json;

import java.util.LinkedList;
import java.util.List;

public class JsonArray implements JSON {
	private List<JSON> list;
	
	public JsonArray() {
		list = new LinkedList<JSON>();
	}
	
	public void push(JSON object) {
		list.add(object);
	}
	
	public void add(JSON object) {
		list.add(object);
	}
	
	public int length() {
		return list.size();
	}

	public String toJSON() {
		StringBuffer result = new StringBuffer();
		for(JSON obj: list) {
			result.append(obj.toJSON()).append(",");
		}
		if (result.length() > 0) {
			return "[" + result.substring(0, result.length()-1) + "]";
		} else {
			return "[]";
		}
	}
	
	public String toString() {
		return this.toJSON();
	}
}
