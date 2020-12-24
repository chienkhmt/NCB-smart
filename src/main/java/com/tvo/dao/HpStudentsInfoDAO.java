package com.tvo.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tvo.model.HpStudentInfo;

@Repository
public interface HpStudentsInfoDAO extends HpTuitionFeesDAO<HpStudentInfo> {

	@Query(countQuery = "SELECT count(*) HpStudentInfo p WHERE p.studentName = :studentName", nativeQuery = true)
	List<HpStudentInfo> findByStudentNameContainingIgnoreCaseAndDeleteAtIsNull(String studentName, Pageable pageable);

	@Query(countQuery = "SELECT count(*) HpStudentInfo p WHERE p.deleteAt is null", nativeQuery = true)
	List<HpStudentInfo> findByDeleteAtIsNull(Pageable pageable);
}
