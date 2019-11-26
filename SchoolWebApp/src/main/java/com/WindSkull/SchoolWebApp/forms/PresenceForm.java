package com.WindSkull.SchoolWebApp.forms;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.WindSkull.SchoolWebApp.models.SchoolClassStudents;
import com.WindSkull.SchoolWebApp.models.SchoolPresence;
import com.WindSkull.SchoolWebApp.models.SchoolPresenceStudents;
import com.WindSkull.SchoolWebApp.models.SchoolStudent;
import com.WindSkull.SchoolWebApp.renderers.StudentPresenceRenderer;
import com.WindSkull.SchoolWebApp.services.SchoolClassStudentsService;
import com.WindSkull.SchoolWebApp.services.SchoolPresenceService;
import com.WindSkull.SchoolWebApp.services.SchoolPresenceStudentsService;
import com.WindSkull.SchoolWebApp.services.SchoolStudentService;
import com.holonplatform.core.Context;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.vaadin.flow.components.Components;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
public class PresenceForm extends HorizontalLayout{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SchoolClassStudentsService classStudentsService;
	
	SchoolPresenceService presenceService;
	
	SchoolPresenceStudentsService presenceStudentsService;
	
	SchoolStudentService schoolStudentService;
	
	private Integer classId;
	private Integer subjectId;
	
	List<PropertyBox> students;
	private Grid<StudentPresence> presenceTable;
	
	

	
	public PresenceForm( Integer classId,Integer subjectId) 
	{
		this.classId = classId;
		this.subjectId = subjectId;
		
		classStudentsService = Context.get().resource(SchoolClassStudentsService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve classStudentsService from Context."));
		
		presenceService = Context.get().resource(	SchoolPresenceService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve SchoolPresenceService from Context."));
		
		presenceStudentsService = Context.get().resource(	SchoolPresenceStudentsService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve SchoolPresenceStudentsService from Context."));
		
		schoolStudentService = Context.get().resource(	SchoolStudentService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve SchoolStudentService from Context."));
		
	
		
		students = classStudentsService.getClassStudentsBox(classId);
		//Id of presence base of class id and subject id
		List<Long> presencesIds = presenceService.getPresencesIds(classId, subjectId);
		
		//
		List<StudentPresence> studentsPresenceClassList = students.stream().map(sb -> getStudentPresenceClass(presencesIds,sb.getValue(SchoolClassStudents.STUDENTID))).collect(Collectors.toList());
		
		presenceTable = new Grid<>();
		presenceTable.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
		presenceTable.setSizeFull();
		presenceTable.setItems(studentsPresenceClassList);
		
		presenceTable.addColumn(StudentPresence::getName).setResizable(true).setWidth("250px").setHeader("Student");
		presencesIds.forEach(pid -> presenceTable.addColumn(new StudentPresenceRenderer(pid)).setHeader(presenceService.getPresence(pid).get().getValue(SchoolPresence.DATE).toLocalDate().toString()));
		
		add(Components.hl().fullSize().addAndExpand(presenceTable,1).build());
	}

	private StudentPresence getStudentPresenceClass(List<Long> presencesIds,Long studnetId)
	{
		StudentPresence sp = new StudentPresence();
		Optional<PropertyBox> spb = schoolStudentService.getStudent(studnetId);
		if(spb.isPresent())
		{
			sp.studnetName = spb.get().getValue(SchoolStudent.NAME);
			sp.studentSurname = spb.get().getValue(SchoolStudent.SURNAME);
			sp.studnetID = studnetId;
		}
		List<PropertyBox> studentPresence = presenceStudentsService.getStudnentsPresence(presencesIds, studnetId);
		studentPresence.forEach(pb -> sp.presences.put(pb.getValue(SchoolPresenceStudents.PRESENCEID), pb.getValue(SchoolPresenceStudents.PRESENCE)));
		return sp;
	}

	//Create new presence for each student form classstudent
	public void addPresenceCheck(Date dt)
	{
		PropertyBox classSubjectPresence = PropertyBox.create(SchoolPresence.PRESENCES);
		classSubjectPresence.setValue(SchoolPresence.CLASSID, classId);
		classSubjectPresence.setValue(SchoolPresence.SUBJECTID, subjectId);
		classSubjectPresence.setValue(SchoolPresence.DATE, convertToLocalDateTimeViaInstant(dt));
		
		Long newPresenceId = (Long) presenceService.save(classSubjectPresence).getFirstInsertedKey().get();
		
		addNewPresenceCheckToStudents(newPresenceId);
		
	}
	
	private void addNewPresenceCheckToStudents(Long presenceID)
	{
		students.stream().forEach(e -> 
		{
			PropertyBox studentPresence = PropertyBox.create(SchoolPresenceStudents.PRESENCES);
			studentPresence.setValue(SchoolPresenceStudents.PRESENCEID, presenceID);
			studentPresence.setValue(SchoolPresenceStudents.STUDENTID, e.getValue(SchoolClassStudents.STUDENTID));
			
			presenceStudentsService.save(studentPresence);
		});
	}
	
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) 
	{
		return dateToConvert.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();
	}
	
	public class StudentPresence
	{
		public String studnetName,studentSurname;
	
		public Long studnetID;
		
		//presence id , is presence
		public HashMap<Long,Boolean> presences = new HashMap<>();
		
		public String getName()
		{
			return studnetName + " " + studentSurname;
		}
		
		public PropertyBox getPresenceStudentsBox(Long presenceID)
		{
			PropertyBox pb = PropertyBox.create(SchoolPresenceStudents.PRESENCES);
			pb.setValue(SchoolPresenceStudents.PRESENCEID, presenceID);
			pb.setValue(SchoolPresenceStudents.PRESENCE, presences.getOrDefault(presenceID, false));
			pb.setValue(SchoolPresenceStudents.STUDENTID, studnetID);
			return pb;
		}
	}
	
}
