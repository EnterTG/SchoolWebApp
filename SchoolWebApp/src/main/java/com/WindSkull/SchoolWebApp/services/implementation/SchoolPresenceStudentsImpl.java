package com.WindSkull.SchoolWebApp.services.implementation;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WindSkull.SchoolWebApp.models.SchoolPresenceStudents;
import com.WindSkull.SchoolWebApp.services.SchoolPresenceStudentsService;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.internal.utils.ObjectUtils;
import com.holonplatform.core.property.PropertyBox;
@Service
public class SchoolPresenceStudentsImpl implements SchoolPresenceStudentsService{

	@Autowired
	Datastore datastore;
	
	/*@Override
	public List<PropertyBox> getStudnentsPresence(Long presenceId) {
		ObjectUtils.argumentNotNull(presenceId, "Missing presenceId");
		
		return datastore.query(SchoolPresenceStudents.TARGET).filter(SchoolPresenceStudents.PRESENCEID.eq(presenceId)).list(SchoolPresenceStudents.PRESENCES);
	}

	@Override
	public List<PropertyBox> getStudnentPresence(Long presenceId, Long studentId) {
		ObjectUtils.argumentNotNull(studentId, "Missing studentId");
		ObjectUtils.argumentNotNull(presenceId, "Missing presenceId");
		return datastore.query(SchoolPresenceStudents.TARGET).filter(SchoolPresenceStudents.PRESENCEID.eq(presenceId).and(SchoolPresenceStudents.STUDENTID.eq(studentId))).list(SchoolPresenceStudents.PRESENCES);
	}
*/
	@Override
	public OperationResult save(@NotNull PropertyBox pbPresenceStudentsItem) {
		ObjectUtils.argumentNotNull(pbPresenceStudentsItem, "Missing PropertyBox in save");
		return datastore.save(SchoolPresenceStudents.TARGET, pbPresenceStudentsItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

	@Override
	public OperationResult delete(@NotNull PropertyBox pbPresenceStudentsItem) {
		ObjectUtils.argumentNotNull(pbPresenceStudentsItem, "Missing PropertyBox in delete");
		return datastore.delete(SchoolPresenceStudents.TARGET, pbPresenceStudentsItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

	@Override
	public List<PropertyBox> getStudnentsPresence(List<Long> presenceId) {
		ObjectUtils.argumentNotNull(presenceId, "Missing presenceId");
		return datastore.query(SchoolPresenceStudents.TARGET).filter(SchoolPresenceStudents.PRESENCEID.in(presenceId)).list(SchoolPresenceStudents.PRESENCES);
	}

	@Override
	public List<PropertyBox> getStudnentsPresence(List<Long> presenceId, Long studentId) {
		ObjectUtils.argumentNotNull(presenceId, "Missing presenceId");
		return datastore.query(SchoolPresenceStudents.TARGET).filter(SchoolPresenceStudents.PRESENCEID.in(presenceId).and(SchoolPresenceStudents.STUDENTID.eq(studentId))).list(SchoolPresenceStudents.PRESENCES);
	}

	



}
