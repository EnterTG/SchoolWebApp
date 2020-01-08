package com.WindSkull.SchoolWebApp.models;

import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.Property;
import com.holonplatform.core.property.PropertySet;

public interface SchoolTeacher 
{
	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);
	
	public static final NumericProperty<Long> USERID = NumericProperty.create("userid", Long.class);
	public static final NumericProperty<Integer> CLASSID = NumericProperty.create("classid", Integer.class);
	public static final NumericProperty<Integer> SUBJECTID = NumericProperty.create("subjectid", Integer.class);
	
	public static final PropertySet<Property<?>> TEACHER = PropertySet.builderOf(ID,USERID, CLASSID,SUBJECTID)
			.identifier(ID)
			.build();
	public static final DataTarget<?> TARGET =  DataTarget.named("teachers");
}
