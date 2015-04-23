package TZ.forms.api;

import java.util.ArrayList;
import java.util.List;

import TZ.forms.FormFrame;
import TZ.forms.Forms;
import TZ.forms.FormsID;
import TZ.forms.api.input.FormInput;
import TZ.forms.api.validate.FormsError;

/**
 * 
 * @author terrazero
 * @created Apr 13, 2015
 * 
 * @file Form.java
 * @project Forms
 * @identifier TZ.forms
 *
 */
public class Form implements FormsID {
	
	private String id;
	private String name;
	private FormInput input;
	
	private List<Step> steps;
	private int current;
	
	private List<FormsError> errors;
	private FormFrame frame;
	
	public Form(String id, String name) {
		this.id = id;
		this.name = name;
		this.init();
		
		this.steps.add(new Step(this.name));
	}
	
	public void init() {
		this.steps = new ArrayList<Step>();
		this.errors = new ArrayList<FormsError>();

		this.current = 0;
		this.input = new FormInput();
	}
	
	/* 
	 * @see TZ.forms.FormsID#id()
	 */
	@Override
	public String id() {
		return this.id;
	}
	
	public List<Step> steps() {
		return this.steps;
	}
	
	public Step step(int index) {
		return this.steps.get(index);
	}
	
	public Step step(String id) {
		return Forms.fromID(this.steps, id);
	}
	
	public Form frame(FormFrame frame) {
		this.frame = frame;
		return this;
	}
	
	public Step currentStep() {
		return this.steps.get(this.current);
	}
	
	public Form input() {
		System.out.println("dev: input");
		this.currentStep().input(this.input);
		return this;
	}
	
	public Form validate() {
		System.out.println("dev: validate");
		this.currentStep().validate(this, this.input);
		return this;
	}
	
	public Form submit() {
		System.out.println("dev: submit");
		this.input().validate();
		if (this.errors.size() != 0) {
			this.refresh();
		}
		return this;
	}
	
	public Form refresh() {
		System.out.println("dev: refresh");
		Step step = this.currentStep();
		
		for (Field field : step.settings(this).fields()) {
			field.show();
		}
		
		step.arrange(this.frame.frame().getWidth(), 0, 0);
		return this;
	}
	
	public Form error(String message) {
		return this.error(new FormsError(message));
	}
	
	public Form error(String message, Field field) {
		return this.error(new FormsError(message, field));
	}
	
	public Form error(FormsError error) {
		this.errors.add(error);
		return this;
	}
	
	public FormInput getInputs() {
		return this.input;
	}

	/* 
	 * @see TZ.forms.FormsID#name()
	 */
	@Override
	public String name() {
		return this.name;
	}
	
}
