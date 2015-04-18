package TZ.forms.api.input;

import java.util.HashMap;
import java.util.Map;

import TZ.forms.api.Field;
import TZ.forms.api.var.Var;

/**
 * 
 * @author terrazero
 * @created Apr 15, 2015
 * 
 * @file FormInput.java
 * @project Forms
 * @identifier TZ.forms.input
 *
 */
public class FormInput {

	private Map<String, InputVar> inputs;
	
	public FormInput() {
		this.inputs = new HashMap<String, InputVar>();
	}
	
	public FormInput set(Field field, Var var) {
		this.inputs.put(field.id(), new InputVar(field, var));
		return this;
	}
	
	public FormInput printDebug() {
		this.inputs.forEach((id, var) -> {
			System.out.println(id + ": " + var.var().toString());
		});
		return this;
	}
	
}
