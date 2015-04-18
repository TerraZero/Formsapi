package TZ.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import TZ.forms.api.Field;
import TZ.forms.api.Form;
import TZ.forms.api.controllers.FieldControllers;
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
		for (int i = 0; i < 5; i++) {
			form.step(0).add(FieldControllers.create("textfield", "Test Text - " + i).option("strict", new Var(true)).option("default", new Var("test test - " + (i * 2))));
		}
		
		for (int i = 0; i < 5; i++) {
			form.step(0).add(FieldControllers.create("checkbox", "Test - " + i).option("default", new Var(i % 2 == 0)));
		}
		Field field = FieldControllers.create("button", "Test Button").option("width", new Var(500));
		form.step(0).add(field);
		JButton button = field.getComponent();
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				form.submit();
				form.getInputs().printDebug();
				form.step(0).field("test_text_0").set(new Var("test"));
			}
			
		});
		
		Field container = FieldControllers.create("container", "Test Container");
		for (int i = 0; i < 4; i++) {
			container.set(new Var(FieldControllers.create("button", "test con" + i).option("width", new Var(150 + i * 100))));
		}
		form.step(0).add(container);
	}
	
}
