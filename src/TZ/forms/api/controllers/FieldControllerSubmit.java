package TZ.forms.api.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import TZ.forms.api.Field;
import TZ.forms.api.Form;
import TZ.forms.api.annotation.FormsFieldController;
import TZ.forms.api.var.Var;

/**
 * 
 * @author terrazero
 * @created Apr 22, 2015
 * 
 * @file FieldControllerSubmit.java
 * @project Formsapi
 * @identifier TZ.forms.api.controllers
 *
 */
@FormsFieldController(
	set = "Forms", 
	name = "Submit", 
	type = "submit", 
	component = JButton.class, 
	settings = "settings", 
	extend = {"button"}, 
	settingsExtend = true, 
	builtExtend = true
)
public class FieldControllerSubmit {

	public static void create(Field field) {
		field.component(new JButton());
	}
	
	public static void settings(Field field, Form form) {
		field.weight(1000);
		JButton button = field.getComponent();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				form.submit();
			}
			
		});
	}
	
	public static void get(Field field, Var var) {
		FieldControllerButton.get(field, var);
	}
	
	public static void set(Field field, Var var) {
		FieldControllerButton.set(field, var);
	}
	
}
