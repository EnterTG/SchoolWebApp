package com.WindSkull.SchoolWebApp.models;

import com.WindSkull.SchoolWebApp.models.entities.SchoolStudentEntity;
import com.holonplatform.core.Validator;
import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.i18n.Localizable;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.Property;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.StringProperty;
import com.holonplatform.datastore.jpa.JpaTarget;

public interface SchoolStudent {

	
	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);
	public static final StringProperty NAME = StringProperty.create("name").message("Imię")
			.withValidator(Validator.notBlank(Localizable.builder().message("Imie jest wymagane").build()));
	public static final StringProperty SURNAME = StringProperty.create("surname").message("Nazwisko")
			.withValidator(Validator.notNull(Localizable.builder().message("Nazwisko jest wymagane").build()));
	public static final StringProperty BOOKID = StringProperty.create("bookid").message("Nr księgi")
			.withValidator(Validator.notNull(Localizable.builder().message("Nr księgi jest wymagany").build()));
	
	public static final PropertySet<Property<?>> STUDENT = PropertySet.builderOf(ID, NAME, SURNAME,BOOKID).identifier(ID)
			.build();
	public static final DataTarget<?> TARGET = JpaTarget.of(SchoolStudentEntity.class);

	
}
