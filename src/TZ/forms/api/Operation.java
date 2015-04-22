package TZ.forms.api;

import TZ.forms.Forms;
import TZ.forms.FormsID;
import TZ.forms.api.controllers.Fields;

/**
 * 
 * @author Terra
 * @created 18.04.2015
 * 
 * @file Operations.java
 * @project Formsapi
 * @identifier TZ.forms.api
 *
 */
public class Operation implements FormsID {

	private String name;
	private String id;
	
	private String type;
	private Field button;
	
	public Operation(String name) {
		this.name = name;
		this.id = Forms.toID(this.name);
		this.type = "submit";
	}

	public Operation(String name, String id) {
		this.name = name;
		this.id = id;
		this.type = "submit";
	}
	
	public Operation type(String type) {
		this.type = type;
		return this;
	}
	
	public String type() {
		return this.type;
	}
	
	public Operation create() {
		if (this.button == null) {
			this.button = Fields.create(this.type, this.name);
		}
		return this;
	}
	
	public Field button() {
		return button;
	}
	
	/* 
	 * @see TZ.forms.FormsID#id()
	 */
	@Override
	public String id() {
		return this.id;
	}
	/* 
	 * @see TZ.forms.FormsID#name()
	 */
	@Override
	public String name() {
		return this.name;
	}
	
	
}
