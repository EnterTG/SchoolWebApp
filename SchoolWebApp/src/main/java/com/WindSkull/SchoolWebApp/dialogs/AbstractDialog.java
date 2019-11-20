package com.WindSkull.SchoolWebApp.dialogs;

import com.holonplatform.vaadin.flow.components.Components;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasTheme;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;

public abstract class AbstractDialog extends Dialog implements HasTheme {

	private static final long serialVersionUID = 1L;

	private Div content;

	public AbstractDialog() {
		// default size
		//this.setSizeFull();
		setWidth("1100px");
		//setHeight("600px");
		//setMinWidth("700px");
		// custome css style
		getThemeNames().add("orders");
		// make content scrollable
		content = Components.div().build();
		content.setSizeFull();
		content.getStyle().set("overflow", "auto");

		add(content);
	}

	protected void setContent(Component component) {
		content.add(component);
	}
	
	
	public void addContent()
	{
		
	}
}
