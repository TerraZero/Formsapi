package TZ.forms;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

import TZ.forms.api.Form;

/**
 * 
 * @author terrazero
 * @created Apr 13, 2015
 * 
 * @file FormFrame.java
 * @project Forms
 * @identifier TZ.forms
 *
 */
public class FormFrame {
	
	public static ComponentListener getDefaultArrangerListener(FormFrame frame) {
		return new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				frame.form().currentStep().arrange(frame.frame().getWidth(), 0, 0);
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
			}
			
		};
	}
	
	protected JFrame frame;
	protected Form form;
	
	public FormFrame(Form form) {
		this.form = form;
	}
	
	public FormFrame(Form form, JFrame frame) {
		this.form = form;
		this.frame = frame;
	}
	
	public Form form() {
		return this.form;
	}
	
	public JFrame frame() {
		return this.frame;
	}
	
	public FormFrame frame(int width, int height) {
		if (this.frame == null) this.frame = new JFrame();
		this.frame.setTitle(this.form.name());
		this.frame.setSize(width, height);
		this.frame.setLocationRelativeTo(null);
		this.frame.setContentPane(Forms.panel(form, width, 5));
		return this;
	}
	
	public FormFrame mode(int mode) {
		this.frame.setDefaultCloseOperation(mode);
		return this;
	}
	
	public FormFrame show() {
		Forms.invoke(this.form.id(), "show", this);
		this.frame.addComponentListener(FormFrame.getDefaultArrangerListener(this));
		this.frame.setVisible(true);
		return this;
	}

}
