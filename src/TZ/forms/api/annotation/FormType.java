package TZ.forms.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author terrazero
 * @created Apr 15, 2015
 * 
 * @file FormType.java
 * @project Forms
 * @identifier TZ.forms.api.annotation
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FormType {

	public String name();
	
	public Class<?> type();
	
	public String built() default "built";
	
	public String input() default "input";
	
}
