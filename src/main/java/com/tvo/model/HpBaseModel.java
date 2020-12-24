package com.tvo.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
@MappedSuperclass
public class HpBaseModel {

	@Column(name = "STATUS")
	private int status;

	@Getter(value = AccessLevel.NONE)
	@Column(name = "CREATED_AT")
	private LocalDate createAt;

	@Getter(value = AccessLevel.NONE)
	@Column(name = "UPDATED_AT")
	private LocalDate updateAt;

	@Getter(value = AccessLevel.NONE)
	@Column(name = "DELETED_AT")
	private LocalDate deleteAt;
}
