package com.tvo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "HP_STUDENTS")
@Data
public class HpStudentInfo extends HpBaseModel {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "HP_STUDENTS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID")
	private Integer id;
	@Column(name = "STUDENT_CODE")
	private String studentCode;

	@Column(name = "STUDENT_NAME")
	private String studentName;

	@Column(name = "CLASS_CODE")
	private String classCode;

	@Column(name = "FACULTIY_CODE")
	private String facultiyCode;

	@Column(name = "SCHOOL_CODE")
	private String schoolCode;
}
