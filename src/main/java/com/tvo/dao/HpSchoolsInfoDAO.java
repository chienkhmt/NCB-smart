package com.tvo.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tvo.model.HpSchoolInfo;

@Repository
public interface HpSchoolsInfoDAO extends HpTuitionFeesDAO<HpSchoolInfo> {

	HpSchoolInfo findBySchoolCodeIgnoreCaseAndStatusAndDeleteAtIsNull(String schoolCode, int status);

	@Query(countQuery = "SELECT count(*) HpSchoolInfo p WHERE p.deleteAt is null", nativeQuery = true)
	List<HpSchoolInfo> findByDeleteAtIsNull(Pageable pageable);
}
