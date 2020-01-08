package com.WindSkull.SchoolWebApp.pages;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.WindSkull.SchoolWebApp.components.ManageableForm;
import com.WindSkull.SchoolWebApp.dialogs.SchoolClassStudentsEditDialog;
import com.WindSkull.SchoolWebApp.dialogs.SchoolClassSubjectsEditDialog;
import com.WindSkull.SchoolWebApp.models.SchoolClass;
import com.WindSkull.SchoolWebApp.models.SchoolClassType;
import com.WindSkull.SchoolWebApp.root.Menu;
import com.WindSkull.SchoolWebApp.services.SchoolClassService;
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
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
@Route(value = ClassPage.CLASSPAGE_ROUTE, layout = Menu.class)
public class ClassPage extends VerticalLayout implements ManageableForm,QueryConfigurationProvider{

	public static final String CLASSPAGE_ROUTE = "classes";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private Datastore datastore;
	@Autowired
	private SchoolClassService classService;

	private Input<String> searchField;
	private PropertyListing propertyListing;
	private PropertyInputForm form;
	
	private Input<Integer> semesterField;
	private NumberField numberField;
	
	private Button btnInsertUpdate;
	private Button btnDelete;
	private Button btnDiscard;
	private Button btnEditStudents;
	private Button btnEditSubjects;
	private boolean editMode;
	
	
	@PostConstruct
	public void init() {

		searchField = Components.input.string().placeholder("Szukaj").prefixComponent(new Icon(VaadinIcon.SEARCH))
				.withValueChangeListener(event -> propertyListing.refresh()).valueChangeMode(ValueChangeMode.EAGER)
				.build();
		//Create input for semestr
		numberField = new NumberField();
		numberField.setValue((double)1);
		
		numberField.setHasControls(true);
		numberField.setMin(1);
		numberField.setMax(7);
		numberField.setLabel("Semestr");
		
		//Component for data binding
		semesterField = Components.input.number(Integer.class).minDecimals(0).maxDecimals(7).build();
		semesterField.setVisible(false);
		semesterField.setValue(1);
		
		
		
		
		//Data binding between vaadini and holo
		numberField.addValueChangeListener(v -> semesterField.setValue(v.getValue().intValue()));
		
		form = Components.input.form(SchoolClass.CLASS).hidden(SchoolClass.ID)
				.bind(SchoolClass.TYPE,
						Components.input.singleSelect(SchoolClassType.ID).dataSource(datastore, SchoolClassType.TARGET, SchoolClassType.TYPE)
								.sizeUndefined().itemCaptionProperty(SchoolClassType.DESCRIPTION).build())
				.bind(SchoolClass.SEMESTER,semesterField).build();
		
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
									btnEditStudents.setEnabled(false);
									btnEditSubjects.setEnabled(false);
									// fieldEmail.focus();
									propertyListing.deselectAll();
									editMode = false;
								}).build())
						.build())
				// horizontal split panel
				.add(Components.hl().fullSize().spacing()
						// user listing
						.addAndExpand(
								propertyListing = Components.listing.properties(SchoolClass.CLASS).fullSize()
								.resizable(true).dataSource(datastore, SchoolClass.TARGET).withQueryConfigurationProvider(this)
								.withDefaultQuerySort(SchoolClass.ID.asc())
								.visibleColumns(SchoolClass.NAME,SchoolClass.SEMESTER,SchoolClass.CREATEYEAR,SchoolClass.TYPE)
								.withThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS)
								.selectionMode(SelectionMode.SINGLE).withItemClickListener(evt -> {
									enableForm(true);
									form.setValue(evt.getItem());
									btnInsertUpdate.setText("Zaktualizuj");
									btnEditStudents.setEnabled(true);
									btnEditSubjects.setEnabled(true);
									editMode = true;
								}).build(), 1d)

						.add(Components.vl().sizeUndefined().fullHeight().withoutPadding().add(form)
								.add(numberField)
								.addAndExpand(new Div(), 1d)
								.add(Components.hl().fullWidth().spacing()
								.addAndExpand(btnEditStudents = Components.button().text("Edytuj uczniów").onClick(e -> {
									SchoolClassStudentsEditDialog omd = new SchoolClassStudentsEditDialog(form.getValue());
									omd.open();
								}).build(),1d)
								.build())
								.add(Components.hl().fullWidth().spacing()
										.addAndExpand(btnEditSubjects = Components.button().text("Edytuj przedmioty").onClick(e -> {
											SchoolClassSubjectsEditDialog omd = new SchoolClassSubjectsEditDialog(form.getValue());
											omd.open();
										}).build(),1d)
										.build())
								//3 buttons for update, discard, delete 
								.add(Components.hl().fullWidth().spacing().addAndExpand(
										btnInsertUpdate = Components.button().text("Zaktualizuj")
												.withThemeVariants(ButtonVariant.LUMO_SUCCESS,
														ButtonVariant.LUMO_PRIMARY)
												.onClick(event -> save()).build(),
										1d)
										.addAndExpand(
												btnDiscard = Components
														.button().text("Odrzuæ").onClick(event -> discard()).build(),
												1d)
										.addAndExpand(btnDelete = Components.button().text("Usuñ")
												.withThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY)
												.onClick(event -> {
													delete();
												}).build(), 1d)
										.build())
								.build())
						.build());
		btnEditStudents.setEnabled(false);
		btnEditSubjects.setEnabled(false);
		enableForm(false);
	}

	@Override
	public void enableForm(boolean enable) {
		form.setEnabled(enable);
		btnDelete.setEnabled(enable);
		btnDiscard.setEnabled(enable);
		btnInsertUpdate.setEnabled(enable);
		numberField.setEnabled(enable);
	}

	@Override
	public void clearFields() {
		form.clear();
		semesterField.setValue(1);
		numberField.setValue((double)1);
	}

	@Override
	public void save() {

		PropertyBox pbUser = form.getValue();

		// check already used email
		String className = pbUser.getValue(SchoolClass.NAME);
		Integer classYear = pbUser.getValue(SchoolClass.CREATEYEAR);
		Integer classSemester = pbUser.getValue(SchoolClass.SEMESTER);
		
		if (!editMode) 
		{
			if (classService.getClass(className, LocalDate.of(classYear, 1, 1), classSemester).size() == 0) {
				saveClass(pbUser);
				//clearFields();
			} else {
				Notification.show("Klasa o podanych parametrach: jest juz w uzyciu", 2000, Position.BOTTOM_CENTER);
			}
		} else {
			saveClass(pbUser);
			
		}
		
	}

	private void saveClass(PropertyBox pbUser) {

		OperationResult operationResult = classService.save(pbUser);
		if (operationResult.getAffectedCount() > 0) {
			propertyListing.refresh();
			propertyListing.select(pbUser);
		}
	}

	@Override
	public void delete() {
		propertyListing.getFirstSelectedItem().ifPresent(propertyBox -> {
			OperationResult op = classService.delete(propertyBox);
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
			return SchoolClass.NAME.containsIgnoreCase(searchField.getValue() != null ? searchField.getValue() : "");
		}
		return null;
	}
}
