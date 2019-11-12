package com.WindSkull.SchoolWebApp.services.implementation;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.WindSkull.SchoolWebApp.models.SchoolGrade;
import com.WindSkull.SchoolWebApp.services.SchoolGradeService;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.internal.utils.ObjectUtils;
import com.holonplatform.core.property.PropertyBox;

public class SchoolGradeServiceImpl implements SchoolGradeService{

	@Autowired
	private Datastore datastore;
	
	@Override
	public Optional<PropertyBox> getGrade(Long id) {
		ObjectUtils.argumentNotNull(id, "Missing grade Id");
		return datastore.query(SchoolGrade.TARGET).filter(SchoolGrade.ID.eq(id)).findOne(SchoolGrade.GRADES);
	}

	@Override
	public List<PropertyBox> getGrades(Long studentId) {
		ObjectUtils.argumentNotNull(studentId, "Missing student id");
		return datastore.query(SchoolGrade.TARGET).filter(SchoolGrade.STUDENTID.eq(studentId)).list(SchoolGrade.GRADES);
	
	}

	@Override
	public List<PropertyBox> getGrades(Long studentId, Integer classId) {
		ObjectUtils.argumentNotNull(studentId, "Missing student id");
		ObjectUtils.argumentNotNull(classId, "Missing class id");
		return datastore.query(SchoolGrade.TARGET)
				.filter(SchoolGrade.STUDENTID.eq(studentId))
				.filter(SchoolGrade.CLASSID.eq(classId))
				.list(SchoolGrade.GRADES);
	
	}

	@Override
	public List<PropertyBox> getGrades(Long studentId, Integer classId, Integer subjectId) {
		ObjectUtils.argumentNotNull(studentId, "Missing student id");
		ObjectUtils.argumentNotNull(classId, "Missing class id");
		ObjectUtils.argumentNotNull(subjectId, "Missing subject id");
		return datastore.query(SchoolGrade.TARGET)
				.filter(SchoolGrade.STUDENTID.eq(studentId))
				.filter(SchoolGrade.CLASSID.eq(classId))
				.filter(SchoolGrade.SUBJECTID.eq(subjectId))
				.list(SchoolGrade.GRADES);
	}


	@Override
	public List<PropertyBox> getGradesClass(Integer classId) {
		ObjectUtils.argumentNotNull(classId, "Missing class id");
		return datastore.query(SchoolGrade.TARGET)
				.filter(SchoolGrade.CLASSID.eq(classId))
				.list(SchoolGrade.GRADES);
	}

	@Override
	public List<PropertyBox> getGradesClass(Integer classId, Integer subjectId) {
		ObjectUtils.argumentNotNull(classId, "Missing class id");
		ObjectUtils.argumentNotNull(subjectId, "Missing subject id");
		return datastore.query(SchoolGrade.TARGET)
				.filter(SchoolGrade.CLASSID.eq(classId))
				.filter(SchoolGrade.SUBJECTID.eq(subjectId))
				.list(SchoolGrade.GRADES);
	}


	@Override
	public OperationResult save(@NotNull PropertyBox pbClassItem) {
		ObjectUtils.argumentNotNull(pbClassItem, "Missing Grade PropertyBox in save");
		return datastore.save(SchoolGrade.TARGET, pbClassItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

	@Override
	public OperationResult delete(@NotNull PropertyBox pbClassItem) {
		ObjectUtils.argumentNotNull(pbClassItem, "Missing Grade PropertyBox in delete");
		return datastore.delete(SchoolGrade.TARGET, pbClassItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}
}
