package TZ.forms.api.types;

import java.util.HashMap;
import java.util.Map;

import TZ.forms.api.Field;
import TZ.forms.api.Form;
import TZ.forms.api.annotation.FormsFieldController;
import TZ.forms.input.FormInput;
import TZ.sys.Init;
import TZ.sys.invoker.Invoker;
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

	private static Map<String, InvokeWrapper<FormsFieldController>> controllers;
	
	public static void init() {
		FieldControllers.controllers = new HashMap<String, InvokeWrapper<FormsFieldController>>();
		Invoker.each(FormsFieldController.class, (wrapper) -> {
			FieldControllers.controllers.put(wrapper.annotation().type(), wrapper);
		});
	}
	
	public static Map<String, InvokeWrapper<FormsFieldController>> types() {
		if (FieldControllers.controllers == null) {
			FieldControllers.init();
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
		FieldControllers.invokeSettings(field, field.type());
	}
	
	public static void invokeSettings(Field field, String type) {
		InvokeWrapper<FormsFieldController> wrapper = FieldControllers.wrapper(type);
		if (wrapper.annotation().settingsExtend()) {
			for (String extend : wrapper.annotation().extend()) {
				FieldControllers.invokeSettings(field, extend);
			}
		}
		wrapper.reflect().call(wrapper.annotation().settings(), field);
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
		FieldControllers.invokeInput(field, input, field.type());
	}
	
	public static void invokeInput(Field field, FormInput input, String type) {
		InvokeWrapper<FormsFieldController> wrapper = FieldControllers.wrapper(type);
		wrapper.reflect().call(wrapper.annotation().input(), field, input);
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
	
}
