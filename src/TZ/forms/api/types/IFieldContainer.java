package TZ.forms.api.types;

import java.util.List;

import TZ.forms.Forms;
import TZ.forms.api.Field;

/**
 * 
 * @author Terra
 * @created 18.04.2015
 * 
 * @file FieldContainer.java
 * @project Formsapi
 * @identifier TZ.forms.api.types
 *
 */
public interface IFieldContainer {

	public default void add(Field field) {
		this.fields().add(field);
	}
	
	public default Field get(String id) {
		return Forms.fromID(this.fields(), id);
	}
	
	public List<Field> fields();
	
}
