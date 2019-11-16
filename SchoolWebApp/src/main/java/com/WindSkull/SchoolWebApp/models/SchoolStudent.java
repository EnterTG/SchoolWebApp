package com.WindSkull.SchoolWebApp.models;

import com.holonplatform.core.Validator;
import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.i18n.Localizable;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.Property;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.StringProperty;

public interface SchoolStudent {

	public static final String BOOKID_MESSAGE = "Nr ksi�gi";
	public static final String SURNAME_MESSAGE = "Nazwisko";
	public static final String NAME_MESSAGE = "Imi�";
	
	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);
	public static final StringProperty NAME = StringProperty.create("name").message(NAME_MESSAGE)
			.withValidator(Validator.notBlank(Localizable.builder().message("Imi� jest wymagane").build()));
	public static final StringProperty SURNAME = StringProperty.create("surname").message(SURNAME_MESSAGE)
			.withValidator(Validator.notNull(Localizable.builder().message("Nazwisko jest wymagane").build()));
	public static final StringProperty BOOKID = StringProperty.create("bookid").message(BOOKID_MESSAGE)
			.withValidator(Validator.notNull(Localizable.builder().message("Nr ksi�gi jest wymagany").build()));
	
	public static final PropertySet<Property<?>> STUDENT = PropertySet.builderOf(ID, NAME, SURNAME,BOOKID).identifier(ID)
			.build();
	public static final DataTarget<?> TARGET = DataTarget.named("students");

	
}
