package com.tvo.dao;

import org.springframework.stereotype.Repository;

import com.tvo.model.HpBatchsInfo;

@Repository
public interface HpBatchInfoDAO extends HpTuitionFeesDAO<HpBatchsInfo> {

	HpBatchsInfo findByBatchCodeIgnoreCaseAndStatusAndDeleteAtIsNull(String batchCode, int status);
}
