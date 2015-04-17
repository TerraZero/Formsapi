package TZ.forms.api.validate;

import TZ.forms.api.Field;

/**
 * 
 * @author Terra
 * @created 17.04.2015
 * 
 * @file FormsError.java
 * @project Formsapi
 * @identifier TZ.forms.api.validate
 *
 */
public class FormsError {

	private String message;
	private Field field;
	
	public FormsError(String message) {
		this.message = message;
	}
	
	public FormsError(String message, Field field) {
		this.message = message;
		this.field = field;
	}
	
	public String message() {
		return this.message;
	}
	
	public Field field() {
		return this.field;
	}
	
	/* 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String string = "ERROR";
		if (this.field != null) {
			string += "[" + this.field.id() + ":" + this.field.type() + "]";
		}
		return string + " " + this.message;
	}
	
}
