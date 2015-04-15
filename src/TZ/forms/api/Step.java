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
 * @file Step.java
 * @project Forms
 * @identifier TZ.forms
 *
 */
public class Step implements FormsID {

	private String name;
	private String id;
	
	protected List<Field> fields;
	
	public Step(String name) {
		this.name = name;
		this.id = Forms.toID(this.name);
		this.init();
	}
	
	public Step(String name, String id) {
		this.name = name;
		this.id = id;
		this.init();
	}
	
	public void init() {
		this.fields = new ArrayList<Field>();
	}
	
	public List<Field> fields() {
		return this.fields;
	}
	
	public Step add(Field field) {
		this.fields.add(field);
		return this;
	}
	
	public Field field(String id) {
		return Forms.fromID(this.fields, id);
	}
	
	public Field field(int index) {
		return this.fields.get(index);
	}
	
	public int arrange(int width, int x, int y) {
		int top = y;
		int index = 0;
		
		for (Field field : this.fields) {
			top += field.arrange(this, index, top, width, x);
			index++;
		}
		return top;
	}
	
	public String id() {
		return this.id;
	}
	
	public Step id(String id) {
		this.id = id;
		return this;
	}
	
	public String name() {
		return this.name;
	}
	
	public Step name(String name) {
		this.name = name;
		return this;
	}
	
}
