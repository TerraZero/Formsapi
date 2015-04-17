package TZ.forms.api.types;

import javax.swing.JTextField;

import TZ.forms.api.Field;
import TZ.forms.api.annotation.FormsFieldController;
import TZ.forms.input.FormInput;

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
	
	public static void settings(Field field) {
		JTextField text = field.getComponent();
		text.setText(field.option("default").string(""));
	}
	
	public static void input(Field field, FormInput input) {
		
	}
	
}
