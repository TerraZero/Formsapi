package TZ.forms.api;

import java.util.ArrayList;
import java.util.List;

import TZ.forms.Forms;
import TZ.forms.FormsID;
import TZ.forms.api.arranger.Arrange;
import TZ.forms.api.controllers.FieldControllers;
import TZ.forms.api.input.FormInput;
import TZ.forms.api.types.FieldContainer;

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
	private List<Field> fields;
	private List<Operation> operations;
	
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
		this.operations = new ArrayList<Operation>();
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
		Arrange arrange = new Arrange();
		arrange.y = y;
		arrange.index = 0;
		arrange.w = width;
		arrange.x = x;
		
		for (Field field : this.fields) {
			if (field.option("container").bool()) {
				FieldContainer container = field.getComponent();
				container.arrange(field, width, x, y);
			}
			field.arrange(arrange);
			arrange.y += arrange.height;
			arrange.index++;
		}
		return arrange.y;
	}
	
	public Step input(FormInput input) {
		for (Field field : this.fields) {
			FieldControllers.input(field, input);
		}
		return this;
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
