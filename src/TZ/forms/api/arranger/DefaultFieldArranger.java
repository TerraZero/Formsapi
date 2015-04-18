package TZ.forms.api.arranger;

import TZ.forms.api.Field;

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
	 * @see TZ.forms.api.arranger.FieldArranger#ar(TZ.forms.api.Field, TZ.forms.api.arranger.Arrange)
	 */
	@Override
	public void arrange(Field field, Arrange arrange) {
		int margin = field.option("margin").number(5);
		int height = margin;
		int fieldwidth = field.option("width").number(field.component().getWidth());
		
		if (field.option("fullwidth").bool(true)) {
			fieldwidth = arrange.w - 2 * margin;
		}
		if (field.option("label").bool()) {
			field.label().setBounds(margin + arrange.x, arrange.y + height, fieldwidth, field.label().getHeight());
			height += field.label().getHeight();
		}
		if (field.option("component").bool()) {
			field.component().setBounds(margin + arrange.x, arrange.y + height, fieldwidth, field.component().getHeight());
			height += field.component().getHeight();
		}
		arrange.width = fieldwidth;
		arrange.height = height + margin;
	}

}
