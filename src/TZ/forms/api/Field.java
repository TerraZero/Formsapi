package TZ.forms.api;

import java.awt.Container;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;

import TZ.forms.Forms;
import TZ.forms.FormsID;
import TZ.forms.arranger.DefaultFieldArranger;
import TZ.forms.arranger.FieldArranger;
import TZ.sys.Sys;
import TZ.sys.invoker.reflect.Reflect;

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
public class Field implements FormsID {
	
	private static DefaultFieldArranger defaultArranger;
	private static Map<String, Reflect> types;
	
	public static DefaultFieldArranger getDefaultArranger() {
		if (Field.defaultArranger == null) {
			Field.defaultArranger = new DefaultFieldArranger();
		}
		return Field.defaultArranger;
	}
	
	public static void invoke(String id, String type, Object... parameters) {
		
	}

	private String name;
	private String id;
	private FieldArranger arranger;
	private int margin;
	private boolean built;
	
	private boolean optionLabel;
	private boolean optionComponent;
	
	protected String type;
	protected JComponent component;
	protected JLabel label;
	
	public Field(String name) {
		this.name = name;
		this.id = Forms.toID(this.name);
		this.init();
	}
	
	public Field(String name, String id) {
		this.name = name;
		this.id = id;
		this.init();
	}
	
	public Field(String name, JComponent component, String type) {
		this.name = name;
		this.id = Forms.toID(this.name);
		this.component = component;
		this.type = type;
		this.init();
	}
	
	public Field(String name, String id, JComponent component, String type) {
		this.name = name;
		this.id = id;
		this.component = component;
		this.type = type;
		this.init();
	}
	
	public void init() {
		this.arranger = Field.getDefaultArranger();
		this.margin = 5;
		this.built = false;
		
		this.optionLabel = true;
		this.optionComponent = true;
	}
	
	public Field built() {
		return this.built(false);
	}
	
	public Field built(boolean force) {
		if (!this.built || force) {
			this.built = true;
			Field.invoke(this.id, this.type, this);
			
			if (this.optionLabel) {
				this.label = new JLabel();
				this.label.setText(this.name);
				this.label.setSize(0, 25);
			}
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
	
	public Field component(JComponent component, String type) {
		this.component = component;
		this.type = type;
		return this;
	}
	
	public int arrange(Step step, int index, int top, int width, int x) {
		return arranger.arrange(step, index, top, this, width, x);
	}
	
	public Field arranger(FieldArranger arranger) {
		this.arranger = arranger;
		return this;
	}
	
	public FieldArranger arranger() {
		return this.arranger;
	}
	
	public Field margin(int margin) {
		this.margin = margin;
		return this;
	}
	
	public int margin() {
		return this.margin;
	}
	
	public Field manageAdd(Container container, Form form) {
		if (this.optionComponent) container.add(this.component);
		if (this.optionLabel) container.add(this.label);
		return this;
	}
	
	public Field optionLabel(boolean option) {
		this.optionLabel = option;
		return this;
	}
	
	public Field optionComponent(boolean option) {
		this.optionComponent = option;
		return this;
	}
	
	public boolean optionLabel() {
		return this.optionLabel;
	}
	
	public boolean optionComponent() {
		return this.optionComponent;
	}
	
}
