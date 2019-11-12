package com.WindSkull.SchoolWebApp.models;

import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.PropertySet;

public interface SchoolGrade {

	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);	
	public static final NumericProperty<Long> STUDENTID = NumericProperty.create("studentid", Long.class);
	public static final NumericProperty<Integer> CLASSID = NumericProperty.create("classid", Integer.class);
	public static final NumericProperty<Integer> SUBJECTID = NumericProperty.create("subjectid", Integer.class);
	public static final NumericProperty<Short> GRADE = NumericProperty.create("grade", Short.class);
	
	public static final PropertySet<?> GRADES = PropertySet
			.builderOf(ID,STUDENTID, CLASSID,SUBJECTID,GRADE).identifier(ID).build();
	public static final DataTarget<?> TARGET = DataTarget.named("grades");
}
