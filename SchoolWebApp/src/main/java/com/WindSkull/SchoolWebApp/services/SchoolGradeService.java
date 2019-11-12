package com.WindSkull.SchoolWebApp.services;

import java.util.List;
import java.util.Optional;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;

public interface SchoolGradeService {

	Optional<PropertyBox> getGrade(Long id);

	List<PropertyBox> getGrades(Long studentId);
	List<PropertyBox> getGrades(Long studentId,Integer classId);
	List<PropertyBox> getGrades(Long studentId,Integer classId,Integer subjectId);
	List<PropertyBox> getGradesClass(Integer classId);
	List<PropertyBox> getGradesClass(Integer classId,Integer subjectId);
	OperationResult save(PropertyBox pbOrderItem);
	OperationResult delete(PropertyBox pbOrderItem);
}
