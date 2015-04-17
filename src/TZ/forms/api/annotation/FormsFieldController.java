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
public @interface FormsFieldController {

	/**
	 * @return The name of the type
	 */
	public String name();
	
	/**
	 * @return UNIQUE type of the form type
	 */
	public String type();
	
	/**
	 * @return the type of the component
	 */
	public Class<?> component();
	
	/**
	 * @return an array of extended types
	 */
	public String[] extend() default {};
	
	/**
	 * @param Field field - the field object
	 * @return static function
	 */
	public String create() default "create";
	
	/**
	 * EXTENDABLE(
	 * 		function: extend; 
	 * 		condition: builtExtend;
	 * )
	 * @param Field field - the field object
	 * @return static function
	 */
	public String built() default "built";
	
	/**
	 * CONDITION 
	 * @return if built will be extend
	 */
	public boolean builtExtend() default true;
	
	/**
	 * EXTENDABLE(
	 * 		function: extend; 
	 * 		condition: settingsExtend;
	 * )
	 * @param Field field - the field object
	 * @return static function
	 */
	public String settings() default "settings";
	
	/**
	 * CONDITION 
	 * @return if settings will be extend
	 */
	public boolean settingsExtend() default true;
	
	/**
	 * OPTIONALY
	 * @param Form form - the form object
	 * @param Field field - the field object
	 * @return static function
	 */
	public String validate() default "";
	
	/**
	 * @param Field field - the field object
	 * @param FormInput input - the input object of the form
	 * @return static function
	 */
	public String input() default "input";
	
}
