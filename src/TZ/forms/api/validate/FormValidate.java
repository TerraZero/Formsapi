package TZ.forms.api.validate;

import TZ.forms.api.Form;
import TZ.forms.api.input.FormInput;

/**
 * 
 * @author terrazero
 * @created Apr 22, 2015
 * 
 * @file FormValidate.java
 * @project Formsapi
 * @identifier TZ.forms.api.validate
 *
 */
public interface FormValidate {

	public void validate(Form form, FormInput input);
	
}
