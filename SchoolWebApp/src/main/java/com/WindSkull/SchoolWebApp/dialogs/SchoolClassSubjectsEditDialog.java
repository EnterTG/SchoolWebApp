package com.WindSkull.SchoolWebApp.dialogs;

import com.WindSkull.SchoolWebApp.models.SchoolClass;
import com.WindSkull.SchoolWebApp.models.SchoolClassSubject;
import com.WindSkull.SchoolWebApp.models.SchoolSubject;
import com.WindSkull.SchoolWebApp.models.User;
import com.WindSkull.SchoolWebApp.services.SchoolClassSubjectsService;
import com.holonplatform.core.Context;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.core.query.QueryConfigurationProvider;
import com.holonplatform.core.query.QueryFilter;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.PropertyListing;
import com.holonplatform.vaadin.flow.components.Selectable.SelectionMode;
import com.holonplatform.vaadin.flow.components.SingleSelect;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;

public class SchoolClassSubjectsEditDialog extends AbstractDialog implements QueryConfigurationProvider{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Datastore datastore;
	
	private SchoolClassSubjectsService classSubjectsService;
	
	private int classID;
	
	SingleSelect<Long> teacherSelect;
	SingleSelect<Integer> subjectSelect;
	private PropertyListing propertyListing;
	
	private Button btnAddSubject;
	
	
	public SchoolClassSubjectsEditDialog(PropertyBox classPropertyBox) 
	{
		datastore = Context.get().resource(Datastore.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));
		
		classSubjectsService = Context.get().resource(SchoolClassSubjectsService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));
		
		this.classID = classPropertyBox.getValue(SchoolClass.ID);
		
		teacherSelect = Components.input.singleSelect(User.ID)
				.dataSource(datastore, User.TARGET, User.USER)
				.itemCaptionProperty(User.NAME)
				.withValueChangeListener(e -> veryfiNewSubjectButton())
				.filterByContains(User.NAME)
				.build();
		
		subjectSelect = Components.input.singleSelect(SchoolSubject.ID)
				.dataSource(datastore, SchoolSubject.TARGET, SchoolSubject.SUBJECT)
				.itemCaptionProperty(SchoolSubject.NAME)
				.withValueChangeListener(e -> veryfiNewSubjectButton())
				.filterByContains(SchoolSubject.NAME)
				.build();
		
		btnAddSubject = Components.button("Dodaj", e -> 
		{
			
			PropertyBox newClassSubject =
			PropertyBox.builder(SchoolClassSubject.CLASSSUBJECT)
			.set(SchoolClassSubject.CLASSID, classID) 
			.set(SchoolClassSubject.TEACHERID,teacherSelect.getSelectedItem().get()) 
			.set(SchoolClassSubject.SUBJECTID ,subjectSelect.getSelectedItem().get()).build();
			if(!classSubjectsService.getClassSubjectId(classID, teacherSelect.getSelectedItem().get(), subjectSelect.getSelectedItem().get()).isPresent())
			{
				classSubjectsService.save(newClassSubject);
				propertyListing.refresh();
			}
			else
				Notification.show("Taki zestaw danych ju¿ istnieje", 5000, Position.BOTTOM_CENTER);
			
		});
		
		
		
		propertyListing = PropertyListing.builder(SchoolClassSubject.CLASSSUBJECT).fullSize()
				.dataSource(datastore, SchoolClassSubject.TARGET).withQueryConfigurationProvider(this)
				.withDefaultQuerySort(SchoolClassSubject.ID.asc())
				.hidden(SchoolClassSubject.ID)
				.hidden(SchoolClassSubject.CLASSID)
				.hidden(SchoolClassSubject.TEACHERID)
				.hidden(SchoolClassSubject.SUBJECTID)
				.withThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS)
				.selectionMode(SelectionMode.NONE)
				
				.withComponentColumn(
						item -> Components.button().text("Usuñ")
						.onClick(e -> 
						{
							classSubjectsService.delete(item);
							propertyListing.refresh();
						})
						.withThemeVariants(ButtonVariant.LUMO_ERROR)
						.build()
						).add()
				.build();
		add(Components.vl().fullSize()
			.add(Components.hl().fullWidth()
					.addAndExpand(teacherSelect, 2)
					.spacing()
					.addAndExpand(subjectSelect, 2)
					.spacing()
					.add(btnAddSubject)
					.build())
			.addAndExpand(Components.hl().fullSize()
					.add(propertyListing).build(),1)
			.add(Components.hl().fullWidth()
					.add(Components.button().text("Zamknij").onClick(evt -> close()).build()).build())
		.build());
		
		veryfiNewSubjectButton();
	}

	private void veryfiNewSubjectButton()
	{
		btnAddSubject.setEnabled(
				teacherSelect != null 
				&& teacherSelect.getSelectedItem().isPresent() 
				&& subjectSelect != null 
				&& subjectSelect.getSelectedItem().isPresent());
	}
	
	
	@Override
	public QueryFilter getQueryFilter() 
	{
		return SchoolClassSubject.CLASSID.eq(classID);

	}

}
