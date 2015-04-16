package TZ.forms.api;

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

	private String string;
	private int number;
	private double decimal;
	private float floatNumber;
	private boolean bool;
	
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
	
	public Var empty() {
		this.string = null;
		this.number = 0;
		this.decimal = 0;
		this.floatNumber = 0;
		this.bool = false;
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
	
	public int type() {
		return this.type;
	}
	
}
