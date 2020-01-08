package com.WindSkull.SchoolWebApp.services.implementation;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WindSkull.SchoolWebApp.models.SchoolClassSubject;
import com.WindSkull.SchoolWebApp.services.SchoolClassSubjectsService;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.internal.utils.ObjectUtils;
import com.holonplatform.core.property.PropertyBox;

@Service
public class SchoolClassSubjectsServiceImpl implements SchoolClassSubjectsService {

	@Autowired
	private Datastore datastore;
	
	@Override
	public Optional<Long> getClassSubjectId(Integer classID, Long teacherID, Integer subjectID) {
		ObjectUtils.argumentNotNull(classID, "Missing class id");
		ObjectUtils.argumentNotNull(subjectID, "Missing subject id");
		ObjectUtils.argumentNotNull(teacherID, "Missing teacher id");
		return datastore.query(SchoolClassSubject.TARGET)
				.filter(SchoolClassSubject.CLASSID.eq(classID)
						.and(SchoolClassSubject.SUBJECTID.eq(subjectID))
						.and(SchoolClassSubject.TEACHERID.eq(teacherID)) )
				.findOne(SchoolClassSubject.ID);

	}

	@Override
	public List<PropertyBox> getClassSubjects(Integer classID) {
		ObjectUtils.argumentNotNull(classID, "Missing class id");
		return datastore.query(SchoolClassSubject.TARGET).filter(SchoolClassSubject.CLASSID.eq(classID))
				.list(SchoolClassSubject.CLASSSUBJECT);
	}

	@Override
	public List<PropertyBox> getTeacherSubjects(Long teacherID) {
		ObjectUtils.argumentNotNull(teacherID, "Missing teacher id");
		return datastore.query(SchoolClassSubject.TARGET).filter(SchoolClassSubject.TEACHERID.eq(teacherID))
				.list(SchoolClassSubject.CLASSSUBJECT);

	}

	@Override
	public List<PropertyBox> getSubjectClasses(Integer subjectID) {
		ObjectUtils.argumentNotNull(subjectID, "Missing subject id");
		return datastore.query(SchoolClassSubject.TARGET).filter(SchoolClassSubject.SUBJECTID.eq(subjectID))
				.list(SchoolClassSubject.CLASSSUBJECT);
	}

	@Override
	public List<PropertyBox> getTeacherSubjects(Integer classID, Long teacherID) {
		ObjectUtils.argumentNotNull(teacherID, "Missing teacher id");
		ObjectUtils.argumentNotNull(classID, "Missing class id");
		return datastore.query(SchoolClassSubject.TARGET).filter(SchoolClassSubject.TEACHERID.eq(teacherID).and(SchoolClassSubject.CLASSID.eq(classID)))
				.list(SchoolClassSubject.CLASSSUBJECT);
	}

	@Override
	public List<PropertyBox> getSubjectClasses(Integer subjectID, Long teacherID) {
		ObjectUtils.argumentNotNull(subjectID, "Missing subject id");
		ObjectUtils.argumentNotNull(teacherID, "Missing teacher id");
		return datastore.query(SchoolClassSubject.TARGET).filter(SchoolClassSubject.TEACHERID.eq(teacherID).and(SchoolClassSubject.SUBJECTID.eq(subjectID)))
				.list(SchoolClassSubject.CLASSSUBJECT);
	}

	@Override
	public OperationResult save(@NotNull PropertyBox pbClassStudentsItem) {
		ObjectUtils.argumentNotNull(pbClassStudentsItem, "Missing PropertyBox in save");
		return datastore.save(SchoolClassSubject.TARGET, pbClassStudentsItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

	@Override
	public OperationResult delete(@NotNull PropertyBox pbClassStudentsItem) {
		ObjectUtils.argumentNotNull(pbClassStudentsItem, "Missing PropertyBox in delete");
		return datastore.delete(SchoolClassSubject.TARGET, pbClassStudentsItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}


}
