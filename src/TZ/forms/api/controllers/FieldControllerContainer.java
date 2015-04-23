package TZ.forms.api.controllers;

import TZ.forms.api.Field;
import TZ.forms.api.Form;
import TZ.forms.api.annotation.FormsFieldController;
import TZ.forms.api.input.FormInput;
import TZ.forms.api.types.FieldContainer;
import TZ.forms.api.var.Var;

/**
 * 
 * @author Terra
 * @created 18.04.2015
 * 
 * @file FieldControllerContainer.java
 * @project Formsapi
 * @identifier TZ.forms.api.types
 *
 */
@FormsFieldController(
	set = "Forms", 
	name = "Container", 
	type = "container", 
	component = FieldContainer.class, 
	settings = "settings", 
	input = "input",
	built = "built",
	extend = {
		"component"
	},
	settingsExtend = true
)
public class FieldControllerContainer {

	public static void create(Field field) {
		field.component(new FieldContainer());
	}

	public static void built(Field field) {
		field.option("label", new Var(false))
		.option("container", new Var(true))
		.option("margin", new Var(0))
		.option("notinput", new Var(true));
	}
	
	public static void settings(Field field, Form form) {
		FieldContainer container = field.getComponent();
		for (Field f : container.fields()) {
			Fields.settings(f, form);
		}
	}
	
	public static void input(Field field, FormInput input) {
		FieldContainer container = field.getComponent();
		for (Field f : container.fields()) {
			Fields.input(f, input);
		}
	}
	
	public static void get(Field field, Var var) {
		FieldContainer container = field.getComponent();
		var.set(container.get(var.string()));
	}
	
	public static void set(Field field, Var var) {
		FieldContainer container = field.getComponent();
		container.add(var.object());
	}
	
}
