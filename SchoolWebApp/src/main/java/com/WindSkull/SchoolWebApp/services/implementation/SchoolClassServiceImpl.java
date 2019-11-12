package com.WindSkull.SchoolWebApp.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.WindSkull.SchoolWebApp.models.SchoolClass;
import com.WindSkull.SchoolWebApp.services.SchoolClassService;
import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.core.datastore.Datastore.OperationResult;
import com.holonplatform.core.datastore.DefaultWriteOption;
import com.holonplatform.core.internal.utils.ObjectUtils;
import com.holonplatform.core.property.PropertyBox;



public class SchoolClassServiceImpl implements SchoolClassService{


	@Autowired
	private Datastore datastore;

	@Override
	public Optional<String> getClassName(@NotNull Integer classId) {
		ObjectUtils.argumentNotNull(classId, "Missing Class Id");
		return datastore.query(SchoolClass.TARGET).filter(SchoolClass.ID.eq(classId)).findOne(SchoolClass.NAME);
	
	}

	@Override
	public Optional<PropertyBox> getClass(@NotNull Integer classId) {
		ObjectUtils.argumentNotNull(classId, "Missing Class Id");
		return datastore.query(SchoolClass.TARGET).filter(SchoolClass.ID.eq(classId)).findOne(SchoolClass.CLASS);
	}

	@Override
	public List<PropertyBox> getClassByName(@NotNull String className) {
		ObjectUtils.argumentNotNull(className, "Missing Class name");
		return datastore.query(SchoolClass.TARGET).filter(SchoolClass.NAME.containsIgnoreCase(className)).list(SchoolClass.CLASS);
	}

	@Override
	public List<PropertyBox> getClassByYear(@NotNull LocalDate day) {
		ObjectUtils.argumentNotNull(day, "Missing Class date");
		return datastore.query(SchoolClass.TARGET).filter(SchoolClass.CREATEYEAR.eq(day)).list(SchoolClass.CLASS);
	}

	@Override
	public List<PropertyBox> getClassByYear(@NotNull LocalDate from,@NotNull LocalDate to) {
		ObjectUtils.argumentNotNull(from, "Missing Class date from");
		ObjectUtils.argumentNotNull(to, "Missing Class date to");
		return datastore.query(SchoolClass.TARGET).filter(SchoolClass.CREATEYEAR.between(from, to)).list(SchoolClass.CLASS);
	}

	@Override
	public List<PropertyBox> getAllClasses() {
		return datastore.query(SchoolClass.TARGET).list(SchoolClass.CLASS);
	}

	@Override
	public OperationResult save(@NotNull PropertyBox pbClassItem) {
		ObjectUtils.argumentNotNull(pbClassItem, "Missing Class PropertyBox in save");
		return datastore.save(SchoolClass.TARGET, pbClassItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}

	@Override
	public OperationResult delete(@NotNull PropertyBox pbClassItem) {
		ObjectUtils.argumentNotNull(pbClassItem, "Missing Class PropertyBox in delete");
		return datastore.delete(SchoolClass.TARGET, pbClassItem, DefaultWriteOption.BRING_BACK_GENERATED_IDS);
	}




}
