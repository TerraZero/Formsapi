package TZ.forms.api.types;

import java.util.HashMap;
import java.util.Map;

import TZ.forms.api.Field;
import TZ.forms.api.annotation.FormType;
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
public class FieldTypes {

	private static Map<String, InvokeWrapper<FormType>> types;
	
	public static void init() {
		FieldTypes.types = new HashMap<String, InvokeWrapper<FormType>>();
		Invoker.each(FormType.class, (wrapper) -> {
			FieldTypes.types.put(wrapper.annotation().type(), wrapper);
		});
	}
	
	public static Map<String, InvokeWrapper<FormType>> types() {
		if (FieldTypes.types == null) {
			FieldTypes.init();
		}
		return FieldTypes.types;
	}
	
	public static Field create(String type, String name) {
		Field field = new Field(type, name);
		FieldTypes.invokeCreate(field, type);
		return FieldTypes.built(field);
	}
	
	public static Field create(String type, String name, String id) {
		Field field = new Field(type, name, id);
		FieldTypes.invokeCreate(field, type);
		return FieldTypes.built(field);
	}
	
	public static void invokeCreate(Field field, String type) {
		InvokeWrapper<FormType> wrapper = FieldTypes.wrapper(type);
		wrapper.reflect().call(wrapper.annotation().create(), field);
	}
	
	public static void settings(Field field) {
		FieldTypes.invokeSettings(field, field.type());
	}
	
	public static void invokeSettings(Field field, String type) {
		InvokeWrapper<FormType> wrapper = FieldTypes.wrapper(type);
		wrapper.reflect().call(wrapper.annotation().settings(), field);
	}
	
	public static Field built(Field field) {
		FieldTypes.built(field, field.type());
		return field;
	}
	
	public static void built(Field field, String type) {
		InvokeWrapper<FormType> wrapper = FieldTypes.wrapper(type);
		for (String extend : wrapper.annotation().extend()) {
			FieldTypes.built(field, extend);
		}
		wrapper.reflect().call(wrapper.annotation().built(), field);
	}
	
	public static void input(Field field, FormInput input) {
		InvokeWrapper<FormType> wrapper = FieldTypes.wrapper(field.type());
		wrapper.reflect().call(wrapper.annotation().input(), field, input);
	}
	
	public static InvokeWrapper<FormType> wrapper(String type) {
		return FieldTypes.types().get(type);
	}
	
}
