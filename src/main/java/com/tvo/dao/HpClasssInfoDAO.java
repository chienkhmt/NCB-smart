package com.tvo.dao;

import org.springframework.stereotype.Repository;

import com.tvo.model.HpClassInfo;

@Repository
public interface HpClasssInfoDAO extends HpTuitionFeesDAO<HpClassInfo> {

	HpClassInfo findBySchoolCodeIgnoreCaseAndFacultyCodeIgnoreCaseAndClassCodeIgnoreCaseAndStatusAndDeleteAtIsNull(
			String schoolCode, String facultyCode, String classCode, int status);
}
