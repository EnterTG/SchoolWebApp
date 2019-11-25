package com.WindSkull.SchoolWebApp.models;

import com.holonplatform.core.datastore.DataTarget;
import com.holonplatform.core.property.BooleanProperty;
import com.holonplatform.core.property.NumericProperty;
import com.holonplatform.core.property.PropertySet;

public interface SchoolPresenceStudents {

	
	public static final NumericProperty<Long> STUDENTID = NumericProperty.create("studentid", Long.class);
	public static final NumericProperty<Long> PRESENCEID = NumericProperty.create("presenceid", Long.class);
	public static final BooleanProperty PRESENCE = BooleanProperty.create("presence");
	
	public static final PropertySet<?> PRESENCES = PropertySet
			.builderOf(STUDENTID, PRESENCEID,PRESENCE).identifiers(PRESENCEID,STUDENTID).build();
	public static final DataTarget<?> TARGET = DataTarget.named("presencestudents");
	
}
