package com.WindSkull.SchoolWebApp.pages;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.WindSkull.SchoolWebApp.components.ManageableForm;
import com.WindSkull.SchoolWebApp.models.SchoolStudent;
import com.WindSkull.SchoolWebApp.root.Menu;
import com.WindSkull.SchoolWebApp.services.SchoolStudentService;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.core.query.QueryConfigurationProvider;
import com.holonplatform.core.query.QueryFilter;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.Input;
import com.holonplatform.vaadin.flow.components.PropertyInputForm;
import com.holonplatform.vaadin.flow.components.PropertyListing;
import com.holonplatform.vaadin.flow.components.Selectable.SelectionMode;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value = StudentsPage.STUDENTSPAGE_ROUTE, layout = Menu.class)
public class StudentsPage extends VerticalLayout implements ManageableForm,QueryConfigurationProvider{

	public static final String STUDENTSPAGE_ROUTE = "students";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private Datastore datastore;
	@Autowired
	private SchoolStudentService studentsService;

	private Input<String> searchField;
	private PropertyListing propertyListing;
	private PropertyInputForm form;
	

	
	private Button btnInsertUpdate;
	private Button btnDelete;
	private Button btnDiscard;

	private boolean editMode;
	
	
	@PostConstruct
	public void init() {

		searchField = Components.input.string().placeholder("Szukaj").prefixComponent(new Icon(VaadinIcon.SEARCH))
				.withValueChangeListener(event -> propertyListing.refresh()).valueChangeMode(ValueChangeMode.EAGER)
				.build();

		form = Components.input.form(SchoolStudent.STUDENT).hidden(SchoolStudent.ID).build();
				
		
		form.setEnabled(false);

		Components.configure(this).spacing().withoutMargin()
				// horizontal toolbar
				.add(Components.hl().fullWidth().spacing()
						// search field
						.addAndExpand(searchField.getComponent(), 1d).add(searchField.getComponent()) 
						// btn new
						.add(Components.button().text("Nowy").icon(VaadinIcon.PLUS)
								.withThemeVariants(ButtonVariant.LUMO_PRIMARY).onClick(event -> {
									clearFields();
									enableForm(true);
									btnInsertUpdate.setText("Dodaj");
									// fieldEmail.focus();
									propertyListing.deselectAll();
									editMode = false;
								}).build())
						.build())
				// horizontal split panel
				.add(Components.hl().fullSize().spacing()
						// user listing
						.addAndExpand(
								propertyListing = Components.listing.properties(SchoolStudent.STUDENT).fullSize()
								.resizable(true).dataSource(datastore, SchoolStudent.TARGET).withQueryConfigurationProvider(this)
								.withDefaultQuerySort(SchoolStudent.ID.asc())
								.visibleColumns(SchoolStudent.NAME,SchoolStudent.SURNAME,SchoolStudent.BOOKID)
								.withThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS)
								.selectionMode(SelectionMode.SINGLE).withItemClickListener(evt -> {
									enableForm(true);
									form.setValue(evt.getItem());
									btnInsertUpdate.setText("Zaktualizuj");
									editMode = true;
								}).build(), 1d)
						//form for input/edit data
						.add(Components.vl().sizeUndefined().fullHeight().withoutPadding().add(form)
								//separator
								.addAndExpand(new Div(), 1d)
								//3 buttons for update, discard, delete 
								.add(Components.hl().fullWidth().spacing().addAndExpand(
										btnInsertUpdate = Components.button().text("Zaktualizuj")
												.withThemeVariants(ButtonVariant.LUMO_SUCCESS,
														ButtonVariant.LUMO_PRIMARY)
												.onClick(event -> save()).build(),
										1d)
										.addAndExpand(
												btnDiscard = Components
														.button().text("Odrzu�").onClick(event -> discard()).build(),
												1d)
										.addAndExpand(btnDelete = Components.button().text("Usu�")
												.withThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY)
												.onClick(event -> {
													delete();
												}).build(), 1d)
										.build())
								.build())
						.build());
		enableForm(false);
	}

	@Override
	public void enableForm(boolean enable) {
		form.setEnabled(enable);
		btnDelete.setEnabled(enable);
		btnDiscard.setEnabled(enable);
		btnInsertUpdate.setEnabled(enable);

	}

	@Override
	public void clearFields() {
		form.clear();

	}

	@Override
	public void save() {

		PropertyBox pbUser = form.getValue();

		// check already used email
		String studentName = pbUser.getValue(SchoolStudent.NAME);
		String studentSurname = pbUser.getValue(SchoolStudent.SURNAME);
		String studentBookId = pbUser.getValue(SchoolStudent.BOOKID);
		
		if (!editMode) 
		{
			if (!studentsService.getStudent(studentName, studentSurname, studentBookId).isPresent()) {
				saveClass(pbUser);
				clearFields();
			} else {
				Notification.show("Student o podanych parametrach: jest juz w uzyciu", 2000, Position.BOTTOM_CENTER);
			}
		} else {
			saveClass(pbUser);
			
		}
		
	}

	private void saveClass(PropertyBox pbUser) {

		OperationResult operationResult = studentsService.save(pbUser);
		if (operationResult.getAffectedCount() > 0) {
			propertyListing.refresh();
			propertyListing.select(pbUser);
		}
	}

	@Override
	public void delete() {
		propertyListing.getFirstSelectedItem().ifPresent(propertyBox -> {
			OperationResult op = studentsService.delete(propertyBox);
			if (op.getAffectedCount() > 0) {
				propertyListing.refresh();
				clearFields();
			}
		});
	}


	
	@Override
	public void discard() {
		propertyListing.getFirstSelectedItem().ifPresent(propertyBox -> {
			form.setValue(propertyBox);
		});
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

