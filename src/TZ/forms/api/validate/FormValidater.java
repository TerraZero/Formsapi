package TZ.forms.api.validate;

import java.util.List;

import TZ.forms.api.Form;
import TZ.forms.api.input.FormInput;

/**
 * 
 * @author terrazero
 * @created Apr 23, 2015
 * 
 * @file FormValidater.java
 * @project Formsapi
 * @identifier TZ.forms.api.validate
 *
 */
public class FormValidater {
	
	public static void validate(Form form, List<FormValidate> validates) {
		FormValidater.validate(form, form.getInputs(), validates);
	}

	public static void validate(Form form, FormInput input, List<FormValidate> validates) {
		for (FormValidate validate : validates) {
			validate.validate(form, input);
		}
	}
	
}
