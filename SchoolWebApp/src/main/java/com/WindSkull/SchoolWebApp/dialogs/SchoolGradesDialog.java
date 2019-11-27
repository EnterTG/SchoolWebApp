package com.WindSkull.SchoolWebApp.dialogs;

import com.WindSkull.SchoolWebApp.models.SchoolClassStudents;
import com.WindSkull.SchoolWebApp.renderers.GradesRenderer;
import com.holonplatform.core.Context;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.query.QueryConfigurationProvider;
import com.holonplatform.core.query.QueryFilter;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.PropertyListing;
import com.holonplatform.vaadin.flow.components.Selectable.SelectionMode;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.grid.GridVariant;

public class SchoolGradesDialog extends AbstractDialog implements QueryConfigurationProvider{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PropertyListing listing;
	
	private Integer classID;
	//private Integer subjectID;
	
	private GradesRenderer gradesRenderers;
	
	private boolean saveOperation = false;
	
	public SchoolGradesDialog(Integer classID,Integer subjectId)
	{
		this.classID = classID;
		//this.subjectID = subjectId;
		
		Datastore datastore = Context.get().resource(Datastore.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));
		
		//Notification.show("ClassId: " + classID + " subjectId: " + subjectId, 10000, Position.BOTTOM_CENTER);
		
		listing = Components.listing.properties(SchoolClassStudents.CLASSSTUDENTS).styleName("storefront").fullSize()
				.withThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS)
				.resizable(true)
				.hidden(SchoolClassStudents.STUDENTID)
				.hidden(SchoolClassStudents.CLASSID)
				.header(SchoolClassStudents.ID, "Oceny")
				.renderer(SchoolClassStudents.ID,gradesRenderers = new GradesRenderer(classID,subjectId))
				.dataSource(datastore, SchoolClassStudents.TARGET)
				.withQueryConfigurationProvider(this)
				.flexGrow(SchoolClassStudents.ID, 1)
				
				.withDefaultQuerySort(SchoolClassStudents.ID.asc())
				.selectionMode(SelectionMode.NONE)
				.expand(SchoolClassStudents.ID)
				.width(SchoolClassStudents.STUDENT_NAME, "150px")
				.width(SchoolClassStudents.STUDENT_SURNAME, "150px")
				.pageSize(25)
				//.displayAfter(SchoolClassStudents.ID, SchoolClassStudents.STUDENT_SURNAME)
				.build();
		
		add(Components.vl().fullSize()
		.addAndExpand(Components.hl().fullSize()
				.addAndExpand(listing,1)
				.build(),1)
		.add(Components.hl().fullWidth()
				.add(Components.button()
						.text("Zamknij")
						.onClick(evt -> {close();})
						.build())
				.build())
		.build()
		);

		addDetachListener(e -> close());
	}
	
	@Override
	protected void onDetach(DetachEvent detachEvent) {
		// TODO Auto-generated method stub
		super.onDetach(detachEvent);
		close();
	}
	@Override
	public void close() 
	{
		super.close();
		if(!saveOperation) 
		{
			saveOperation = true;
			gradesRenderers.save();
		}
	}
	
	@Override
	public QueryFilter getQueryFilter() 
	{
		return  SchoolClassStudents.CLASSID.eq(classID);
	}



}
