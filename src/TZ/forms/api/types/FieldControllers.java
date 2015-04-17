package TZ.forms.api.types;

import java.util.HashMap;
import java.util.Map;

import TZ.forms.api.Field;
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
		wrapper.reflect().call(wrapper.annotation().settings(), field);
	}
	
	public static Field built(Field field) {
		FieldControllers.built(field, field.type());
		return field;
	}
	
	public static void built(Field field, String type) {
		InvokeWrapper<FormsFieldController> wrapper = FieldControllers.wrapper(type);
		for (String extend : wrapper.annotation().extend()) {
			FieldControllers.built(field, extend);
		}
		wrapper.reflect().call(wrapper.annotation().built(), field);
	}
	
	public static void input(Field field, FormInput input) {
		InvokeWrapper<FormsFieldController> wrapper = FieldControllers.wrapper(field.type());
		wrapper.reflect().call(wrapper.annotation().input(), field, input);
	}
	
	public static InvokeWrapper<FormsFieldController> wrapper(String type) {
		return FieldControllers.types().get(type);
	}
	
}
