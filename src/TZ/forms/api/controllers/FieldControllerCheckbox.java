package TZ.forms.api.controllers;

import javax.swing.JCheckBox;

import TZ.forms.api.Field;
import TZ.forms.api.annotation.FormsFieldController;
import TZ.forms.api.var.Var;

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
@FormsFieldController(name = "Checkbox", type = "checkbox", component = JCheckBox.class)
public class FieldControllerCheckbox {
	
	public static void create(Field field) {
		field.component(new JCheckBox());
	}

	public static void built(Field field) {
		JCheckBox box = field.getComponent();
		box.setSize(0, 20);
		box.setText(field.name());
		field.option("label", new Var(false));
	}
	
	public static void get(Field field, Var var) {
		JCheckBox box = field.getComponent();
		var.set(box.isSelected());
	}
	
	public static void set(Field field, Var var) {
		JCheckBox box = field.getComponent();
		box.setSelected(var.bool());
	}
	
}
