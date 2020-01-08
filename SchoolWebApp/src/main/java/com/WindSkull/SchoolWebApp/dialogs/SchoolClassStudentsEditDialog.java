package com.WindSkull.SchoolWebApp.dialogs;

import java.util.List;
import java.util.Optional;

import com.WindSkull.SchoolWebApp.models.SchoolClass;
import com.WindSkull.SchoolWebApp.models.SchoolClassStudents;
import com.WindSkull.SchoolWebApp.models.SchoolStudent;
import com.WindSkull.SchoolWebApp.services.SchoolClassStudentsService;
import com.holonplatform.core.Context;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.core.query.QueryConfigurationProvider;
import com.holonplatform.core.query.QueryFilter;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.Input;
import com.holonplatform.vaadin.flow.components.PropertyListing;
import com.holonplatform.vaadin.flow.components.Selectable.SelectionMode;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.value.ValueChangeMode;

public class SchoolClassStudentsEditDialog extends AbstractDialog implements QueryConfigurationProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Datastore datastore;
	
	SchoolClassStudentsService classStudentsService;
	
	private Integer classId;
	private Input<String> searchField;
	private Input<Boolean> searchStudents;
	private PropertyListing propertyListing;
	
	private List<Long> classStudentsList;
	
	public SchoolClassStudentsEditDialog(PropertyBox classPropertyBox) 
	{
		super();
		classId = classPropertyBox.getValue(SchoolClass.ID);
		
		datastore = Context.get().resource(Datastore.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));
		classStudentsService = Context.get().resource(SchoolClassStudentsService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));
		
		classStudentsList = classStudentsService.getClassStudents(classId);
		
		searchField = Components.input.string().placeholder("Szukaj").prefixComponent(new Icon(VaadinIcon.SEARCH))
				.withValueChangeListener(event -> {refreshClassStudentsList();propertyListing.refresh();}).valueChangeMode(ValueChangeMode.EAGER)
				.build();
		
		propertyListing = PropertyListing.builder(SchoolStudent.STUDENT).fullSize()
				.editorBuffered(true) 
				.dataSource(datastore, SchoolStudent.TARGET).withQueryConfigurationProvider(this)
				.withDefaultQuerySort(SchoolStudent.ID.asc())
				.hidden(SchoolStudent.ID)
				.withThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS)
				.selectionMode(SelectionMode.SINGLE)
				.withComponentColumn(
						item -> Components.button().text(classStudentsList.contains(item.getValue(SchoolStudent.ID)) ? "Usuñ" : "Dodaj"  )
						.onClick(e -> 
						{
							e.getSource().setEnabled(false);
							boolean isInClass = classStudentsList.contains(item.getValue(SchoolStudent.ID));

							e.getSource().setText(isInClass ? "Dodaj" :  "Usuñ" );
							e.getSource().addThemeName(isInClass ? ButtonVariant.LUMO_SUCCESS.getVariantName() : ButtonVariant.LUMO_ERROR.getVariantName());
							e.getSource().removeThemeName(isInClass ? ButtonVariant.LUMO_ERROR.getVariantName() : ButtonVariant.LUMO_SUCCESS.getVariantName());
							
							Long studentId = item.getValue(SchoolStudent.ID);
							Optional<Long> classStudentsId = classStudentsService.getClassStudentsId(classId, studentId);
							com.holonplatform.core.property.PropertyBox.Builder
							build = PropertyBox.builder(SchoolClassStudents.CLASSSTUDENTS)
									.set(SchoolClassStudents.CLASSID, classId)
									.set(SchoolClassStudents.STUDENTID, studentId);
							if(isInClass && classStudentsId.isPresent()) build.set(SchoolClassStudents.ID, classStudentsId.get());
							PropertyBox bp = build.build();
							if(isInClass) classStudentsService.delete(bp); else classStudentsService.save(bp);
							e.getSource().setEnabled(true);
						})
						.withThemeVariants(classStudentsList.contains(item.getValue(SchoolStudent.ID)) ? ButtonVariant.LUMO_ERROR :ButtonVariant.LUMO_SUCCESS,ButtonVariant.LUMO_PRIMARY)
						.build()
						).add()
				.build();
		
		
		add(Components.vl().fullSize()
				.add(Components.hl().fullWidth()
						.addAndExpand(searchField,1d)
						.add(searchStudents = Components.input.boolean_().label("Wybrani").onClick(event -> {refreshClassStudentsList();propertyListing.refresh();}).build()).build())
				.addAndExpand(Components.hl().fullSize().minHeight("320px").spacing()
						.addAndExpand(propertyListing,1).build(), 1)
				.add(Components.hl().fullWidth()
						.add(Components.button().text("Zamknij").onClick(evt -> close()).build()).build())
				.build()
			);
		
	}


	private void refreshClassStudentsList()
	{
		classStudentsList = classStudentsService.getClassStudents(classId);
	}

	@Override
	public QueryFilter getQueryFilter() {
		String searchFilter = searchField.getValue();
		QueryFilter qf = null;
		
		boolean search = searchFilter != null && !searchFilter.isEmpty() ;
		if(searchStudents.getValue())
		{
			if (search) 
			{
				qf = (SchoolStudent.NAME.containsIgnoreCase(searchField.getValue() != null ? searchField.getValue() : "")
						.or(SchoolStudent.BOOKID.containsIgnoreCase(searchField.getValue() != null ? searchField.getValue() : "")
						.or(SchoolStudent.SURNAME.containsIgnoreCase(searchField.getValue() != null ? searchField.getValue() : "")))
						.and(SchoolStudent.ID.in(classStudentsService.getClassStudents(classId))));
			}
			else qf = SchoolStudent.ID.in(classStudentsService.getClassStudents(classId));
			
		}
		else if(search) 
		{
			qf = (SchoolStudent.NAME.containsIgnoreCase(searchField.getValue() != null ? searchField.getValue() : "")
					.or(SchoolStudent.BOOKID.containsIgnoreCase(searchField.getValue() != null ? searchField.getValue() : "")
							.or(SchoolStudent.SURNAME.containsIgnoreCase(searchField.getValue() != null ? searchField.getValue() : ""))));
		}
		return qf;
	}

}
