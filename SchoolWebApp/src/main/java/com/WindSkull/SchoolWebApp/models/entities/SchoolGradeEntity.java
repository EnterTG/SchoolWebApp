package com.WindSkull.SchoolWebApp.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "grades") 
@Table(name = "grades")
public class SchoolGradeEntity {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer studentid;
	private Integer classid;
	private Integer subjectid;
	private Integer grade;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the studentid
	 */
	public Integer getStudentid() {
		return studentid;
	}
	/**
	 * @param studentid the studentid to set
	 */
	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}
	/**
	 * @return the classid
	 */
	public Integer getClassid() {
		return classid;
	}
	/**
	 * @param classid the classid to set
	 */
	public void setClassid(Integer classid) {
		this.classid = classid;
	}
	/**
	 * @return the subjectid
	 */
	public Integer getSubjectid() {
		return subjectid;
	}
	/**
	 * @param subjectid the subjectid to set
	 */
	public void setSubjectid(Integer subjectid) {
		this.subjectid = subjectid;
	}
	/**
	 * @return the grade
	 */
	public Integer getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classid == null) ? 0 : classid.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((studentid == null) ? 0 : studentid.hashCode());
		result = prime * result + ((subjectid == null) ? 0 : subjectid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchoolGradeEntity other = (SchoolGradeEntity) obj;
		if (classid == null) {
			if (other.classid != null)
				return false;
		} else if (!classid.equals(other.classid))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (studentid == null) {
			if (other.studentid != null)
				return false;
		} else if (!studentid.equals(other.studentid))
			return false;
		if (subjectid == null) {
			if (other.subjectid != null)
				return false;
		} else if (!subjectid.equals(other.subjectid))
			return false;
		return true;
	}
	
}
