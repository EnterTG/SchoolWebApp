package com.WindSkull.SchoolWebApp.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "classstudents") 
@Table(name = "classstudents")
public class SchoolClassStudentsEntity {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer studentid;
	private Integer classid;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classid == null) ? 0 : classid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((studentid == null) ? 0 : studentid.hashCode());
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
		SchoolClassStudentsEntity other = (SchoolClassStudentsEntity) obj;
		if (classid == null) {
			if (other.classid != null)
				return false;
		} else if (!classid.equals(other.classid))
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
		return true;
	}
	
}
