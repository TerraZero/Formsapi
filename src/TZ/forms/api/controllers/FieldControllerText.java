package TZ.forms.api.controllers;

import javax.swing.JLabel;

import TZ.forms.api.Field;
import TZ.forms.api.Form;
import TZ.forms.api.annotation.FormsFieldController;
import TZ.forms.api.var.Var;

/**
 * 
 * @author terrazero
 * @created Apr 23, 2015
 * 
 * @file FieldControllerLabel.java
 * @project Formsapi
 * @identifier TZ.forms.api.controllers
 *
 */
@FormsFieldController(
	set = "Forms", 
	name = "Text", 
	type = "text", 
	component = JLabel.class,
	settings = "settings",
	built = "built",
	extend = {
		"component",
	},
	settingsExtend = true
)
public class FieldControllerText {

	public static void create(Field field) {
		field.component(new JLabel());
	}
	
	public static void built(Field field) {
		JLabel label = field.getComponent();
		label.setSize(0, 30);
		label.setText(field.name());
		field.option("label", new Var(false));
	}
	
	public static void settings(Field field, Form form) {
		JLabel label = field.getComponent();
		label.setText(field.option("text").string(label.getText()));
		field.option("notinput", new Var(true));
	}
	
	public static void get(Field field, Var var) {
		JLabel label = field.getComponent();
		var.set(label.getText());
	}
	
	public static void set(Field field, Var var) {
		JLabel label = field.getComponent();
		label.setText(var.string(""));
	}
	
}
