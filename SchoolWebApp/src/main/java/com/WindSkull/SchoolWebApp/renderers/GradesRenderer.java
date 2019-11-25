package com.WindSkull.SchoolWebApp.renderers;

import java.util.ArrayList;
import java.util.List;

import com.WindSkull.SchoolWebApp.models.SchoolClassStudents;
import com.WindSkull.SchoolWebApp.models.SchoolGrade;
import com.WindSkull.SchoolWebApp.services.SchoolGradeService;
import com.holonplatform.core.Context;
import com.holonplatform.core.property.PropertyBox;
import com.holonplatform.vaadin.flow.components.Components;
import com.holonplatform.vaadin.flow.components.Input;
import com.holonplatform.vaadin.flow.components.PropertyInputForm;
import com.holonplatform.vaadin.flow.components.builders.HorizontalLayoutBuilder;
import com.holonplatform.vaadin.flow.components.builders.PropertyInputFormBuilder;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class GradesRenderer extends ComponentRenderer<HorizontalLayout, PropertyBox> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private SchoolGradeService schoolGradeService;
	
	private Integer classId;
	private Integer subjectId;
	private Long studentId;
	
	
	private List<PropertyInputForm> propertyBoxs= new ArrayList<>();
	
	public GradesRenderer(List<GradesRenderer> listrenderers , Integer classId,Integer subjectId) 
	{
		this.classId = classId;
		this.subjectId = subjectId;
		listrenderers.add(this);
	}

	@Override
	public HorizontalLayout createComponent(PropertyBox item) 
	{
		schoolGradeService = Context.get().resource(SchoolGradeService.class)
				.orElseThrow(() -> new IllegalStateException("Cannot retrieve SchoolGradeService from Context."));
		
		studentId = item.getValue(SchoolClassStudents.STUDENTID);
		
		//Notification.show("classId: " + classId + " subjectId: " + subjectId + " studentId: " + studentId, 10000, Position.BOTTOM_CENTER);
		
		List<PropertyInputForm> propertList = getGradesList();
		HorizontalLayoutBuilder grades = Components.hl();
	
		propertList.forEach(p -> {grades.add(Components.div().width("5px").build());grades.add(p);});
		HorizontalLayout ghl = grades.build();
		
		HorizontalLayout hl = Components.hl().fullWidth()
				.add(Components.label().text( item.getValue(SchoolClassStudents.STUDENT_NAME ) + " " + item.getValue(SchoolClassStudents.STUDENT_SURNAME) ).build() )
				.addAndExpand(ghl , 1)
				.add(Components.button()
						.icon(VaadinIcon.PLUS)
						.onClick(e -> 
							{
								PropertyInputForm pif = getInputForm(PropertyBox.builder(SchoolGrade.GRADES).build());
								ghl.add(Components.div().width("5px").build());
								ghl.add(pif.getComponent());
								//Notification.show("Pro: " + pif.getValue().getValue(SchoolGrade.CLASSID), 10000, Position.BOTTOM_CENTER);
								schoolGradeService.save(pif.getValue());
								
							})
						.build())
				.build();
		
		return hl;
		
	}
	public void save()
	{
		//propertyBoxs.forEach(p -> Notification.show("Pro: " +p.getValue().getValue(SchoolGrade.GRADE), 10000, Position.BOTTOM_CENTER));
		propertyBoxs.forEach(p -> schoolGradeService.save(p.getValue()));
	}
	
	private List<PropertyInputForm> getGradesList()
	{
		List<PropertyBox> gradesBox = schoolGradeService.getGrades(studentId, classId, subjectId);
		List<PropertyInputForm> gradesInput = new ArrayList<PropertyInputForm>();
		
		gradesBox.stream().forEach( g -> gradesInput.add(getInputForm(g)));
		
		return gradesInput;
	}
	
	private PropertyInputForm getInputForm(PropertyBox grade)
	{
		grade.setValue(SchoolGrade.STUDENTID, studentId);
		grade.setValue(SchoolGrade.CLASSID, classId);
		grade.setValue(SchoolGrade.SUBJECTID, subjectId);
		grade.setValue(SchoolGrade.GRADE, grade.getValue(SchoolGrade.GRADE));

		
		Input<String> gradeInput = Components.input.string().withValue("1").width("40px").maxWidth("40px")
		//.preventInvalidInput().pattern("|1[0-5]?|[2-9]")
		.withThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER)
		/*.prefixComponent(
				Components.button().icon(VaadinIcon.MINUS)
				.withThemeVariants(ButtonVariant.LUMO_ICON,ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY).maxWidth("20px")
				.onClick(evt -> 
					{
						
					
					}
				)
				.build()
		)*/
		.build();
		
		
		PropertyInputFormBuilder<FormLayout> fb =  Components.input.form(grade)
				.hidden(SchoolGrade.ID)
				.hidden(SchoolGrade.STUDENTID)
				.hidden(SchoolGrade.SUBJECTID)
				.hidden(SchoolGrade.CLASSID)
				.hidePropertyCaption(SchoolGrade.GRADE)
				.bind(SchoolGrade.GRADE	, gradeInput);
				
		PropertyInputForm pif = fb.build();
		
		pif.setValue(grade);
		propertyBoxs.add(pif);
		
		return pif;
	}
}
