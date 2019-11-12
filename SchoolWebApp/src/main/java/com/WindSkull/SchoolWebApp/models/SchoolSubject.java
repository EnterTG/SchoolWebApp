package com.WindSkull.SchoolWebApp.models;

import com.holonplatform.core.Validator;
import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.i18n.Localizable;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.Property;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.StringProperty;

public interface SchoolSubject {

	public static final NumericProperty<Integer> ID = NumericProperty.create("id", Integer.class);
	public static final StringProperty NAME = StringProperty.create("name").message("Nazwa przedmiotu")
			.withValidator(Validator.notBlank(Localizable.builder().message("Nazwa przemiotu jest wymagana").build()));
	
	public static final PropertySet<Property<?>> CLASS = PropertySet.builderOf(ID, NAME).identifier(ID)
			.build();
	public static final DataTarget<?> TARGET =  DataTarget.named("subjects");
}
