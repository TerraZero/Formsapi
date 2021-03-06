package TZ.forms;

import java.awt.Color;

import javax.swing.JFrame;

import TZ.forms.api.Field;
import TZ.forms.api.Form;
import TZ.forms.api.controllers.Fields;
import TZ.forms.api.var.Var;
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
		frame.frame(800, 400).mode(JFrame.EXIT_ON_CLOSE).show();
	}
	
	public static void testformbuilt(Form form) {
		form.step(0).add(Fields.create("submit", "Submit"));
		for (int i = 0; i < 5; i++) {
			form.step(0).add(Fields.create("textfield", "Test Text - " + i).option("strict", new Var(true)).option("default", new Var("test test - " + (i * 2))));
		}
		
		for (int i = 0; i < 5; i++) {
			form.step(0).add(Fields.create("checkbox", "Test - " + i).option("default", new Var(i % 2 == 0)));
		}
		Field container = new Field("container", "Test Container").option("background", new Var(Color.RED));
		container.set(new Var(new Field("text", "test label").option("foreground", new Var(Color.white))));
		form.step(0).add(container);
		form.step(0).validate((vform, field) -> {
			vform.error("test");
		});
	}
	
}
