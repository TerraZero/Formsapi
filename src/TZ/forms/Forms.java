package TZ.forms;

import java.util.List;

import javax.swing.JPanel;

import TZ.forms.api.Field;
import TZ.forms.api.Form;
import TZ.forms.api.Step;
import TZ.sys.Sys;

/**
 * 
 * @author terrazero
 * @created Apr 13, 2015
 * 
 * @file Forms.java
 * @project Forms
 * @identifier TZ.forms
 *
 */
public class Forms {

	public static Form built(String id, String name) {
		Form form = new Form(id, name);
		Forms.invoke(id, "built", form);
		return form;
	}
	
	public static FormFrame frame(Form form, int width, int height) {
		FormFrame frame = new FormFrame(form);
		
		return frame;
	}
	
	public static void show(Form form, int width, int height) {
		
	}

	public static JPanel panel(Form form, int width, int margin) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		Step step = form.currentStep();
		
		for (Field field : step.fields()) {
			field.built();
			field.manageAdd(panel, form);
		}
		
		int height = step.arrange(width, margin, margin);
		panel.setSize(width + 2 * margin, height + margin);
		return panel;
	}
	
	public static String toID(String name) {
		return name.toLowerCase().replaceAll("[^a-z0-9]", "-").replaceAll("-+", "-").replaceAll("^-|-$", "");
	}
	
	public static<type extends FormsID> type fromID(List<type> elements, String id) {
		for (type element : elements) {
			if (element.id().equals(id)) {
				return element;
			}
		}
		return null;
	}
	
	public static<type extends FormsID> type fromName(List<type> elements, String name) {
		for (type element : elements) {
			if (element.name().equals(name)) {
				return element;
			}
		}
		return null;
	}
	
	public static void invoke(String id, String operation, Object... parameters) {
		Sys.cascade(new String[]{
			"forms:" + operation,
			id + ":" + operation,
		}, parameters);
	}
	 
}
