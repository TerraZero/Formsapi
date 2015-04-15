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
	
	public static void main(String[] args) {
		FieldTypes.init();
	}
	
	public static void init() {
		FieldTypes.types = new HashMap<String, InvokeWrapper<FormType>>();
		Invoker.each(FormType.class, (wrapper) -> {
			FieldTypes.types.put(wrapper.annotation().type().getName(), wrapper);
		});
	}
	
	public static void built(Field field) {
		FieldTypes.built(field, field.component().getClass());
	}
	
	public static void built(Field field, Class<?> type) {
		InvokeWrapper<FormType> wrapper = FieldTypes.wrapper(type);
		for (Class<?> extend : wrapper.annotation().builtExtends()) {
			FieldTypes.built(field, extend);
		}
		wrapper.reflect().call(wrapper.annotation().built(), field);
	}
	
	public static void input(Field field, FormInput input) {
		InvokeWrapper<FormType> wrapper = FieldTypes.wrapper(field.component().getClass());
		wrapper.reflect().call(wrapper.annotation().input(), field, input);
	}
	
	public static InvokeWrapper<FormType> wrapper(Class<?> type) {
		return FieldTypes.types.get(type.getName());
	}
	
}
