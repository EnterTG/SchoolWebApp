package com.WindSkull.SchoolWebApp.root;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.WindSkull.SchoolWebApp.enums.UserRole;
import com.WindSkull.SchoolWebApp.pages.ClassPage;
import com.WindSkull.SchoolWebApp.pages.StudentsPage;
import com.WindSkull.SchoolWebApp.pages.SubjectPage;
import com.WindSkull.SchoolWebApp.pages.UsersPage;
import com.holonplatform.auth.AuthContext;
import com.holonplatform.auth.Authentication;
import com.holonplatform.auth.Authentication.AuthenticationListener;
import com.holonplatform.auth.annotations.Authenticate;
import com.holonplatform.http.HttpHeaders;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.navigator.Navigator;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;

@Authenticate(schemes = HttpHeaders.SCHEME_BASIC, redirectURI = "login")
@ParentLayout(MainLayout.class)
@Component
@UIScope
public class Menu extends HorizontalLayout implements RouterLayout, AuthenticationListener {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AuthContext authContext;

	private Button btnDashboard;

	
	/**
	 * Admin buttons
	 */
	private Button btnUsers;
	private Button btnClass;
	private Button btnSubject;
	private Button btnStudents;
	
	
	

	public Menu() {
		super();
		setSizeFull();
		setSpacing(false);
		getStyle().set("overflow", "hidden");
	}

	@PostConstruct
	private void init() {

		authContext.addAuthenticationListener(this);

		Image holonLogo = new Image("frontend/images/holon-logo.png", "Holon Logo");
		holonLogo.setWidth("80%");

		Label lblArtisan = new Label();
		lblArtisan.getElement().setProperty("innerHTML", "Szko³a");
		lblArtisan.getStyle().set("font-size", "xx-large");

		VerticalLayout vl = Components.vl().width("280px").height("100%").styleName("menu")
				.add(Components.vl().withoutPadding().add(lblArtisan,
						btnDashboard = Components.button()
										.text("Strona g³ówna").withThemeVariants(ButtonVariant.LUMO_LARGE).width("100%").onClick(evt -> {
											Navigator.get().navigateTo("");
											resetStyles();
											btnDashboard.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
										}).build(), 
						btnUsers = Components.button().text("U¿ytkownicy").withThemeVariants(ButtonVariant.LUMO_LARGE)
								.width("100%").onClick(evt -> {
									Navigator.get().navigateTo(UsersPage.USERSPAGE_ROUTE);
									resetStyles();
									btnUsers.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
								}).build(),
						btnClass = Components.button().text("Klasy").withThemeVariants(ButtonVariant.LUMO_LARGE)
								.width("100%").onClick(evt -> {
									Navigator.get().navigateTo(ClassPage.CLASSPAGE_ROUTE);
									resetStyles();
									btnClass.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
								}).build(),
						btnSubject = Components.button().text("Przedmioty").withThemeVariants(ButtonVariant.LUMO_LARGE)
								.width("100%").onClick(evt -> {
									Navigator.get().navigateTo(SubjectPage.SUBJECTPAGE_ROUTE);
									resetStyles();
									btnSubject.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
								}).build(),
						btnStudents = Components.button().text("Studenci").withThemeVariants(ButtonVariant.LUMO_LARGE)
								.width("100%").onClick(evt -> {
									Navigator.get().navigateTo(StudentsPage.STUDENTSPAGE_ROUTE);
									resetStyles();
									btnStudents.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
								}).build(),		
						Components.button().text("Logout").withThemeVariants(ButtonVariant.LUMO_LARGE).width("100%")
								.onClick(evt -> {
									logout();
								}).build())
						.build())
				.add(Components.vl().withoutPadding().add(holonLogo).align(holonLogo, Alignment.CENTER).build())
				.justifyContentMode(JustifyContentMode.BETWEEN).align(lblArtisan, Alignment.CENTER).build();

		add(vl);
		btnDashboard.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
	}

	private void logout() {
		authContext.unauthenticate();
		VaadinSession.getCurrent().getSession().invalidate();
		UI.getCurrent().getPage().reload();
	}

	public void resetStyles() {
		btnDashboard.removeThemeVariants(ButtonVariant.LUMO_PRIMARY);
		btnUsers.removeThemeVariants(ButtonVariant.LUMO_PRIMARY);
		btnClass.removeThemeVariants(ButtonVariant.LUMO_PRIMARY);
		btnSubject.removeThemeVariants(ButtonVariant.LUMO_PRIMARY);
		btnStudents.removeThemeVariants(ButtonVariant.LUMO_PRIMARY);
	}

	@Override
	public void onAuthentication(Authentication authentication) {
		if (authentication != null) {
			btnDashboard.setVisible(authContext.isPermittedAny(UserRole.ADMIN.getRole(), UserRole.TEACHER.getRole()));
			btnUsers.setVisible(authContext.isPermitted(UserRole.ADMIN.getRole()));
			btnClass.setVisible(authContext.isPermitted(UserRole.ADMIN.getRole()));
			btnSubject.setVisible(authContext.isPermitted(UserRole.ADMIN.getRole()));
			btnStudents.setVisible(authContext.isPermitted(UserRole.ADMIN.getRole()));
		}
	}

}
