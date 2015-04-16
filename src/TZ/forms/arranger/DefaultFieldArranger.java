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
		int margin = field.option("margin").number(5);
		int height = margin;
		
		if (field.option("label").bool()) {
			field.label().setBounds(margin, top + height, width - 2 * margin, field.label().getHeight());
			height += field.label().getHeight();
		}
		if (field.option("component").bool()) {
			field.component().setBounds(margin, top + height, width - 2 * margin, field.component().getHeight());
			height += field.component().getHeight();
		}
		return height + margin;
	}

}
