package com.WindSkull.SchoolWebApp.models;

import java.time.LocalDate;

import com.WindSkull.SchoolWebApp.models.entities.SchoolClassEntity;
import com.holonplatform.core.Validator;
import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.i18n.Localizable;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.Property;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.StringProperty;
import com.holonplatform.core.property.TemporalProperty;
import com.holonplatform.datastore.jpa.JpaTarget;

public interface SchoolClass 
{
	
	public static final NumericProperty<Integer> ID = NumericProperty.create("id", Integer.class);
	public static final StringProperty NAME = StringProperty.create("name").message("Nazwa")
			.withValidator(Validator.notBlank(Localizable.builder().message("Nazwa klasy jest wymagana").build()));
	public static final NumericProperty<Integer> SEMESTER = NumericProperty.create("semester", Integer.class).message("Semestr")
			.withValidator(Validator.notNull(Localizable.builder().message("Semestr jest wymagany").build()));
	public static final StringProperty TYPE = StringProperty.create("type").message("Typ")
			.withValidator(Validator.notBlank(Localizable.builder().message("Typ klasy jest wymagany").build()));
	public static final TemporalProperty<LocalDate> CREATEYEAR = TemporalProperty.create("createyear", LocalDate.class).message("Rok utorzenia")
			.withValidator(Validator.notNull(Localizable.builder().message("Rok utworzenia jest wymagany").build()));;


	public static final PropertySet<Property<?>> CLASS = PropertySet.builderOf(ID, NAME, CREATEYEAR,SEMESTER).identifier(ID)
			.build();
	public static final DataTarget<?> TARGET = JpaTarget.of(SchoolClassEntity.class);
}
