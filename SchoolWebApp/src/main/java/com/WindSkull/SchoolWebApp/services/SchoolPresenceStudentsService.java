package com.WindSkull.SchoolWebApp.services;

import java.util.List;

import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.property.PropertyBox;

public interface SchoolPresenceStudentsService 
{

	
	
	List<PropertyBox> getStudnentsPresence(List<Long> presenceId);
	List<PropertyBox> getStudnentsPresence(List<Long> presenceId,Long studentId);
	OperationResult save(PropertyBox pbOrderItem);
	OperationResult delete(PropertyBox pbOrderItem);
}
