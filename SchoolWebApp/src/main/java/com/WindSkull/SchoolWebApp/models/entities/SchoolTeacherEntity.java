package com.WindSkull.SchoolWebApp.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "subjects") 
@Table(name = "subjects")
public class SchoolTeacherEntity {
	@Id
	@GeneratedValue
	private Long id;
	private Integer userid;
	private Integer classid;
	private Integer subjectid;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the userid
	 */
	public Integer getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(Integer userid) {
		this.userid = userid;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classid == null) ? 0 : classid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((subjectid == null) ? 0 : subjectid.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		SchoolTeacherEntity other = (SchoolTeacherEntity) obj;
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
		if (subjectid == null) {
			if (other.subjectid != null)
				return false;
		} else if (!subjectid.equals(other.subjectid))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}
}
