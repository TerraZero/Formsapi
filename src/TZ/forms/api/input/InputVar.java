package TZ.forms.api.input;

import TZ.forms.api.Field;
import TZ.forms.api.var.Var;

/**
 * 
 * @author Terra
 * @created 18.04.2015
 * 
 * @file InputVar.java
 * @project Formsapi
 * @identifier TZ.forms.api.input
 *
 */
public class InputVar {

	private Field field;
	private Var var;
	
	public InputVar(Field field, Var var) {
		this.field = field;
		this.var = var;
	}
	
	public Field field() {
		return this.field;
	}
	
	public Var var() {
		return this.var;
	}
	
}
