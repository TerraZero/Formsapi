package TZ.forms.api;

import java.util.ArrayList;
import java.util.List;

import TZ.forms.Forms;
import TZ.forms.FormsID;

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
	
	protected List<Step> steps;
	protected int current;
	
	public Form(String id, String name) {
		this.id = id;
		this.name = name;
		this.init();
		
		this.steps.add(new Step(this.name));
	}
	
	public void init() {
		this.steps = new ArrayList<Step>();
		this.current = 0;
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
	
	public Step currentStep() {
		return this.steps.get(this.current);
	}

	/* 
	 * @see TZ.forms.FormsID#name()
	 */
	@Override
	public String name() {
		return this.name;
	}
	
}
