package com.tvo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvo.dao.HpCostsInfoDAO;
import com.tvo.model.HpCostInfo;

@Service
public class HpCostsService extends HpTuitionFeeService<HpCostInfo> {
	private static final Logger LOGGER = LoggerFactory.getLogger(HpCostsService.class);
	@Autowired
	private HpCostsInfoDAO costsInfoDAO;

	@Override
	protected String TAG() {
		return "cost data ";
	}

	@Override
	public List<HpCostInfo> findDataBy(String dataFind) {
		try {
			LOGGER.info(TAG() + "filter start");
			List<HpCostInfo> lsCosts = costsInfoDAO.findByCostCodeAndDeleteAtIsNull(dataFind);
			LOGGER.info(TAG() + "filter done: " + lsCosts);
			return lsCosts;
		} catch (Exception e) {
			LOGGER.error(TAG() + "filter error: " + e.getMessage());
			e.printStackTrace();
			return new ArrayList<HpCostInfo>();
		}
	}
}
