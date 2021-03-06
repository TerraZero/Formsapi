package TZ.forms.api;

import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JLabel;

import TZ.forms.Forms;
import TZ.forms.FormsID;
import TZ.forms.api.arranger.Arrange;
import TZ.forms.api.arranger.DefaultFieldArranger;
import TZ.forms.api.arranger.FieldArranger;
import TZ.forms.api.controllers.Fields;
import TZ.forms.api.var.Options;
import TZ.forms.api.var.Var;
import TZ.sys.Sys;
import TZ.sys.lists.Weighted;

/**
 * 
 * @author terrazero
 * @created Apr 13, 2015
 * 
 * @file Field.java
 * @project Forms
 * @identifier TZ.forms
 *
 */
public class Field implements FormsID, Weighted {
	
	private static DefaultFieldArranger defaultArranger;
	
	public static DefaultFieldArranger getDefaultArranger() {
		if (Field.defaultArranger == null) {
			Field.defaultArranger = new DefaultFieldArranger();
		}
		return Field.defaultArranger;
	}
	
	public static void invoke(String id, String type, String operation, Object... parameters) {
		Sys.cascade(new String[] {
			"field:" + operation,
			"field:" + type + ":" + operation,
			id + ":" + type + ":" + operation,
		}, parameters);
	}

	private String name;
	private String id;
	private String type;
	private FieldArranger arranger;
	private Options options;
	private int weight;
	
	protected JLabel label;
	protected JComponent component;
	
	public Field(String type, String name) {
		this.name = name;
		this.type = type;
		this.id = Forms.toID(this.name);
		this.init();
		Fields.create(this);
	}
	
	public Field(String type, String name, String id) {
		this.name = name;
		this.id = id;
		this.type = type;
		this.init();
	}
	
	public Field(String type, String name, JComponent component) {
		this.name = name;
		this.id = Forms.toID(this.name);
		this.component = component;
		this.type = type;
		this.init();
	}
	
	public Field(String type, String name, String id, JComponent component) {
		this.name = name;
		this.id = id;
		this.component = component;
		this.type = type;
		this.init();
	}
	
	public void init() {
		this.options = new Options();
		this.arranger = Field.getDefaultArranger();
		
		this.options.set("component", new Var(true));
		this.options.set("label", new Var(true));
	}
	
	public Field show() {
		Field.invoke(this.id, this.type, "show", this);
		
		if (this.option("label").bool()) {
			this.label = new JLabel();
			this.label.setText(this.name);
			this.label.setSize(0, 25);
		}
		return this;
	}
	
	public String id() {
		return this.id;
	}
	
	public Field id(String id) {
		this.id = id;
		return this;
	}
	
	public String name() {
		return this.name;
	}
	
	public Field name(String name) {
		this.name = name;
		return this;
	}
	
	public String type() {
		return this.type;
	}
	
	@SuppressWarnings("unchecked")
	public<component extends JComponent> component getComponent() {
		return (component)this.component;
	}
	
	public JLabel label() {
		return this.label;
	}
	
	public Field label(JLabel label) {
		this.label = label;
		return this;
	}
	
	public JComponent component() {
		return this.component;
	}
	
	public Field component(JComponent component) {
		this.component = component;
		return this;
	}
	
	public void arrange(Arrange arrange) {
		arranger.arrange(this, arrange);
	}
	
	public Field arranger(FieldArranger arranger) {
		this.arranger = arranger;
		return this;
	}
	
	public FieldArranger arranger() {
		return this.arranger;
	}
	
	public Field manageAdd(Container container) {
		if (this.option("label").bool()) container.add(this.label);
		if (this.option("component").bool()) container.add(this.component);
		return this;
	}
	
	public Var option(String name) {
		Var var = this.options.get(name);
		return (var == null ? new Var() : var);
	}
	
	public Var getOption(String name) {
		return this.options.get(name);
	}
	
	public Field option(String name, Var option) {
		this.options.set(name, option);
		return this;
	}
	
	public Options options() {
		return this.options;
	}
	
	public Field set(Var var) {
		Fields.set(this, var);
		return this;
	}
	
	public Var get() {
		return Fields.get(this);
	}
	
	public Var get(Var var) {
		return Fields.get(this, var);
	}

	/* 
	 * @see TZ.sys.lists.Weighted#weight()
	 */
	@Override
	public int weight() {
		return this.weight;
	}
	
	public Field weight(int weight) {
		this.weight = weight;
		return this;
	}
	
}
