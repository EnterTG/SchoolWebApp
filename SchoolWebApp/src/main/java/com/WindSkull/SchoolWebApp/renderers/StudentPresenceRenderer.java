package com.WindSkull.SchoolWebApp.renderers;

import com.WindSkull.SchoolWebApp.forms.PresenceForm.StudentPresence;
import com.WindSkull.SchoolWebApp.services.SchoolPresenceStudentsService;
import com.holonplatform.core.Context;
import com.holonplatform.vaadin.flow.components.Components;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class StudentPresenceRenderer extends ComponentRenderer<Component,StudentPresence>{



	private static final long serialVersionUID = 1L;


	private Long presenceID;
	public StudentPresenceRenderer(Long presenceId)
	{
		this.presenceID = presenceId;
	}
	
	
	@Override
	public Component createComponent(StudentPresence item) {
		Boolean b = item.presences.get(presenceID);
		return Components.input.boolean_().withValue(b == null ? false : b).onClick(e ->
		{
			SchoolPresenceStudentsService presenceStudentsService = Context.get().resource(	SchoolPresenceStudentsService.class)
			.orElseThrow(() -> new IllegalStateException("Cannot retrieve SchoolPresenceStudentsService from Context."));
			
			item.presences.put(presenceID, e.getSource().getValue());
			presenceStudentsService.save(item.getPresenceStudentsBox(presenceID));
		}).build().getComponent();
	}

}
