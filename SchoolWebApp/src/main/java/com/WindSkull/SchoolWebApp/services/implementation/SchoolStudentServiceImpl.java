package com.WindSkull.SchoolWebApp.services.implementation;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WindSkull.SchoolWebApp.models.SchoolStudent;
import com.WindSkull.SchoolWebApp.services.SchoolStudentService;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.internal.utils.ObjectUtils;
import com.holonplatform.core.property.PropertyBox;
@Service
public class SchoolStudentServiceImpl implements SchoolStudentService{

	@Autowired
	private Datastore datastore;

	@Override
	public Optional<PropertyBox> getStudent(@NotNull Long id) {
		ObjectUtils.argumentNotNull(id, "Missing student Id");
		return datastore.query(SchoolStudent.TARGET).filter(SchoolStudent.ID.eq(id)).findOne(SchoolStudent.STUDENT);
	
	}

	@Override
	public List<PropertyBox> getStudentsByName(@NotNull String studentName) {
		ObjectUtils.argumentNotNull(studentName, "Missing student name");
		return datastore.query(SchoolStudent.TARGET).filter(SchoolStudent.NAME.containsIgnoreCase(studentName)).list(SchoolStudent.STUDENT);
	
	}

	@Override
	public List<PropertyBox> getStudentsBySurname(@NotNull String studentSurname) {
		ObjectUtils.argumentNotNull(studentSurname, "Missing student surname");
		return datastore.query(SchoolStudent.TARGET).filter(SchoolStudent.SURNAME.containsIgnoreCase(studentSurname)).list(SchoolStudent.STUDENT);
	
	}

	@Override
	public List<PropertyBox> getStudentsByNameSurname(@NotNull String studentName,@NotNull  String studentSurname) {
		ObjectUtils.argumentNotNull(studentSurname, "Missing student surname");
		ObjectUtils.argumentNotNull(studentName, "Missing student name");
		return datastore.query(SchoolStudent.TARGET)
				.filter(SchoolStudent.SURNAME.containsIgnoreCase(studentSurname))
				.filter(SchoolStudent.NAME.containsIgnoreCase(studentName))
				.list(SchoolStudent.STUDENT);
	
	}

	@Override
	public Optional<PropertyBox> getStudentByIndex(@NotNull String index) {
		ObjectUtils.argumentNotNull(index, "Missing student index");
		return datastore.query(SchoolStudent.TARGET).filter(SchoolStudent.BOOKID.containsIgnoreCase(index)).findOne(SchoolStudent.STUDENT);
	}

	@Override
	public OperationResult save(@NotNull PropertyBox pbClassItem) {
		ObjectUtils.argumentNotNull(pbClassItem, "Missing Student PropertyBox in save");
		return datastore.save(SchoolStudent.TARGET, pbClassItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

	@Override
	public OperationResult delete(@NotNull PropertyBox pbClassItem) {
		ObjectUtils.argumentNotNull(pbClassItem, "Missing Student PropertyBox in delete");
		return datastore.delete(SchoolStudent.TARGET, pbClassItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}
	

}
