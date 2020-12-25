package com.tvo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvo.model.HpCostInfo;

@Repository
public interface HpCostsInfoDAO extends HpTuitionFeesDAO<HpCostInfo> {

	List<HpCostInfo> findByCostCodeIgnoreCaseAndDeleteAtIsNull(String costName);
}
