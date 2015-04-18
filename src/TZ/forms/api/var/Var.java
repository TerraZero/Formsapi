package TZ.forms.api.var;

/**
 * 
 * @author terrazero
 * @created Apr 16, 2015
 * 
 * @file Var.java
 * @project Formsapi
 * @identifier TZ.forms.api
 *
 */
public class Var {
	
	public static final int TYPE_EMPTY = -1;
	public static final int TYPE_STRING = 0;
	public static final int TYPE_INT = 1;
	public static final int TYPE_DOUBLE = 2;
	public static final int TYPE_FLOAT = 3;
	public static final int TYPE_BOOL = 4;
	public static final int TYPE_OBJECT = 5;

	private String string;
	private int number;
	private double decimal;
	private float floatNumber;
	private boolean bool;
	private Object object;
	
	private int type;
	
	public Var() {
		this.empty();
	}
	
	public Var(String var) {
		this.set(var);
	}
	
	public Var(int var) {
		this.set(var);
	}
	
	public Var(double var) {
		this.set(var);
	}
	
	public Var(float var) {
		this.set(var);
	}
	
	public Var(boolean var) {
		this.set(var);
	}
	
	public Var(Object var) {
		this.set(var);
	}
	
	public Var empty() {
		this.string = null;
		this.number = 0;
		this.decimal = 0;
		this.floatNumber = 0;
		this.bool = false;
		this.object = null;
		this.type = Var.TYPE_EMPTY;
		return this;
	}
	
	public Var set(String var) {
		this.empty();
		this.string = var;
		this.type = Var.TYPE_STRING;
		return this;
	}
	
	public Var set(int var) {
		this.empty();
		this.number = var;
		this.type = Var.TYPE_INT;
		return this;
	}
	
	public Var set(double var) {
		this.empty();
		this.decimal = var;
		this.type = Var.TYPE_DOUBLE;
		return this;
	}
	
	public Var set(float var) {
		this.empty();
		this.floatNumber = var;
		this.type = Var.TYPE_FLOAT;
		return this;
	}
	
	public Var set(boolean var) {
		this.empty();
		this.bool = var;
		this.type = Var.TYPE_BOOL;
		return this;
	}
	
	public Var set(Object var) {
		this.empty();
		this.object = var;
		this.type = Var.TYPE_OBJECT;
		return this;
	}
	
	public String string() {
		return this.string;
	}
	
	public String string(String fallback) {
		return (this.isEmpty() ? fallback : this.string);
	}
	
	public int number() {
		return this.number;
	}
	
	public int number(int fallback) {
		return (this.isEmpty() ? fallback : this.number);
	}
	
	public double decimal() {
		return this.decimal;
	}
	
	public double decimal(double fallback) {
		return (this.isEmpty() ? fallback : this.decimal);
	}
	
	public float floatNumber() {
		return this.floatNumber;
	}
	
	public float floatNumber(float fallback) {
		return (this.isEmpty() ? fallback : this.floatNumber);
	}
	
	public boolean bool() {
		return this.bool;
	}
	
	public boolean bool(boolean fallback) {
		return (this.isEmpty() ? fallback : this.bool);
	}
	
	@SuppressWarnings("unchecked")
	public<type> type object() {
		return (type)this.object;
	}
	
	@SuppressWarnings("unchecked")
	public<type> type object(type fallback) {
		return (this.isEmpty() ? fallback : (type)this.object);
	}
	
	public boolean isEmpty() {
		return this.type == Var.TYPE_EMPTY;
	}
	
	public boolean isString() {
		return this.type == Var.TYPE_STRING;
	}
	
	public boolean isInt() {
		return this.type == Var.TYPE_INT;
	}
	
	public boolean isDouble() {
		return this.type == Var.TYPE_DOUBLE;
	}
	
	public boolean isFloat() {
		return this.type == Var.TYPE_FLOAT;
	}
	
	public boolean isBool() {
		return this.type == Var.TYPE_BOOL;
	}
	
	public boolean isObject() {
		return this.type == Var.TYPE_OBJECT;
	}
	
	public int type() {
		return this.type;
	}
	
	/* 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String string = "VAR[";
		if (this.isEmpty()) {
			string += "EMPTY";
		} else if (this.isString()) {
			string += "STRING:" + this.string;
		} else if (this.isInt()) {
			string += "INT:" + this.number;
		} else if (this.isDouble()) {
			string += "DOUBLE:" + this.decimal;
		} else if (this.isFloat()) {
			string += "FLOAT:" + this.floatNumber;
		} else if (this.isBool()) {
			string += "BOOLEAN:" + this.bool;
		} else if (this.isObject()) {
			string += "OBJECT:" + (this.object == null ? "NULL" : this.object.getClass()) + ":" + this.object;
		}
		return string + "]";
	}
	
	public boolean compare(Var var) {
		if (var.type() != this.type) return false;
		return (this.bool == var.bool 
			&& this.number == var.number 
			&& this.decimal == var.decimal 
			&& this.floatNumber == var.floatNumber 
			&& (
				this.string != null 
				&& var.string != null 
				&& this.string.equals(var.string) 
				|| this.string == null 
				&& var.string == null
			) && (
				this.object != null 
				&& var.object != null 
				&& this.object.equals(var.object) 
				|| this.object == null 
				&& var.object == null
			)
		);
	}
	
}
