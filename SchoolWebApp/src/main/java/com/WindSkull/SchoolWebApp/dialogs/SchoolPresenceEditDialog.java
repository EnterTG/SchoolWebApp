package com.WindSkull.SchoolWebApp.dialogs;

import java.util.ArrayList;
import java.util.List;

import com.WindSkull.SchoolWebApp.forms.PresenceForm;
import com.WindSkull.SchoolWebApp.models.SchoolClassStudents;
import com.WindSkull.SchoolWebApp.renderers.GradesRenderer;
import com.holonplatform.core.query.QueryConfigurationProvider;
import com.holonplatform.core.query.QueryFilter;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.Input;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class SchoolPresenceEditDialog extends AbstractDialog implements QueryConfigurationProvider{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PresenceForm listing;
	
	private Integer classID;
	//private Integer subjectID;
	
	private Input<java.util.Date> dateInput;
	
	private List<GradesRenderer> gradesRenderers = new ArrayList<>();
	private HorizontalLayout hl;
	public SchoolPresenceEditDialog(Integer classID,Integer subjectId)
	{
		this.classID = classID;
		
		listing = new PresenceForm(classID,subjectId);
		
		listing.setSizeFull();
		add(Components.vl().fullSize()
			.add(Components.hl().fullWidth()
				.add(dateInput = Components.input.date().build())
				.add(Components.button("Dodaj", e -> {
					listing.addPresenceCheck(dateInput.getValue());
					hl.removeAll();
					listing = new PresenceForm(classID,subjectId);
					listing.setSizeFull();
					hl.add(listing);
				}))
				.build())
			.addAndExpand(
				hl = Components.hl().fullSize()
				.add(listing)
				.build(), 1)
			.add(
				Components.hl().fullWidth()
					.add(Components.button()
							.text("Zamknij")
							.onClick(evt -> {close();})
							.build())
					.build()).build());
		
		addDetachListener(e -> close());
	}
	
@Override
protected void onDetach(DetachEvent detachEvent) {
	super.onDetach(detachEvent);
	close();
}
	@Override
	public void close() 
	{
		super.close();
		gradesRenderers.forEach(r -> r.save());
	}
	
	@Override
	public QueryFilter getQueryFilter() 
	{
		return  SchoolClassStudents.CLASSID.eq(classID);
	}



}
