package com.WindSkull.SchoolWebApp.services;

import java.util.List;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;

public interface SchoolClassStudentsService {

	List<PropertyBox> getClassStudents(Integer id);
	
	
	OperationResult save(PropertyBox pbOrderItem);

	OperationResult delete(PropertyBox pbOrderItem);
}
