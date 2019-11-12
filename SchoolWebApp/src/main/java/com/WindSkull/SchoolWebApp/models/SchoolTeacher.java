package com.WindSkull.SchoolWebApp.models;

import com.WindSkull.SchoolWebApp.models.entities.SchoolTeacherEntity;
import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.Property;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.datastore.jpa.JpaTarget;

public interface SchoolTeacher 
{
	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);
	
	public static final NumericProperty<Integer> USERID = NumericProperty.create("userid", Integer.class);
	public static final NumericProperty<Integer> CLASSID = NumericProperty.create("classid", Integer.class);
	public static final NumericProperty<Integer> SUBJECTID = NumericProperty.create("subjectid", Integer.class);
	
	public static final PropertySet<Property<?>> TEACHER = PropertySet.builderOf(ID,USERID, CLASSID,SUBJECTID)
			.identifier(ID)
			.build();
	public static final DataTarget<?> TARGET =  JpaTarget.of(SchoolTeacherEntity.class);
}
