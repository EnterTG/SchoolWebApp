package com.WindSkull.SchoolWebApp.services;

import java.util.List;
import java.util.Optional;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;

public interface SchoolTeacherService 
{
	Optional<PropertyBox> getTeacher(Long teacherId);
	List<PropertyBox> getTeacher(Integer classId);
	Optional<PropertyBox> getTeacher(Integer classId,Integer subjectId);
	
	
	
	
	OperationResult save(PropertyBox pbOrderItem);
	OperationResult delete(PropertyBox pbOrderItem);
}
