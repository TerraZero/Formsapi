package TZ.forms.api.controllers;

import javax.swing.JTextField;

import TZ.forms.api.Field;
import TZ.forms.api.annotation.FormsFieldController;
import TZ.forms.api.var.Var;

/**
 * 
 * @author terrazero
 * @created Apr 16, 2015
 * 
 * @file FormTypeTextfield.java
 * @project Formsapi
 * @identifier TZ.forms.api.types
 *
 */
@FormsFieldController(name = "Textfield", type = "textfield", component = JTextField.class)
public class FieldControllerTextfield {

	public static void create(Field field) {
		field.component(new JTextField());
	}

	public static void built(Field field) {
		JTextField text = field.getComponent();
		text.setSize(0, 30);
	}
	
	public static void get(Field field, Var var) {
		JTextField text = field.getComponent();
		var.set(text.getText());
	}
	
	public static void set(Field field, Var var) {
		JTextField text = field.getComponent();
		text.setText(var.string(""));
	}
	
}
