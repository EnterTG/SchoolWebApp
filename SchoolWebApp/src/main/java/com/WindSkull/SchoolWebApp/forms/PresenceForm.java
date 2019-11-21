package com.WindSkull.SchoolWebApp.forms;

import java.util.List;

import com.WindSkull.SchoolWebApp.models.SchoolClassStudents;
import com.WindSkull.SchoolWebApp.services.SchoolClassStudentsService;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.builders.VerticalLayoutBuilder;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class PresenceForm extends HorizontalLayout{

	
	SchoolClassStudentsService classStudentsService;
	
	private Integer classId;
	private Integer subjectId;
	
	public PresenceForm() 
	{
		VerticalLayout vt = getStudentsList();
	}

	
	
	private VerticalLayout getStudentsList()
	{
		List<PropertyBox> students = classStudentsService.getClassStudentsBox(classId);
		
		VerticalLayoutBuilder vlb = Components.vl();
		
		students.stream().forEach(pb -> vlb.add(Components.paragraph().text(getStudentName(pb)).build()));
		
		return vlb.build();
	}
	
	
	private String getStudentName(PropertyBox studentPropertyBox)
	{
		return (studentPropertyBox.getValue(SchoolClassStudents.STUDENT_NAME) + " "  + studentPropertyBox.getValue( SchoolClassStudents.STUDENT_SURNAME));
	}
}
