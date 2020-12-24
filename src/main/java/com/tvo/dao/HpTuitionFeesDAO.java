package com.tvo.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.tvo.model.HpStudentInfo;

@NoRepositoryBean
public interface HpTuitionFeesDAO<T> extends JpaRepository<T, Integer> {
	T findByIdAndDeleteAtIsNull(Integer id);

	List<T> findByDeleteAtIsNull();
}
