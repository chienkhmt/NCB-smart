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
@Table(name = "HP_SCHOOLS")
@Data
public class HpSchoolInfo extends HpBaseModel {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "HP_SCHOOLS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID")
	private Integer id;

	@Column(name = "SCHOOL_CODE")
	private String schoolCode;

	@Column(name = "SCHOOL_NAME")
	private String schoolName;

	@Column(name = "SCHOOL_CIF")
	private String schoolCif;

	@Column(name = "ACCT_NO")
	private String schoolAcctNo;

	@Column(name = "ACCT_NAME")
	private String schoolAcctName;

	@Column(name = "BANK_CODE")
	private String schoolBankCode;

	@Column(name = "BANK_NAME")
	private String schoolBankName;

	@Column(name = "CITAD_GT")
	private String schoolCitadGt;

	@Column(name = "CITAD_TT")
	private String schoolCitadTT;

	@Column(name = "ADDRESS")
	private String schoolAddress;

	@Column(name = "PHONE_NUMBER")
	private String schoolPhoneNumber;

}
