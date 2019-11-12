package com.WindSkull.SchoolWebApp.pages;

import com.WindSkull.SchoolWebApp.root.Menu;
import com.holonplatform.vaadin.flow.components.Input;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = Menu.class)
public class Dashboard extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private Input<String> searchField;

	public Dashboard() {
		super();
		/*StorefrontListing storefrontListing = new StorefrontListing();
		storefrontListing.setFilterSupplier(() -> buildFilter());

		searchField = Components.input.string().placeholder("Search").prefixComponent(new Icon(VaadinIcon.SEARCH))
				.fullWidth().withValueChangeListener(evt -> {
					storefrontListing.setFilterSupplier(() -> buildFilter());
					storefrontListing.refresh();
				}).valueChangeMode(ValueChangeMode.EAGER).build();

		Components.configure(this).fullWidth().spacing().withoutMargin()
				// horizontal toolbar
				.add(Components.hl().fullWidth().spacing()
						// search field
						.addAndExpand(searchField.getComponent(), 1d)
						// btn new
						.add(Components.button().text("New order").styleName("storefront-btn").icon(VaadinIcon.PLUS)
								.withThemeVariants(ButtonVariant.LUMO_PRIMARY).onClick(event -> {
									OrderManageDialog omd = new OrderManageDialog(null,
											storefrontListing.getPropertyListing());
									omd.open();
								}).build())
						.build())
				.add(storefrontListing).flexGrow(1, storefrontListing);*/
	}

	/*private QueryFilter buildFilter() {
		QueryFilter filter = Order.DUE_DATE.goe(LocalDate.now());

		// customer filter
		String customerName = searchField.getValue();
		if (customerName != null) {
			QueryFilter customerFilter = SubQuery.create().target(Customer.TARGET).filter(
					// NB: used to set order alias for Order.customer field!
					Order.TARGET.property(Order.CUSTOMER).eq(Customer.ID)
							.and(Customer.FULLNAME.containsIgnoreCase(customerName)))
					.exists();
			filter = filter.and(customerFilter);
		}
		return filter;
	}*/
}
