package com.WindSkull.SchoolWebApp.pages;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.WindSkull.SchoolWebApp.dialogs.SchoolGradesDialog;
import com.WindSkull.SchoolWebApp.dialogs.SchoolPresenceEditDialog;
import com.WindSkull.SchoolWebApp.enums.UserRole;
import com.WindSkull.SchoolWebApp.models.SchoolClassSubject;
import com.WindSkull.SchoolWebApp.models.User;
import com.WindSkull.SchoolWebApp.root.Menu;
import com.holonplatform.auth.AuthContext;
import com.holonplatform.auth.Authentication;
import com.holonplatform.auth.Authentication.AuthenticationListener;
import com.holonplatform.auth.annotations.Authenticate;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.query.QueryConfigurationProvider;
import com.holonplatform.core.query.QueryFilter;
import com.holonplatform.http.HttpHeaders;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.Input;
import com.holonplatform.vaadin.flow.components.PropertyListing;
import com.holonplatform.vaadin.flow.components.Selectable.SelectionMode;
import com.holonplatform.vaadin.flow.components.builders.PropertyListingBuilder.DatastorePropertyListingBuilder;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
@Authenticate(schemes = HttpHeaders.SCHEME_BASIC, redirectURI = "login")
@Route(value = "", layout = Menu.class)
public class Dashboard extends VerticalLayout implements QueryConfigurationProvider,AuthenticationListener{

	private static final long serialVersionUID = 1L;

	@Autowired
	private Datastore datastore;
	
	@Autowired
	private AuthContext authContext;
	
	@SuppressWarnings("unused")
	private Input<String> searchField;
	
	private PropertyListing propertyListing;
	
	private Long userId;
	
	public Dashboard()
	{
		super();
	}
	
	@PostConstruct
	private void init() {
		
		authContext.addAuthenticationListener(this);
		
		searchField = Components.input.string().placeholder("Szukaj").prefixComponent(new Icon(VaadinIcon.SEARCH))
				.fullWidth().withValueChangeListener(evt -> {
					
				}).valueChangeMode(ValueChangeMode.EAGER).build();
		
		DatastorePropertyListingBuilder listingBuilder  = Components.listing
				.properties(SchoolClassSubject.CLASSSUBJECT)
				.dataSource(datastore,SchoolClassSubject.TARGET)
				.hidden(SchoolClassSubject.CLASSID)
				.hidden(SchoolClassSubject.ID)
				.hidden(SchoolClassSubject.TEACHERID)
				
				.hidden(SchoolClassSubject.SUBJECTID)
				.withThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS)
				.selectionMode(SelectionMode.NONE)
				.withComponentColumn(
						item -> 
						{
							return Components.hl()
									.addAndExpand(
											Components.button().text("Oceny")
											.onClick(e -> 
											{
												SchoolGradesDialog sgd = new SchoolGradesDialog(item.getValue(SchoolClassSubject.CLASSID), item.getValue(SchoolClassSubject.SUBJECTID));sgd.open();
											})
											.withThemeVariants(ButtonVariant.LUMO_PRIMARY)
											.build(), 1)
									.addAndExpand(
											Components.button().text("Obecnoœci")
											.onClick(e -> 
											{
												SchoolPresenceEditDialog ped = new SchoolPresenceEditDialog(item.getValue(SchoolClassSubject.CLASSID), item.getValue(SchoolClassSubject.SUBJECTID));
												ped.open();
											})
											.withThemeVariants(ButtonVariant.LUMO_PRIMARY)
											.build(), 1)
							.build();
						}
				).add()
				.fullSize();
		
		if(!authContext.isPermitted(UserRole.ADMIN.getRole()))
			listingBuilder.hidden(SchoolClassSubject.TEACHER_NAME);
		
		propertyListing = listingBuilder.build();
		add(Components.hl().fullSize().add(propertyListing).build());
		
	}

	
	
	@Override
	public QueryFilter getQueryFilter() 
	{
		if(!authContext.isPermitted(UserRole.ADMIN.getRole()))
			return SchoolClassSubject.TEACHERID.eq(userId);
		return null;
	}

	@Override
	public void onAuthentication(Authentication authentication) {
		if(authentication != null)
		{
			Optional<Long> oUserId = authentication.getParameter(User.USER_DETAIL_ID, Long.class);
			if(oUserId.isPresent())
				userId = oUserId.get();
		}
	}

}
