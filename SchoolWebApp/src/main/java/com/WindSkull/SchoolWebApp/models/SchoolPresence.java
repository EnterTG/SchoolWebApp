package com.WindSkull.SchoolWebApp.models;

import java.time.LocalDateTime;

import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.PropertySet;
import com.holonplatform.core.property.TemporalProperty;

public interface SchoolPresence 
{
	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);	
	
	public static final NumericProperty<Integer> CLASSID = NumericProperty.create("classid", Integer.class);
	public static final NumericProperty<Integer> SUBJECTID = NumericProperty.create("subjectid", Integer.class);
	public static final TemporalProperty<LocalDateTime> DATE = TemporalProperty.create("date",LocalDateTime.class);

	
	public static final PropertySet<?> PRESENCES = PropertySet
			.builderOf(ID,CLASSID,SUBJECTID,DATE).identifier(ID).build();
	public static final DataTarget<?> TARGET = DataTarget.named("presence");
}
