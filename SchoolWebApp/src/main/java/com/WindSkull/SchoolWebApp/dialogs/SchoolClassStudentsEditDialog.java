package com.WindSkull.SchoolWebApp.dialogs;

import com.WindSkull.SchoolWebApp.models.SchoolClass;
import com.WindSkull.SchoolWebApp.models.SchoolStudent;
import com.holonplatform.core.Context;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.core.query.QueryConfigurationProvider;
import com.holonplatform.core.query.QueryFilter;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.Input;
import com.holonplatform.vaadin.flow.components.PropertyListing;
import com.holonplatform.vaadin.flow.components.Selectable.SelectionMode;
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
	
	private Integer classId;
	private Input<String> searchField;
	
	private PropertyListing propertyListing;
	
	
	public SchoolClassStudentsEditDialog(PropertyBox classPropertyBox) 
	{
		super();
		classId = classPropertyBox.getValue(SchoolClass.ID);
		datastore = Context.get().resource(Datastore.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));
		searchField = Components.input.string().placeholder("Szukaj").prefixComponent(new Icon(VaadinIcon.SEARCH))
				.withValueChangeListener(event -> propertyListing.refresh()).valueChangeMode(ValueChangeMode.EAGER)
				.build();
		
		propertyListing = PropertyListing.builder(SchoolStudent.STUDENT)
				.editorBuffered(true) 
				.dataSource(datastore, SchoolStudent.TARGET).withQueryConfigurationProvider(this)
				.withDefaultQuerySort(SchoolStudent.ID.asc())
				.hidden(SchoolStudent.ID)
				.withThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS)
				.selectionMode(SelectionMode.SINGLE)
				.withComponentColumn(item -> Components.button("Edit", e -> {;})).add()
				.build();
		
		add(Components.hl().fullWidth().addAndExpand(searchField,1d).build());
		add(Components.hl().fullSize().minHeight("320px").spacing().addAndExpand(propertyListing.getComponent(),1).build());
		add(Components.hl().fullWidth().add(Components.button().text("Cancel").onClick(evt -> close()).build()).build());
		
	}


	@Override
	public QueryFilter getQueryFilter() {
		String searchFilter = searchField.getValue();
		if (searchFilter != null && !searchFilter.isEmpty()) {
			return SchoolStudent.NAME.containsIgnoreCase(searchField.getValue() != null ? searchField.getValue() : "")
					.or(SchoolStudent.SURNAME.containsIgnoreCase(searchField.getValue() != null ? searchField.getValue() : "")
							.or(SchoolStudent.BOOKID.containsIgnoreCase(searchField.getValue() != null ? searchField.getValue() : "")));
		}
		return null;
	}

}
