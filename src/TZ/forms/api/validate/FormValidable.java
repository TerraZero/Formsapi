package TZ.forms.api.validate;

/**
 * 
 * @author terrazero
 * @created Apr 22, 2015
 * 
 * @file FormValidable.java
 * @project Formsapi
 * @identifier TZ.forms.api.validate
 *
 */
public interface FormValidable<handler> {

	public handler validate(FormValidate validate);
	
	public handler error(FormsError error);
	
}
