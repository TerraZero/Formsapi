package TZ.forms.api.controllers;

import java.util.HashMap;
import java.util.Map;

import TZ.forms.api.Field;
import TZ.forms.api.Form;
import TZ.forms.api.annotation.FormsFieldController;
import TZ.forms.api.input.FormInput;
import TZ.forms.api.var.Var;
import TZ.sys.Init;
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
@Init(name = "Field Types")
public class FieldControllers {
	
	public static final String defaultSet = "Forms";
	public static String initSet = FieldControllers.defaultSet;

	private static Map<String, InvokeWrapper<FormsFieldController>> controllers;
	
	private static Map<String, CallFunc> getters;
	private static Map<String, CallFunc> setters;
	
	public static void init(String set) {
		FieldControllers.controllers = new HashMap<String, InvokeWrapper<FormsFieldController>>();
		FieldControllers.getters = new HashMap<String, CallFunc>();
		FieldControllers.setters = new HashMap<String, CallFunc>();
		
		Invoker.each(FormsFieldController.class, (wrapper) -> {
			if (wrapper.annotation().set().equals(FieldControllers.initSet) || (wrapper.annotation().set().equals(FieldControllers.defaultSet) && !FieldControllers.controllers.containsKey(wrapper.annotation().type()))) {
				FieldControllers.controllers.put(wrapper.annotation().type(), wrapper);
			}
		});
	}
	
	public static Map<String, InvokeWrapper<FormsFieldController>> types() {
		if (FieldControllers.controllers == null) {
			FieldControllers.init(FieldControllers.initSet);
		}
		return FieldControllers.controllers;
	}
	
	public static Field create(String type, String name) {
		Field field = new Field(type, name);
		FieldControllers.invokeCreate(field, type);
		return FieldControllers.built(field);
	}
	
	public static Field create(String type, String name, String id) {
		Field field = new Field(type, name, id);
		FieldControllers.invokeCreate(field, type);
		return FieldControllers.built(field);
	}
	
	public static void invokeCreate(Field field, String type) {
		InvokeWrapper<FormsFieldController> wrapper = FieldControllers.wrapper(type);
		wrapper.reflect().call(wrapper.annotation().create(), field);
	}
	
	public static void settings(Field field) {
		if (field.options().is("default")) {
			field.set(field.option("default"));
		}
		FieldControllers.invokeSettings(field, field.type());
	}
	
	public static void invokeSettings(Field field, String type) {
		InvokeWrapper<FormsFieldController> wrapper = FieldControllers.wrapper(type);
		if (wrapper.annotation().settingsExtend()) {
			for (String extend : wrapper.annotation().extend()) {
				FieldControllers.invokeSettings(field, extend);
			}
		}
		if (wrapper.annotation().settings().length() != 0) {
			wrapper.reflect().call(wrapper.annotation().settings(), field);
		}
	}
	
	public static Field built(Field field) {
		FieldControllers.invokeBuilt(field, field.type());
		return field;
	}
	
	public static void invokeBuilt(Field field, String type) {
		InvokeWrapper<FormsFieldController> wrapper = FieldControllers.wrapper(type);
		if (wrapper.annotation().builtExtend()) {
			for (String extend : wrapper.annotation().extend()) {
				FieldControllers.invokeBuilt(field, extend);
			}
		}
		wrapper.reflect().call(wrapper.annotation().built(), field);
	}
	
	public static void input(Field field, FormInput input) {
		Var var = field.get();
		if (!field.option("notinput").bool()) {
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
		FieldControllers.invokeInput(field, input, field.type());
	}
	
	public static void invokeInput(Field field, FormInput input, String type) {
		InvokeWrapper<FormsFieldController> wrapper = FieldControllers.wrapper(type);
		if (wrapper.annotation().input().length() != 0) {
			wrapper.reflect().call(wrapper.annotation().input(), field, input);
		}
	}
	
	public static void validate(Form form, Field field) {
		FieldControllers.invokeValidate(form, field, field.type());
	}
	
	public static void invokeValidate(Form form, Field field, String type) {
		InvokeWrapper<FormsFieldController> wrapper = FieldControllers.wrapper(type);
		if (wrapper.annotation().validate().length() != 0) {
			wrapper.reflect().call(wrapper.annotation().validate(), form, field);
		}
	}
	
	public static InvokeWrapper<FormsFieldController> wrapper(String type) {
		return FieldControllers.types().get(type);
	}
	
	public static Var get(Field field) {
		Var var = new Var();
		CallFunc getter = FieldControllers.getters.get(field.type());
		if (getter == null) {
			InvokeWrapper<FormsFieldController> wrapper = FieldControllers.wrapper(field.type());
			getter = wrapper.reflect().getCall(wrapper.annotation().getter(), Field.class, Var.class);
			FieldControllers.getters.put(field.type(), getter);
		}
		getter.call(field, var);
		return var;
	}
	
	public static Var get(Field field, Var var) {
		CallFunc getter = FieldControllers.getters.get(field.type());
		if (getter == null) {
			InvokeWrapper<FormsFieldController> wrapper = FieldControllers.wrapper(field.type());
			getter = wrapper.reflect().getCall(wrapper.annotation().getter(), Field.class, Var.class);
			FieldControllers.getters.put(field.type(), getter);
		}
		getter.call(field, var);
		return var;
	}
	
	public static void set(Field field, Var var) {
		CallFunc setter = FieldControllers.setters.get(field.type());
		if (setter == null) {
			InvokeWrapper<FormsFieldController> wrapper = FieldControllers.wrapper(field.type());
			setter = wrapper.reflect().getCall(wrapper.annotation().setter(), Field.class, Var.class);
			FieldControllers.setters.put(field.type(), setter);
		}
		setter.call(field, var);
	}
	
}
