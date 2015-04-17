package TZ.forms.api.var;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author terrazero
 * @created Apr 16, 2015
 * 
 * @file FieldOption.java
 * @project Formsapi
 * @identifier TZ.forms.api
 *
 */
public class Options {

	private Map<String, Var> options;
	
	public Options() {
		this.options = new HashMap<String, Var>();
	}
	
	public Options set(String name, Var option) {
		this.options.put(name, option);
		return this;
	}
	
	public Var get(String name) {
		return this.options.get(name);
	}
	
	public boolean is(String name) {
		return this.options.containsKey(name);
	}
	
	public Options unset(String... names) {
		for (String name : names) {
			this.options.remove(name);
		}
		return this;
	}
	
}
