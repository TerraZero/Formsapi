package TZ.forms.api.arranger;

import TZ.forms.api.Field;
import TZ.forms.api.var.Var;

/**
 * 
 * @author Terra
 * @created 18.04.2015
 * 
 * @file DefaultContainerArranger.java
 * @project Formsapi
 * @identifier TZ.forms.api.arranger
 *
 */
public class DefaultContainerArranger implements FieldArranger {

	/* 
	 * @see TZ.forms.api.arranger.FieldArranger#arrange(TZ.forms.api.Field, TZ.forms.api.arranger.Arrange)
	 */
	@Override
	public void arrange(Field field, Arrange arrange) {
		int width = arrange.field.option("container:width").number();
		int fieldwidth = field.option("width").number(field.component().getWidth());
		int maxwidth = field.option("maxwidth").number(fieldwidth);
		
		arrange.w = width;
		if (fieldwidth > width) {
			field.option("fullwidth", new Var(true));
		}
		field.option("maxwidth", new Var(maxwidth));
		if (width > maxwidth) {
			arrange.w = maxwidth;
		}
		field.arrange(arrange);
		arrange.x += arrange.width + field.option("margin").number(5);
	}

}
