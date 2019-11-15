package com.WindSkull.SchoolWebApp.services.implementation;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WindSkull.SchoolWebApp.models.SchoolSubject;
import com.WindSkull.SchoolWebApp.services.SchoolSubjectService;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.internal.utils.ObjectUtils;
import com.holonplatform.core.property.PropertyBox;
@Service
public class SchoolSubjectServiceImpl implements SchoolSubjectService{

	@Autowired
	Datastore datastore;
	
	@Override
	public Optional<PropertyBox> getSubject(Integer id) {
		return datastore.query(SchoolSubject.TARGET).filter(SchoolSubject.ID.eq(id)).findOne(SchoolSubject.SUBJECT);
	}

	
	
	/*
	 * return -1 when subject not found 
	 * 
	 */
	@Override
	public Integer getSubjectByName(String subjectName) {
		return datastore.query(SchoolSubject.TARGET).filter(SchoolSubject.NAME.eq(subjectName)).findOne(SchoolSubject.ID).orElse(-1);
	}

	@Override
	public List<PropertyBox> getSubjects(String subjectName) {
		return datastore.query(SchoolSubject.TARGET).filter(SchoolSubject.NAME.contains(subjectName)).list(SchoolSubject.SUBJECT);
	}

	@Override
	public OperationResult save(@NotNull PropertyBox pbTeacherItem) {
		ObjectUtils.argumentNotNull(pbTeacherItem, "Missing Student PropertyBox in save");
		return datastore.save(SchoolSubject.TARGET, pbTeacherItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

	@Override
	public OperationResult delete(@NotNull PropertyBox pbTeacherItem) {
		ObjectUtils.argumentNotNull(pbTeacherItem, "Missing Student PropertyBox in delete");
		return datastore.delete(SchoolSubject.TARGET, pbTeacherItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}


}
