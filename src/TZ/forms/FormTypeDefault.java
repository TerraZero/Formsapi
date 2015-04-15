package TZ.forms;

import javax.swing.JCheckBox;

import TZ.forms.api.Field;
import TZ.sys.invoker.annotations.Invoke;
import TZ.sys.invoker.annotations.Invokes;

/**
 * 
 * @author terrazero
 * @created Apr 15, 2015
 * 
 * @file FormTypeDefault.java
 * @project Forms
 * @identifier TZ.forms
 *
 */
@Invokes({
	@Invoke(invoke = "field:checkbox", function = "typeCheckbox")
})
public class FormTypeDefault {

	public static void typeCheckbox(Field field) {
		field.optionLabel(false);
		JCheckBox box = field.getComponent();
		box.setText(field.name());
	}
	
}
