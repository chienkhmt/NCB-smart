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
@Table(name = "HP_FACULTIES")
@Data
public class HpFacultieInfo extends HpBaseModel {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "HP_FACULTIES_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID")
	private Integer id;

	@Column(name = "FACULTY_CODE")
	private String facultyCode;

	@Column(name = "FACULTY_NAME")
	private String facultyName;

	@Column(name = "SCHOOL_CODE")
	private String schoolCode;

}
