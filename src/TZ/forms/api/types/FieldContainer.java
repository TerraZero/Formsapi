package TZ.forms.api.types;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import TZ.forms.api.Field;
import TZ.forms.api.arranger.Arrange;
import TZ.forms.api.arranger.DefaultContainerArranger;
import TZ.forms.api.arranger.FieldArranger;
import TZ.forms.api.var.Var;

/**
 * 
 * @author Terra
 * @created 18.04.2015
 * 
 * @file OperationContainer.java
 * @project Formsapi
 * @identifier TZ.forms.api.types
 *
 */
public class FieldContainer extends JPanel implements IFieldContainer {
	
	private static DefaultContainerArranger defaultArranger;
	
	public static DefaultContainerArranger getDefaultArranger() {
		if (FieldContainer.defaultArranger == null) {
			FieldContainer.defaultArranger = new DefaultContainerArranger();
		} 
		return FieldContainer.defaultArranger;
	}

	private List<Field> fields;
	private FieldArranger arranger;
	
	private static final long serialVersionUID = 1L;
	
	public FieldContainer() {
		this.fields = new ArrayList<Field>();
		this.arranger = FieldContainer.getDefaultArranger();
		this.setLayout(null);
	}

	/* 
	 * @see TZ.forms.api.types.FieldContainer#fields()
	 */
	@Override
	public List<Field> fields() {
		return this.fields;
	}
	
	public void add(Field field) {
		this.fields.add(field);
	}
	
	public void arrange(Field container, int width, int x, int y) {
		Arrange arrange = new Arrange();
		arrange.w = width;
		arrange.y = y;
		arrange.x = x;
		arrange.index = 0;
		arrange.field = container;
		container.option("container:width", new Var(width / this.fields.size()));
		int height = 0;
		
		for (Field field : this.fields) {
			field.manageAdd(this);
			this.arranger.arrange(field, arrange);
			height = (height > arrange.height ? height : arrange.height);
			arrange.index++;
		}
		this.setSize(0, height);
	}

}
