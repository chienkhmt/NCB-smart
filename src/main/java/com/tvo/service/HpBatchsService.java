package com.tvo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvo.dao.HpBatchInfoDAO;
import com.tvo.model.HpBatchsInfo;

@Service
public class HpBatchsService extends HpTuitionFeeService<HpBatchsInfo> {
	private static final Logger LOGGER = LoggerFactory.getLogger(HpBatchsService.class);
	@Autowired
	private HpBatchInfoDAO batchsService;

	@Override
	protected String TAG() {
		return "cost data ";
	}

	public List<HpBatchsInfo> findDataBatchs(String dataFind, int status) {
		List<HpBatchsInfo> lsBatchsInfos = new ArrayList<HpBatchsInfo>();
		try {
			LOGGER.info(TAG() + "filter start");
			HpBatchsInfo batchInfo = batchsService
					.findByBatchCodeIgnoreCaseAndStatusAndDeleteAtIsNull(dataFind.toUpperCase(), status);
			LOGGER.info(TAG() + "filter done: " + batchInfo);
			if (batchInfo != null) {
				lsBatchsInfos.add(batchInfo);
			}
		} catch (Exception e) {
			LOGGER.error(TAG() + "filter error: " + e.getMessage());
			e.printStackTrace();
		}
		return lsBatchsInfos;
	}
}
