package com.WindSkull.SchoolWebApp.models;

import com.holonplatform.core.Context;
import com.holonplatform.core.Validator;
import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.i18n.Localizable;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.VirtualProperty;

public interface SchoolClassSubject {
	
	public static final String SUBJECT_MESSAGE = "Przedmiot";
	public static final String TEACHER_MESSAGE = "Nauczyciel";

	
	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);	
	public static final NumericProperty<Integer> SUBJECTID = NumericProperty.create("subjectid", Integer.class)
			.withValidator(Validator.notNull(Localizable.builder().message("Subject id is required").build()));

	public static final NumericProperty<Integer> CLASSID = NumericProperty.create("classid", Integer.class)
			.withValidator(Validator.notNull(Localizable.builder().message("Class id is required").build()));
	public static final NumericProperty<Long> TEACHERID = NumericProperty.create("teacherid", Long.class)
			.withValidator(Validator.notNull(Localizable.builder().message("Teacher id is required").build()));
	
	public static final VirtualProperty<String> SUBJECT_NAME = VirtualProperty.create(String.class, propertyBox -> {
		Datastore ds = Context.get().resource(Datastore.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));
		return ds.query(SchoolSubject.TARGET).filter(SchoolSubject.ID.eq(propertyBox.getValue(SUBJECTID)))
				.findOne(SchoolSubject.NAME).orElse("NAME error");
	}).message(SUBJECT_MESSAGE);
	
	public static final VirtualProperty<String> TEACHER_NAME = VirtualProperty.create(String.class, propertyBox -> {
			Datastore ds = Context.get().resource(Datastore.class)
					.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));
			return ds.query(User.TARGET).filter(User.ID.eq(propertyBox.getValue(TEACHERID)))
					.findOne(User.NAME).orElse("NAME error");
	}).message(TEACHER_MESSAGE);
	public static final PropertySet<?> CLASSSUBJECT = PropertySet
			.builderOf(ID, SUBJECTID, CLASSID,TEACHERID,SUBJECT_NAME,TEACHER_NAME).identifier(ID).build();

	
	
	public static final DataTarget<?> TARGET = DataTarget.named("classsubjects");
}
