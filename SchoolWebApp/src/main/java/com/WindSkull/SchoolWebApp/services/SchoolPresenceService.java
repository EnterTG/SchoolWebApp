package com.WindSkull.SchoolWebApp.services;

import java.util.List;
import java.util.Optional;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;

public interface SchoolPresenceService 
{
	Optional<PropertyBox> getPresence(Long presenceId);
	List<Long> getPresencesIds(Integer classId,Integer subjectId);
	List<PropertyBox> getPresencesBoxes(Integer classId,Integer subjectId);
	//Long getPresence(Integer classId,Integer subjectId);
	
	List<PropertyBox> getStudnentsPresence(Integer classId,Integer subjectId);
	List<PropertyBox> getStudnentPresence(Integer classId,Integer subjectId,Long studentId);
	
	
	OperationResult save(PropertyBox pbOrderItem);
	OperationResult delete(PropertyBox pbOrderItem);
}
