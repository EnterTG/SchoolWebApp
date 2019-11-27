package com.WindSkull.SchoolWebApp.models;

import com.holonplatform.core.Context;
import com.holonplatform.core.Validator;
import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.i18n.Localizable;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.VirtualProperty;


public interface SchoolClassStudents 
{
	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);	
	public static final NumericProperty<Long> STUDENTID = NumericProperty.create("studentid", Long.class)
			.withValidator(Validator.notNull(Localizable.builder().message("Student id is required").build()));

	public static final NumericProperty<Integer> CLASSID = NumericProperty.create("classid", Integer.class)
			.withValidator(Validator.notNull(Localizable.builder().message("Class id is required").build()));

	
	public static final VirtualProperty<String> STUDENT_NAME = VirtualProperty.create(String.class, propertyBox -> {
		Datastore ds = Context.get().resource(Datastore.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));
		return ds.query(SchoolStudent.TARGET).filter(SchoolStudent.ID.eq(propertyBox.getValue(SchoolClassStudents.STUDENTID)))
				.findOne(SchoolStudent.NAME).orElse("NAME error");
	}).message(SchoolStudent.NAME_MESSAGE);
	public static final VirtualProperty<String> STUDENT_SURNAME = VirtualProperty.create(String.class, propertyBox -> {
		Datastore ds = Context.get().resource(Datastore.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve Datastore from Context."));
		return ds.query(SchoolStudent.TARGET).filter(SchoolStudent.ID.eq(propertyBox.getValue(SchoolClassStudents.STUDENTID)))
				.findOne(SchoolStudent.SURNAME).orElse("SURNAME error");
	}).message(SchoolStudent.SURNAME_MESSAGE);

	public static final PropertySet<?> CLASSSTUDENTS = PropertySet
			.builderOf( STUDENTID, CLASSID,STUDENT_NAME,STUDENT_SURNAME,ID/*,STUDENT_INDEX */).identifier(ID).build();

	
	public static final DataTarget<?> TARGET = DataTarget.named("classstudents");
	
}
