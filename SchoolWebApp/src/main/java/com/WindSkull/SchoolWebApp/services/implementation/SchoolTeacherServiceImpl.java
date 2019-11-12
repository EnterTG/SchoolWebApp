package com.WindSkull.SchoolWebApp.services.implementation;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WindSkull.SchoolWebApp.models.SchoolTeacher;
import com.WindSkull.SchoolWebApp.services.SchoolTeacherService;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.internal.utils.ObjectUtils;
import com.holonplatform.core.property.PropertyBox;
@Service
public class SchoolTeacherServiceImpl implements SchoolTeacherService {

	@Autowired
	private Datastore datastore;

	@Override
	public Optional<PropertyBox> getTeacher(Long teacherId) {
		ObjectUtils.argumentNotNull(teacherId, "Missing student Id");
		return datastore.query(SchoolTeacher.TARGET).filter(SchoolTeacher.ID.eq(teacherId)).findOne(SchoolTeacher.TEACHER);
	
	}

	@Override
	public List<PropertyBox> getTeacher(Integer classId) {
		ObjectUtils.argumentNotNull(classId, "Missing class id");
		return datastore.query(SchoolTeacher.TARGET).filter(SchoolTeacher.CLASSID.eq(classId)).list(SchoolTeacher.TEACHER);
	
	}

	@Override
	public Optional<PropertyBox> getTeacher(Integer classId, Integer subjectId) {
		ObjectUtils.argumentNotNull(classId, "Missing class id");
		return datastore.query(SchoolTeacher.TARGET)
				.filter(SchoolTeacher.CLASSID.eq(classId))
				.filter(SchoolTeacher.SUBJECTID.eq(subjectId)).findOne(SchoolTeacher.TEACHER);
	}

	@Override
	public OperationResult save(@NotNull PropertyBox pbTeacherItem) {
		ObjectUtils.argumentNotNull(pbTeacherItem, "Missing Student PropertyBox in save");
		return datastore.save(SchoolTeacher.TARGET, pbTeacherItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

	@Override
	public OperationResult delete(@NotNull PropertyBox pbTeacherItem) {
		ObjectUtils.argumentNotNull(pbTeacherItem, "Missing Student PropertyBox in delete");
		return datastore.delete(SchoolTeacher.TARGET, pbTeacherItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}
}
