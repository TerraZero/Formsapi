package TZ.forms.api.types;

import javax.swing.JCheckBox;

import TZ.forms.api.Field;
import TZ.forms.api.Var;
import TZ.forms.api.annotation.FormType;
import TZ.forms.input.FormInput;

/**
 * 
 * @author terrazero
 * @created Apr 15, 2015
 * 
 * @file FormTypeCheckbox.java
 * @project Forms
 * @identifier TZ.forms.api.types
 *
 */
@FormType(name = "Checkbox", type = "checkbox", component = JCheckBox.class)
public class FormTypeCheckbox {
	
	public static void create(Field field) {
		field.component(new JCheckBox());
	}

	public static void built(Field field) {
		JCheckBox box = field.getComponent();
		box.setSize(0, 20);
		box.setText(field.name());
		field.option("label", new Var(false));
	}
	
	public static void settings(Field field) {
		JCheckBox box = field.getComponent();
		box.setSelected(field.option("default").bool());
	}
	
	public static void input(Field field, FormInput input) {
		
	}
	
}
