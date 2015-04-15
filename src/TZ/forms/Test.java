package TZ.forms;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import TZ.forms.api.Field;
import TZ.forms.api.Form;
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
			JTextField field = new JTextField();
			field.setSize(0, 30);
			form.step(0).add(new Field("Test Field " + i, field, "textfield"));
		}
		
		for (int i = 0; i < 5; i++) {
			JCheckBox box = new JCheckBox();
			box.setSize(0, 20);
			form.step(0).add(new Field("Test box " + i, box, "checkbox"));
		}
	}
	
}
