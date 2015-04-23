package TZ.forms.api.controllers;

import javax.swing.JComponent;
import javax.swing.JPanel;

import TZ.forms.api.Field;
import TZ.forms.api.Form;
import TZ.forms.api.annotation.FormsFieldController;
import TZ.forms.api.var.Var;

/**
 * 
 * @author terrazero
 * @created Apr 23, 2015
 * 
 * @file FieldControllerComponent.java
 * @project Formsapi
 * @identifier TZ.forms.api.controllers
 *
 */
@FormsFieldController(
	set = "Forms", 
	name = "Component", 
	type = "component", 
	component = JPanel.class,
	settings = "settings",
	built = "built"
)
public class FieldControllerComponent {

	public static void create(Field field) {
		field.component(new JPanel());
	}
	
	public static void built(Field field) {
		JComponent component = field.getComponent();
		component.setSize(0, 20);
	}
	
	public static void settings(Field field, Form form) {
		JComponent component = field.component();
		component.setBackground(field.option("background").object(component.getBackground()));
		component.setForeground(field.option("foreground").object(component.getForeground()));
		component.setSize(field.option("width").number(component.getWidth()), field.option("height").number(component.getHeight()));
	}
	
	public static void get(Field field, Var var) {
		JComponent container = field.getComponent();
		var.set(container.getComponent(var.number()));
	}
	
	public static void set(Field field, Var var) {
		JComponent container = field.getComponent();
		container.add(var.object());
	}
	
}
