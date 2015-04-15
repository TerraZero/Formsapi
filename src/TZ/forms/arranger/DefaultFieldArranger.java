package TZ.forms.arranger;

import TZ.forms.api.Field;
import TZ.forms.api.Step;

/**
 * 
 * @author terrazero
 * @created Apr 14, 2015
 * 
 * @file DefaultFieldArranger.java
 * @project Forms
 * @identifier TZ.forms.arranger
 *
 */
public class DefaultFieldArranger implements FieldArranger {

	/* 
	 * @see TZ.forms.arranger.FieldArranger#arrange(TZ.forms.api.Step, int, TZ.forms.api.Field, int, int)
	 */
	@Override
	public int arrange(Step step, int index, int top, Field field, int width, int x) {
		int height = field.margin();
		if (field.optionLabel()) {
			field.label().setBounds(field.margin(), top + height, width - 2 * field.margin(), field.label().getHeight());
			height += field.label().getHeight();
		}
		if (field.optionComponent()) {
			field.component().setBounds(field.margin(), top + height, width - 2 * field.margin(), field.component().getHeight());
			height += field.component().getHeight();
		}
		return height + field.margin();
	}

}
