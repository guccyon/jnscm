package net.nanoflops.json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class JsonMap implements JSON {
	
	private Map map;
	
	public JsonMap() {
		map = new HashMap();
	}
	public JsonMap(String key, JSON value) {
		this();
		this.put(key, value);
	}
	
	public void put(String key, String value) {
		map.put(key, new JsonString(value));
	}
	
	public void put(String key, JSON value) {
		map.put(key, value);
	}
	
	public boolean containsKey(String key) {
		return map.containsKey(key);
	}
	
	public JSON get(String key) {
		return (JSON) map.get(key);
	}

	public String toJSON() {
		Iterator iter = map.keySet().iterator();
		StringBuffer result = new StringBuffer();
		while(iter.hasNext()) {
			String key = iter.next().toString();
			JSON value = (JSON) map.get(key);
			result.append(new JsonString(key).toJSON()).append(":").append(value.toJSON());
			if (iter.hasNext()) result.append(",");
		}
		
		return "{" + result + "}";
	}
	
	public String toString() {
		return this.toJSON();
	}
}
