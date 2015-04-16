package TZ.forms.input;

import TZ.forms.api.Field;

/**
 * 
 * @author terrazero
 * @created Apr 15, 2015
 * 
 * @file FormVar.java
 * @project Forms
 * @identifier TZ.forms.input
 *
 */
public class FormVar {

	private String value;
	private Field field;
	
	public FormVar(String value) {
		this.value = value;
	}
	
	public FormVar(String value, Field field) {
		this.value = value;
		this.field = field;
	}
	
	public String value() {
		return this.value;
	}
	
	public boolean bool() {
		return Boolean.parseBoolean(this.value);
	}
	
	public int number() {
		return Integer.parseInt(this.value);
	}
	
	public float floatNumber() {
		return Float.parseFloat(this.value);
	}
	
	public double decimal() {
		return Double.parseDouble(this.value);
	}
	
	public Field field() {
		return this.field;
	}
	
}
