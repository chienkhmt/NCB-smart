package com.tvo.dao;

import org.springframework.stereotype.Repository;

import com.tvo.model.HpFacultieInfo;

@Repository
public interface HpFacultiesInfoDAO extends HpTuitionFeesDAO<HpFacultieInfo> {

	HpFacultieInfo findBySchoolCodeIgnoreCaseAndFacultyCodeContainingIgnoreCaseAndStatusAndDeleteAtIsNull(
			String schoolCode, String faCode, int status);
}
