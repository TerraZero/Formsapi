package TZ.forms;

import javax.swing.JFrame;

import TZ.forms.api.Form;
import TZ.forms.api.Var;
import TZ.forms.api.types.FieldTypes;
import TZ.sys.invoker.annotations.Invoke;

/**
 * 
 * @author terrazero
 * @created Apr 14, 2015
 * 
 * @file Test.java
 * @project Forms
 * @identifier TZ.forms
 *
 */
@Invoke(invoke = "testform:built", function = "testformbuilt")
public class Test {

	public static void main(String[] args) {
		Form form = Forms.built("testform", "Testform");
		FormFrame frame = new FormFrame(form);
		frame.frame(600, 400).mode(JFrame.EXIT_ON_CLOSE).show();
	}
	
	public static void testformbuilt(Form form) {
		for (int i = 0; i < 5; i++) {
			form.step(0).add(FieldTypes.create("textfield", "Test Text - " + i).option("default", new Var("test test - " + (i * 2))));
		}
		
		for (int i = 0; i < 5; i++) {
			form.step(0).add(FieldTypes.create("checkbox", "Test - " + i).option("default", new Var(i % 2 == 0)));
		}
	}
	
}
