package com.WindSkull.SchoolWebApp.services;

import java.util.List;
import java.util.Optional;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;

public interface SchoolStudentService 
{
	Optional<PropertyBox> getStudent(Long id);
	
	List<PropertyBox> getStudentsByName(String studentName);
	List<PropertyBox> getStudentsBySurname(String studentSurname);
	List<PropertyBox> getStudentsByNameSurname(String studentName,String studentSurname);
	Optional<PropertyBox> getStudent(String studentName,String studentSurname,String index);
	
	Optional<PropertyBox> getStudentByIndex(String index);
	
	OperationResult save(PropertyBox pbOrderItem);
	OperationResult delete(PropertyBox pbOrderItem);
}
