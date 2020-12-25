package com.tvo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tvo.dao.HpSchoolsInfoDAO;
import com.tvo.model.HpSchoolInfo;

@Service
public class HpSchoolsService extends HpTuitionFeeService<HpSchoolInfo> {
	private static final Logger LOGGER = LoggerFactory.getLogger(HpSchoolsService.class);

	@Autowired
	private HpSchoolsInfoDAO schoolsInfoDAO;

	@Override
	protected String TAG() {
		return "school data ";
	}

	public List<HpSchoolInfo> findDataSchool(String dataFind, int status) {
		List<HpSchoolInfo> lsData = new ArrayList<HpSchoolInfo>();
		try {
			LOGGER.info(TAG() + "filter start");
			HpSchoolInfo lsSchools = schoolsInfoDAO
					.findBySchoolCodeContainingIgnoreCaseAndStatusAndDeleteAtIsNull(dataFind.toUpperCase(), status);
			LOGGER.info(TAG() + "filter done: " + lsSchools);
			if (lsSchools != null) {
				lsData.add(lsSchools);
			}
			return lsData;
		} catch (Exception e) {
			LOGGER.error(TAG() + "filter error: " + e.getMessage());
			e.printStackTrace();
			return new ArrayList<HpSchoolInfo>();
		}
	}

	@Override
	public List<HpSchoolInfo> getData(Pageable pageable) {
		try {
			LOGGER.info(TAG() + "get start");
			List<HpSchoolInfo> lsSchools = schoolsInfoDAO.findByDeleteAtIsNull(pageable);
			LOGGER.info(TAG() + "get done: " + lsSchools);
			return lsSchools;
		} catch (Exception e) {
			LOGGER.error(TAG() + "get error: " + e.getMessage());
			e.printStackTrace();
			return new ArrayList<HpSchoolInfo>();
		}
	}
}
