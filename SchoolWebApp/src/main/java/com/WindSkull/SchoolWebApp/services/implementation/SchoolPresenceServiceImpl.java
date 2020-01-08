package com.WindSkull.SchoolWebApp.services.implementation;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WindSkull.SchoolWebApp.models.SchoolPresence;
import com.WindSkull.SchoolWebApp.services.SchoolPresenceService;
import com.WindSkull.SchoolWebApp.services.SchoolPresenceStudentsService;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.internal.utils.ObjectUtils;
import com.holonplatform.core.property.PropertyBox;
@Service
public class SchoolPresenceServiceImpl implements SchoolPresenceService{

	@Autowired
	Datastore datastore;
	
	@Autowired
	SchoolPresenceStudentsService presenceStudentsService;
	
	

	
	@Override
	public List<PropertyBox> getStudnentsPresence(Integer classId, Integer subjectId) {
		ObjectUtils.argumentNotNull(classId, "Missing classid");
		ObjectUtils.argumentNotNull(subjectId, "Missing subjectid");
		return presenceStudentsService.getStudnentsPresence(
				datastore.query(SchoolPresence.TARGET).filter(SchoolPresence.CLASSID.eq(classId).and(SchoolPresence.SUBJECTID.eq(subjectId))).list(SchoolPresence.ID));
	}

	@Override
	public List<PropertyBox> getStudnentPresence(Integer classId, Integer subjectId, Long studentId) {
		ObjectUtils.argumentNotNull(classId, "Missing classid");
		ObjectUtils.argumentNotNull(subjectId, "Missing subjectid");
		ObjectUtils.argumentNotNull(studentId, "Missing studentid");
		return presenceStudentsService.getStudnentsPresence(
				datastore.query(SchoolPresence.TARGET).filter(SchoolPresence.CLASSID.eq(classId).and(SchoolPresence.SUBJECTID.eq(subjectId))).list(SchoolPresence.ID));
	}

	@Override
	public OperationResult save(@NotNull PropertyBox pbPresenceStudentsItem) {
		ObjectUtils.argumentNotNull(pbPresenceStudentsItem, "Missing PropertyBox in save");
		return datastore.save(SchoolPresence.TARGET, pbPresenceStudentsItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

	@Override
	public OperationResult delete(@NotNull PropertyBox pbPresenceStudentsItem) {
		ObjectUtils.argumentNotNull(pbPresenceStudentsItem, "Missing PropertyBox in delete");
		return datastore.delete(SchoolPresence.TARGET, pbPresenceStudentsItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

	@Override
	public List<Long> getPresencesIds(Integer classId, Integer subjectId) {
		ObjectUtils.argumentNotNull(classId, "Missing classId");
		ObjectUtils.argumentNotNull(subjectId, "Missing subjectId");
		return datastore.query(SchoolPresence.TARGET).filter(SchoolPresence.CLASSID.eq(classId).and(SchoolPresence.SUBJECTID.eq(subjectId))).list(SchoolPresence.ID);
	}


	@Override
	public List<PropertyBox> getPresencesBoxes(Integer classId, Integer subjectId) {
		ObjectUtils.argumentNotNull(classId, "Missing classId");
		ObjectUtils.argumentNotNull(subjectId, "Missing subjectId");
		return datastore.query(SchoolPresence.TARGET).filter(SchoolPresence.CLASSID.eq(classId).and(SchoolPresence.SUBJECTID.eq(subjectId))).list(SchoolPresence.PRESENCES);
	}

	@Override
	public Optional<PropertyBox> getPresence(Long presenceId) {
		ObjectUtils.argumentNotNull(presenceId, "Missing presenceId");
		return datastore.query(SchoolPresence.TARGET).filter(SchoolPresence.ID.eq(presenceId)).findOne(SchoolPresence.PRESENCES);
	}


}
