package com.WindSkull.SchoolWebApp.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;

public interface SchoolClassService {

	
	Optional<String> getClassName(Integer classId);
	
	Optional<PropertyBox> getClass(Integer classId);
	List<PropertyBox> getClass(String className,LocalDate day,Integer semester);
	List<PropertyBox> getClassByName(String className);
	List<PropertyBox> getClassByYear(LocalDate day);
	List<PropertyBox> getClassByYear(LocalDate from, LocalDate to);
	List<PropertyBox> getClassBySemester(Integer semester);
	List<PropertyBox> getAllClasses();
	
	OperationResult save(PropertyBox pbOrderItem);
	OperationResult delete(PropertyBox pbOrderItem);
}
