package TZ.forms.api.controllers;

import java.util.HashMap;
import java.util.Map;

import TZ.forms.Forms;
import TZ.forms.api.Field;
import TZ.forms.api.Form;
import TZ.forms.api.annotation.FormsFieldController;
import TZ.forms.api.input.FormInput;
import TZ.forms.api.var.Var;
import TZ.sys.Mod;
import TZ.sys.invoker.Invoker;
import TZ.sys.invoker.reflect.CallFunc;
import TZ.sys.invoker.reflect.InvokeWrapper;

/**
 * 
 * @author terrazero
 * @created Apr 15, 2015
 * 
 * @file FieldTypes.java
 * @project Formsapi
 * @identifier TZ.forms.api.types
 *
 */
@Mod(name = "Field Types", register = "register")
public class Fields {
	
	public static final String defaultSet = "Forms";
	public static String initSet = Fields.defaultSet;

	private static Map<String, InvokeWrapper<FormsFieldController>> controllers;
	
	private static Map<String, CallFunc> getters;
	private static Map<String, CallFunc> setters;
	
	public static void register() {
		Fields.controllers = new HashMap<String, InvokeWrapper<FormsFieldController>>();
		Fields.getters = new HashMap<String, CallFunc>();
		Fields.setters = new HashMap<String, CallFunc>();
		
		Invoker.each(FormsFieldController.class, (wrapper) -> {
			if (wrapper.annotation().set().equals(Fields.initSet) || (wrapper.annotation().set().equals(Fields.defaultSet) && !Fields.controllers.containsKey(wrapper.annotation().type()))) {
				Fields.controllers.put(wrapper.annotation().type(), wrapper);
			}
		});
	}
	
	public static Map<String, InvokeWrapper<FormsFieldController>> types() {
		if (Fields.controllers == null) {
			Fields.register();
		}
		return Fields.controllers;
	}
	
	public static void create(Field field) {
		Fields.invokeCreate(field, field.type());
		Fields.built(field);
	}
	
	public static Field create(String type, String name) {
		return Fields.create(type, name, Forms.toID(name));
	}
	
	public static Field create(String type, String name, String id) {
		Field field = new Field(type, name, id);
		Fields.invokeCreate(field, type);
		return Fields.built(field);
	}
	
	public static void invokeCreate(Field field, String type) {
		InvokeWrapper<FormsFieldController> wrapper = Fields.wrapper(type);
		wrapper.reflect().call(wrapper.annotation().create(), field);
	}
	
	public static void settings(Field field, Form form) {
		if (field.options().is("default")) {
			field.set(field.option("default"));
		}
		Fields.invokeSettings(field, field.type(), form);
	}
	
	public static void invokeSettings(Field field, String type, Form form) {
		InvokeWrapper<FormsFieldController> wrapper = Fields.wrapper(type);
		if (wrapper.annotation().settingsExtend()) {
			for (String extend : wrapper.annotation().extend()) {
				Fields.invokeSettings(field, extend, form);
			}
		}
		if (wrapper.annotation().settings().length() != 0) {
			wrapper.reflect().call(wrapper.annotation().settings(), field, form);
		}
	}
	
	public static Field built(Field field) {
		Fields.invokeBuilt(field, field.type());
		return field;
	}
	
	public static void invokeBuilt(Field field, String type) {
		InvokeWrapper<FormsFieldController> wrapper = Fields.wrapper(type);
		if (wrapper.annotation().builtExtend()) {
			for (String extend : wrapper.annotation().extend()) {
				Fields.invokeBuilt(field, extend);
			}
		}
		if (wrapper.annotation().built().length() != 0) {
			wrapper.reflect().call(wrapper.annotation().built(), field);
		}
	}
	
	public static void input(Field field, FormInput input) {
		if (!field.option("notinput").bool()) {
			Var var = field.get();
			if (field.option("strict").bool()) {
				if (field.option("default").compare(var)) {
					input.set(field, new Var());
				} else {
					input.set(field, var);
				}
			} else {
				input.set(field, var);
			}
		}
		Fields.invokeInput(field, input, field.type());
	}
	
	public static void invokeInput(Field field, FormInput input, String type) {
		InvokeWrapper<FormsFieldController> wrapper = Fields.wrapper(type);
		if (wrapper.annotation().input().length() != 0) {
			wrapper.reflect().call(wrapper.annotation().input(), field, input);
		}
	}
	
	public static void validate(Form form, Field field) {
		Fields.invokeValidate(form, field, field.type());
	}
	
	public static void invokeValidate(Form form, Field field, String type) {
		InvokeWrapper<FormsFieldController> wrapper = Fields.wrapper(type);
		if (wrapper.annotation().validate().length() != 0) {
			wrapper.reflect().call(wrapper.annotation().validate(), form, field);
		}
	}
	
	public static InvokeWrapper<FormsFieldController> wrapper(String type) {
		return Fields.types().get(type);
	}
	
	public static Var get(Field field) {
		Var var = new Var();
		CallFunc getter = Fields.getters.get(field.type());
		if (getter == null) {
			InvokeWrapper<FormsFieldController> wrapper = Fields.wrapper(field.type());
			getter = wrapper.reflect().getCall(wrapper.annotation().getter(), Field.class, Var.class);
			Fields.getters.put(field.type(), getter);
		}
		getter.call(field, var);
		return var;
	}
	
	// TODO getter und setter extendable machen
	public static Var get(Field field, Var var) {
		CallFunc getter = Fields.getters.get(field.type());
		if (getter == null) {
			InvokeWrapper<FormsFieldController> wrapper = Fields.wrapper(field.type());
			getter = wrapper.reflect().getCall(wrapper.annotation().getter(), Field.class, Var.class);
			Fields.getters.put(field.type(), getter);
		}
		getter.call(field, var);
		return var;
	}
	
	public static void set(Field field, Var var) {
		CallFunc setter = Fields.setters.get(field.type());
		if (setter == null) {
			InvokeWrapper<FormsFieldController> wrapper = Fields.wrapper(field.type());
			setter = wrapper.reflect().getCall(wrapper.annotation().setter(), Field.class, Var.class);
			Fields.setters.put(field.type(), setter);
		}
		setter.call(field, var);
	}
	
}
