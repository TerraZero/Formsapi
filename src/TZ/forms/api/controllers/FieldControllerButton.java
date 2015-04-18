package TZ.forms.api.controllers;

import javax.swing.JButton;

import TZ.forms.api.Field;
import TZ.forms.api.annotation.FormsFieldController;
import TZ.forms.api.var.Var;

/**
 * 
 * @author Terra
 * @created 18.04.2015
 * 
 * @file FieldControllerButton.java
 * @project Formsapi
 * @identifier TZ.forms.api.types
 *
 */
@FormsFieldController(name = "Button", type = "button", component = JButton.class, settings = "settings")
public class FieldControllerButton {

	public static void create(Field field) {
		field.component(new JButton());
	}

	public static void built(Field field) {
		JButton button = field.getComponent();
		button.setSize(300, 30);
		button.setText(field.name());
		field.option("label", new Var(false)).option("fullwidth", new Var(false));
	}
	
	public static void settings(Field field) {
		field.component().setSize(field.option("width").number(field.component().getWidth()), field.component().getHeight());
	}
	
	public static void get(Field field, Var var) {
		JButton button = field.getComponent();
		var.set(button.getText());
	}
	
	public static void set(Field field, Var var) {
		JButton button = field.getComponent();
		button.setText(var.string(""));
	}
	
}
