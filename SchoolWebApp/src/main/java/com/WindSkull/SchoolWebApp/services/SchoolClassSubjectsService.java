package com.WindSkull.SchoolWebApp.services;

import java.util.List;
import java.util.Optional;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;

public interface SchoolClassSubjectsService {

	Optional<Long> getClassSubjectId(Integer classID,Long teacherID,Integer subjectID);
	
	List<PropertyBox> getClassSubjects(Integer classID);
	List<PropertyBox> getTeacherSubjects(Long teacherID);
	List<PropertyBox> getSubjectClasses(Integer subjectID);
	
	
	List<PropertyBox> getTeacherSubjects(Integer classID,Long teacherID);
	List<PropertyBox> getSubjectClasses(Integer subjectID,Long teacherID);
	
	OperationResult save(PropertyBox pbOrderItem);
	OperationResult delete(PropertyBox pbOrderItem);
}
