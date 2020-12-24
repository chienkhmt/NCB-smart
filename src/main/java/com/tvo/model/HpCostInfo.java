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
@Table(name = "HP_COSTS")
@Data
public class HpCostInfo extends HpBaseModel {

	@Id
	@SequenceGenerator(name = "generator", sequenceName = "HP_COSTS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID")
	private Integer id;
	@Column(name = "COST_CODE")
	private String costCode;

	@Column(name = "COST_NAME")
	private String costName;

}
