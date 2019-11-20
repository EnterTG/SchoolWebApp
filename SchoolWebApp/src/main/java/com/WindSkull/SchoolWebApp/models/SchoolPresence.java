package com.WindSkull.SchoolWebApp.models;

import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.property.BooleanProperty;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.PropertySet;

public interface SchoolPresence 
{
	public static final NumericProperty<Long> ID = NumericProperty.create("id", Long.class);	
	public static final NumericProperty<Long> STUDENTID = NumericProperty.create("studentid", Long.class);
	public static final NumericProperty<Integer> CLASSID = NumericProperty.create("classid", Integer.class);
	public static final NumericProperty<Integer> SUBJECTID = NumericProperty.create("subjectid", Integer.class);
	public static final BooleanProperty PRESENCE = BooleanProperty.create("presence");
	
	public static final PropertySet<?> PRESENCES = PropertySet
			.builderOf(ID,STUDENTID, CLASSID,SUBJECTID,PRESENCE).identifier(ID).build();
	public static final DataTarget<?> TARGET = DataTarget.named("presence");
}
