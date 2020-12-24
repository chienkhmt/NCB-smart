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
@Table(name = "HP_CLASS")
@Data
public class HpClassInfo extends HpBaseModel {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "HP_CLASS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID")
	private Integer id;
	@Column(name = "CLASS_CODE")
	private String classCode;

	@Column(name = "CLASS_NAME")
	private String className;

	@Column(name = "FACULTY_CODE")
	private String facultyCode;

	@Column(name = "SCHOOL_CODE")
	private String schoolCode;

}
