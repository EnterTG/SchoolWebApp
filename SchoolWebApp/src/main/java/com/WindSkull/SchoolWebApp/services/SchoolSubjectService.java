package com.WindSkull.SchoolWebApp.services;

import java.util.List;
import java.util.Optional;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;

public interface SchoolSubjectService 
{
	Optional<PropertyBox> getSubject(Integer id);
	
	Integer getSubjectByName(String subjectName);
	
	List<PropertyBox> getSubjects(String subjectName);
	
	OperationResult save(PropertyBox pbOrderItem);

	OperationResult delete(PropertyBox pbOrderItem);
}
