package TZ.forms.api.excpetions;

/**
 * 
 * @author terrazero
 * @created Apr 23, 2015
 * 
 * @file APIException.java
 * @project Formsapi
 * @identifier TZ.forms.api.excpetion
 *
 */
public class APIException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected String type;
	protected Exception e;
	protected String message;
	protected Object[] additional;
	
	public APIException(Exception e) {
		this.e = e;
	}
	
	public APIException(Exception e, String message, Object... additonal) {
		this.e = e;
		this.message = message;
		this.additional = additonal;
	}
	
	public APIException(String message, Object... additional) {
		this.message = message;
		this.additional = additional;
	}
	
	protected void init() {
		this.type = "API ERROR";
	}
	
	public APIException additional(Object... additional) {
		this.additional = additional;
		return this;
	}
	
	public boolean isException() {
		return this.e != null;
	}
	
	public boolean hasAdditional() {
		return this.additional != null;
	}
	
	public Exception e() {
		return this.e;
	}
	
	public String message() {
		return this.message;
	}
	
	public Object[] additional() {
		return this.additional;
	}
	
	public String type() {
		return this.type;
	}
	
	/* 
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		return this.type + ": [" + this.e + ", " + this.message + "]";
	}

}
