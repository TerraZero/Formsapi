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
		
		arrange.w = width;
		field.option("fullwidth", new Var(true));
		field.arrange(arrange);
		arrange.x += arrange.width + field.option("margin").number(5);
	}

}
