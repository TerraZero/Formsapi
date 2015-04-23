package TZ.forms.api.validate;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author terrazero
 * @created Apr 22, 2015
 * 
 * @file FormValidateHandler.java
 * @project Formsapi
 * @identifier TZ.forms.api.validate
 *
 */
public class FormValidateHandler {

	private List<FormsError> errors;
	private List<FormValidate> validates;
	
	public FormValidateHandler() {
		this.errors = new ArrayList<FormsError>();
	}
	
}
