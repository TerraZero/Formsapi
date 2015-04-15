package TZ.forms.arranger;

import TZ.forms.api.Field;
import TZ.forms.api.Step;

/**
 * 
 * @author terrazero
 * @created Apr 13, 2015
 * 
 * @file FieldArranger.java
 * @project Forms
 * @identifier TZ.forms
 *
 */
public interface FieldArranger {

	public int arrange(Step step, int index, int top, Field field, int width, int x);
	
}
