package com.WindSkull.SchoolWebApp.services.implementation;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WindSkull.SchoolWebApp.models.SchoolClassStudents;
import com.WindSkull.SchoolWebApp.services.SchoolClassStudentsService;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.internal.utils.ObjectUtils;
import com.holonplatform.core.property.PropertyBox;
@Service
public class SchoolClassStudentsServiceImpl implements SchoolClassStudentsService {


	@Autowired
	private Datastore datastore;

	/*@Override
	public List<PropertyBox> getClassStudents(Integer classId) {
		return datastore.query(SchoolClassStudents.TARGET).filter(SchoolClassStudents.CLASSID.eq(classId))
				.list(SchoolClassStudents.CLASSSTUDENTS);
	}*/
	@Override
	public OperationResult save(@NotNull PropertyBox pbClassStudentsItem) {
		ObjectUtils.argumentNotNull(pbClassStudentsItem, "Missing Class PropertyBox in save");
		return datastore.save(SchoolClassStudents.TARGET, pbClassStudentsItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

	@Override
	public OperationResult delete(@NotNull PropertyBox pbClassStudentsItem) {
		ObjectUtils.argumentNotNull(pbClassStudentsItem, "Missing Class PropertyBox in delete");
		return datastore.delete(SchoolClassStudents.TARGET, pbClassStudentsItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}
	
	@Override
	public List<Long> getClassStudents(Integer classId) {
		return datastore.query(SchoolClassStudents.TARGET).filter(SchoolClassStudents.CLASSID.eq(classId))
				.list(SchoolClassStudents.STUDENTID);
	}

	@Override
	public Optional<Long> getClassStudentsId(Integer classId, Long studentId) {
		return datastore.query(SchoolClassStudents.TARGET)
				.filter(SchoolClassStudents.CLASSID.eq(classId).and( SchoolClassStudents.STUDENTID.eq(studentId)))
				.findOne(SchoolClassStudents.ID);

	}
}
