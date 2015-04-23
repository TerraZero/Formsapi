package TZ.forms.api.excpetions;

/**
 * 
 * @author terrazero
 * @created Apr 23, 2015
 * 
 * @file InvokeException.java
 * @project Formsapi
 * @identifier TZ.forms.api.excpetion
 *
 */
public class InvokeException extends APIException {

	private static final long serialVersionUID = 1L;

	public InvokeException(Exception e, String message, String id, String operation, Object[] parameters) {
		super(e, message, id, operation, parameters);
	}
	
	/* 
	 * @see TZ.forms.api.excpetion.APIException#init()
	 */
	@Override
	protected void init() {
		super.init();
		this.type = "INVOKE ERROR";
	}

}
