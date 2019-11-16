package com.WindSkull.SchoolWebApp.services;

import java.util.List;
import java.util.Optional;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;

public interface SchoolClassStudentsService {

	//List<PropertyBox> getClassStudents(Integer id);
	List<Long> getClassStudents(Integer classid);
	
	Optional<Long> getClassStudentsId(Integer classId,Long studentId);
	
	OperationResult save(PropertyBox pbOrderItem);

	OperationResult delete(PropertyBox pbOrderItem);
}
